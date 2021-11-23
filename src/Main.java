import java.util.AbstractMap;

public class Main {
    public static void main(String[] args) {
        //String input = "alla gillar glass";
        String input = "Huffman är kul";
        Huffman huffman = new Huffman();
        AbstractMap.SimpleEntry<Long, Integer> result = huffman.compress(input);
        long code = result.getKey();
        int bits = result.getValue();
        String text = huffman.uncompress(code, bits);
        System.out.println(text);
        System.out.println("Compression rate: " + input.length() * 8 + " bits uncompressed, " + bits + " bits compressed");
        //huffman.printCodes();
//        BinaryTree binTree = new BinaryTree();
//        binTree.add(10);
//        binTree.add(5);
//        binTree.add(15);
//        binTree.add(7);
//        binTree.add(8);
//        binTree.add(1);
//        binTree.add(17);
//        binTree.printInorder();
//        System.out.println("**************");
//        binTree.printPreOder();
//        System.out.println("**************");
//        binTree.printPostOder();
//        System.out.println("**************");
//        System.out.println(binTree.isInTree(15));
//        System.out.println(binTree.isInTree(3));
    }
}
