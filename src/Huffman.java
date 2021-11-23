import java.util.AbstractMap;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Huffman {
    private HuffmanNode root;
    private HashMap<Character, String> charMap;

    public Huffman() {
        charMap = new HashMap<>();
    }
    /*
    Steps:
    1. Count the character frequencies for each character and store in a sorted structure
    2. Let each character be stored in a HuffmanNode, containing the character, and it's frequency
    3. Take (and remove) the two last nodes (the least frequency) and make them child nodes to a new node
    4. Let the new node have a frequency that is the sum of its children's frequencies
    5. Insert the new node in the list
    6. Make sure the list is sorted on frequency
    7. Repeat until we only have one node left in the list
     */

    private void buildCodesRecursive(HuffmanNode currentNode, String code) {
        // Exit condition, both left and right are null
        // We are at a leaf node
        if(currentNode.getLeft() == null && currentNode.getRight() == null) {
            // print the character and the content of code
            charMap.put(currentNode.getCharacter(),code);
            return;
        }
        // Recursive calls
        buildCodesRecursive(currentNode.getLeft(), code + "0");
        buildCodesRecursive(currentNode.getRight(), code + "1");
    }

    private void printCodesRecursive(HuffmanNode currentNode, String code) {
        // Exit condition, both left and right are null
        // We are at a leaf node
        if(currentNode.getLeft() == null && currentNode.getRight() == null) {
            // print the character and the content of code
            System.out.println(currentNode.getCharacter() + " - " + code);
            return;
        }
        // Recursive calls
        printCodesRecursive(currentNode.getLeft(), code + "0");
        printCodesRecursive(currentNode.getRight(), code + "1");
    }

    public void printCodes() {
        printCodesRecursive(root, "");
    }


    private long setBit(long value, int pos) {
        pos--;
        return value | (1L << pos);

    }

    public AbstractMap.SimpleEntry<Long, Integer> compress(String text) {
        // Count character frequency using a hashmap. The key is the character, the value is the
        // frequency count
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for(char c : text.toCharArray()) {
            // If this is the first time we see this character, create a key with this character and the
            // value 1
            if(!freqMap.containsKey(c)) {
                freqMap.put(c, 1);
            }
            else{ // We have seen this character before, increase the value by one
                freqMap.put(c, freqMap.get(c) + 1 );
            }
        }

        // Create a priority queue to store our Huffman Nodes in
        PriorityQueue<HuffmanNode> freqQueue = new PriorityQueue<>(freqMap.size(), new HuffmanComparator());
        for(var k : freqMap.keySet()) {
            // Create nodes for all character-frequency pairs
            HuffmanNode node = new HuffmanNode(k, freqMap.get(k));
            // Add this node to the queue
            freqQueue.add(node);
        }

        // Loop until we only have one node in the queue
        while(freqQueue.size() > 1) {
            // Remove the two nodes with the least frequency
            HuffmanNode left = freqQueue.poll();
            HuffmanNode right = freqQueue.poll();
            // Create a new node with the sum of the two removed nodes frequencies
            HuffmanNode newNode = new HuffmanNode('-', left.getFrequency() + right.getFrequency());
            // Set one of the nodes to the left of the new node
            newNode.setLeft(left);
            // and the other to the right
            newNode.setRight(right);
            // Add the new node to the priority queue
            freqQueue.add(newNode);
        }
        // We have only one node in hte queue, that is our root
        root = freqQueue.poll();
        buildCodesRecursive(root, "");
        long code = 0;
        int pos = 1;
        for(char c : text.toCharArray()) {
            String pattern = charMap.get(c);
            for(char p : pattern.toCharArray()) {
                if(p == '1') {
                    code = setBit(code, pos);
                }
                pos++;
            }
        }
        return new AbstractMap.SimpleEntry<>(code, bitsNeeded(text));

    }

    private int bitsNeeded(String input) {
        int bitsNeeded = 0;
        for(char c : input.toCharArray()) {
            bitsNeeded += charMap.get(c).length();
        }
        return bitsNeeded;
    }


    private boolean isBitSet(long value, int pos) {
        return ((value >> pos - 1) & 1) == 1;
    }


    public String uncompress(long code, int bits) {
        int pos = 1;
        StringBuilder text = new StringBuilder();
        HuffmanNode currentNode = root;
        while(pos <= bits+1) {
            if(currentNode.getLeft() == null && currentNode.getRight() == null) {
                text.append(currentNode.getCharacter());
                currentNode = root;
            }
            else {
                if(isBitSet(code, pos)) {
                    currentNode = currentNode.getRight();
                }
                else {
                    currentNode = currentNode.getLeft();
                }
                pos++;
            }
        }
        return text.toString();
    }
}
