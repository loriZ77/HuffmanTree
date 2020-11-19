import javafx.util.Pair;

/**
 * Trie Node class.
 * Each node will record corresponding frequency, character, and Node[] children.
 */
public class Node {
  private int frequency;
  private String character;
  private Node[] children;
  private boolean isLeaf;


  public Node(int freq, String s, Node[] children) {
    isLeaf = false;
    this.character = s;
    this.frequency = freq;
    this.children = children;
  }

  public int getFrequency() {
    return frequency;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public void setChildren(Node[] children) {
    this.children = children;
  }


  public Node[] getChildren() {
    return children;
  }

  public boolean isLeaf() {
    return isLeaf;
  }

  public void setLeaf(boolean leaf) {
    isLeaf = leaf;
  }

  public String toString() {
    Pair<Integer, String> pair = new Pair<>(this.getFrequency(), this.character);
    return pair.toString();
  }


}
