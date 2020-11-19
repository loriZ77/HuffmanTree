import java.io.*;
import java.util.Scanner;

public class Driver {

  /**
   * Read and return strings in a selected file.
   *
   * @param path file path String
   * @return String
   */
  public static String readFile(String path) {
    String read = new String();
    try {
      File f = new File(path);
      InputStream input = new FileInputStream(f);
      InputStreamReader reader = new InputStreamReader(input);
      Scanner in = new Scanner(reader);
      StringBuilder sb = new StringBuilder();
      while (in.hasNextLine()) {
        sb.append(in.nextLine());
      }
      read = sb.toString();
      //System.out.println(read);
      input.close();

    } catch (IOException e) {
      System.out.println("Exception!");
      e.printStackTrace();
    }
    return read;
  }

  /**
   * Write content at selected path.
   *
   * @param path    String file path
   * @param content String
   */
  public static void writeFile(String path, String content) {
    try {
      OutputStream f = new FileOutputStream(path);
      OutputStreamWriter writer = new OutputStreamWriter(f);
      writer.append(content);
      writer.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * read messages from either the keyboard or a file
   * write messages to the screen or to a file
   *
   * @param args args
   */
  public static void main(String[] args) throws IOException {
    String source;
    int base;
    Scanner scan = new Scanner(System.in);
    System.out.println("Select the options to read Source Message.\n" +
        "Press 1 for Keyboard\n" +
        "Press 2 for File");
    int option = scan.nextInt();

    //Read messages from keyboard.
    //Write messages to the screen.
    if (option == 1) {
      System.out.println("Enter source message: ");
      if (scan.hasNextLine()) {
        String str2 = scan.nextLine();
      }
      source = scan.nextLine();
      System.out.println("Enter an integer as base for scale: ");
      base = scan.nextInt();
      HuffmanCode keyboard = new HuffmanCode(source, base);
      System.out.println("Encoding...\n" + keyboard.encode());
      System.out.println("Decoding...\n" + keyboard.decode(keyboard.encode()));

    } else if (option == 2) { //read and write in files
      source = readFile("/Users/zhouyuyan/Desktop/pdp/HW4/test.txt");
      System.out.println("Enter an integer as base for scale: ");
      base = scan.nextInt();
      HuffmanCode file = new HuffmanCode(source, base);
      // write encoding to a new file
      writeFile("/Users/zhouyuyan/Desktop/pdp/HW4/encode1.txt", file.encode());
      System.out.println("Writing encoding to file.........\nDone!");
      String decoding = file.decode(readFile("/Users/zhouyuyan/Desktop/pdp/HW4/encode1.txt"));
      writeFile("/Users/zhouyuyan/Desktop/pdp/HW4/decode1.txt", decoding);
      System.out.println("Writing decoding to file.........\nDone!");

    } else {
      System.out.println("Invalid input!");
    }

  }
}
