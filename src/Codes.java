import java.util.Map;
import java.util.PriorityQueue;

public interface Codes {
  void buildFreqMap();

  Map<Character, Integer> getFreqMap();

  void buildMinHeap();

  PriorityQueue<Node> getMinHeap();

  Node buildTrie();

  Node getTrie();

  void encodeMap();

  String encode();

  String decode(String encoding);


}
