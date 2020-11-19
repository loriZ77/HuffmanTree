import org.junit.Before;
import org.junit.Test;

public class HuffmanCodeTest {
  HuffmanCode test1;
  HuffmanCode test2;

  @Before
  public void setUp() throws Exception {
    test1 = new HuffmanCode("SHE SELLS SEA SHELLS BY THE SEA SHORE", 16);
    test2 = new HuffmanCode("SHE SELLS SEA SHELLS BY THE SEA SHORE", 2);
  }

  @Test
  public void buildFreqMapTest() {
    test1.buildFreqMap();
    System.out.println(test1.getFreqMap().toString());
  }

  @Test
  public void buildMinHeapTest() {
    test1.buildFreqMap();
    test1.buildMinHeap();
    System.out.println(test1.getMinHeap().toString());
    while (!test1.getMinHeap().isEmpty()) {
      System.out.println(test1.getMinHeap().poll().toString());
    }
  }

  @Test
  public void getTrieTest() {
    test1.buildFreqMap();
    test1.buildMinHeap();
    test1.getTrie();
  }

  @Test
  public void encodeMapTest() {
    test1.buildFreqMap();
    test1.buildMinHeap();
    test1.getTrie();
    test1.encodeMap();
    System.out.println(test1.getEncodingMap().toString());

  }

  @Test
  public void decodeTest() {
    System.out.println(test1.decode(test1.encode()));
    //System.out.println(test1.decode(test1.encode()));
    //System.out.println(test1.decode("C16DAD6D6B7EB"));
  }


  @Test
  public void getTrie() {
    test1.buildFreqMap();
    test1.buildMinHeap();
    System.out.println(test1.getTrie().toString());
  }

  @Test
  public void encode() {
    System.out.println(test1.encode());

  }
}