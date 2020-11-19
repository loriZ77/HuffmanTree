import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Encode and decode data using prefix encodings.
 * Given a source message, use Huffman Algorithm construct prefix encoding.
 * Encode and decode messages by symbol->code dictionary containing a prefix code.
 */
public class HuffmanCode implements Codes {
  private String source;
  private int base;
  Map<Character, Integer> freqMap;
  Map<Character, String> encodingMap;
  PriorityQueue<Node> minHeap;
  Node root;

  public HuffmanCode(String input, int base) {
    if (base < 2 || base > 16 || input == "") {
      throw new IllegalArgumentException("Invalid Input!");
    }
    this.source = input;
    this.base = base;
    freqMap = new HashMap<>();
    encodingMap = new HashMap<>();
    minHeap = new PriorityQueue<>((x, y) -> x.getFrequency() == y.getFrequency() ?
        x.getCharacter().compareTo(y.getCharacter()) : x.getFrequency() - y.getFrequency());

  }


  /**
   * Create a frequency table (a,f(a)) where a is a symbol in source and f(a) is the
   * number of times a occurs in source.
   */
  @Override
  public void buildFreqMap() {
    char[] sourceArr = source.toCharArray();
    for (char c : sourceArr) {
      if (!freqMap.containsKey(c)) {
        freqMap.put(c, 1);
      } else {
        freqMap.put(c, freqMap.get(c) + 1);
      }
    }
  }

  @Override
  public Map<Character, Integer> getFreqMap() {
    return freqMap;
  }

  /**
   * Add all symbols into a priority queue minHeap, using their frequencies as priorities.
   * If frequencies equal, return as lexicographical order.
   */
  @Override
  public void buildMinHeap() {
    for (char c : freqMap.keySet()) {
      int freq = freqMap.get(c);
      Node node = new Node(freq, String.valueOf(c), new Node[] {});
      node.setLeaf(true);
      minHeap.offer(node);
    }
  }

  @Override
  public PriorityQueue<Node> getMinHeap() {
    return this.minHeap;
  }

  /**
   * Build Huffman Trie bottom-up.
   *
   * @return node
   */
  @Override
  public Node buildTrie() {
    while (minHeap.size() > 1) {
      if (minHeap.size() < base) {
        trieHelper(minHeap.size());
      } else {
        trieHelper(base);
      }
    }
    return minHeap.poll();
  }

  private void trieHelper(int size) {
    Node[] children = new Node[size];
    for (int i = 0; i < size; i++) {
      children[i] = minHeap.poll();
    }
    int newFreq = 0;
    StringBuilder sb = new StringBuilder();
    for (Node node : children) {
      newFreq += node.getFrequency();
      sb.append(node.getCharacter());
    }
    Node mergeNode = new Node(newFreq, sb.toString(), children);
    minHeap.offer(mergeNode);
  }

  @Override
  public Node getTrie() {
    this.root = buildTrie();
    return root;
  }

  /**
   * Build Hashmap, mapping from character to encoding.
   */
  @Override
  public void encodeMap() {
    encodeHelper(root, "", encodingMap);

  }

  /**
   * Use dfs to traverse the huffman trie, build encode hashmap.
   *
   * @param root root
   * @param path String
   * @param map  HashMap
   */
  private void encodeHelper(Node root, String path, Map<Character, String> map) {
    if (root.getChildren().length == 0) {
      map.put(root.getCharacter().charAt(0), path);
      return;
    }
    for (int i = 0; i < root.getChildren().length; i++) {
      if (base <= 10) {
        encodeHelper(root.getChildren()[i], path + i, map);
      } else {
        encodeHelper(root.getChildren()[i], path + Integer.toHexString(i), map);
      }

    }

  }

  public Map<Character, String> getEncodingMap() {
    return encodingMap;
  }

  /**
   * Use encoding hashmap to encode message.
   *
   * @return String
   */
  @Override
  public String encode() {
    buildFreqMap();
    buildMinHeap();
    getTrie();
    encodeMap();
    StringBuilder sb = new StringBuilder();
    char[] chars = source.toCharArray();
    for (char c : chars) {
      sb.append(encodingMap.get(c));
    }
    return sb.toString();
  }

  /**
   * Decode by traversing huffman trie up to bottom.
   *
   * @param encoding String
   * @return String
   */
  @Override
  public String decode(String encoding) {
    char[] code = encoding.toCharArray();
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < code.length) {
      Node curr = root;
      while (curr.getChildren().length != 0) {
        if (code[i] <= '9' && code[i] >= '0') {
          curr = curr.getChildren()[code[i] - '0'];
        } else {
          curr = curr.getChildren()[Character.toLowerCase(code[i]) - 'a' + 10];
        }
        i++;
      }
      sb.append(curr.getCharacter());
    }
    return sb.toString();
  }
}
