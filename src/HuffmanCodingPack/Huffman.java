package HuffmanCodingPack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node {
    private int freq;
    private char ch;

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    Node left;
    Node right;

}

public class Huffman {

    static void getCodes(Node root, String s, Map<Character, String> code) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            code.put(root.getCh(), s);
        }

        getCodes(root.left, s + "0", code);
        getCodes(root.right, s + "1", code);
    }

    static void buildHuffmanTree(String string) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            // Init every key to 0, so we can increase it by 1 later.
            if (!freq.containsKey(string.charAt(i))) {
                freq.put(string.charAt(i), 0);
            }
            // If we get a key that is already present than increase it's value by 1
            freq.put(string.charAt(i), freq.get(string.charAt(i)) + 1);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFreq));

        // Add a leaf nodes to priority queue
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            Node node = new Node();
            node.setCh(entry.getKey());
            node.setFreq(entry.getValue());
            node.left = null;
            node.right = null;
            queue.add(node);
        }

        Node root;
        while (queue.size() > 1) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            Node node = new Node();

            node.setCh('\0');
            assert node2 != null;
            int sum = node1.getFreq() + node2.getFreq();
            node.setFreq(sum);
            node.left = node1;
            node.right = node2;

            queue.add(node);
        }

        root = queue.peek();

        Map<Character, String> code = new HashMap<>();
        getCodes(root, "", code);

        System.out.println("Huffman codes are: \n");
        for (Map.Entry<Character, String> entry : code.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        int variableTotal = 0;
        int countVar = -1;
        int freqCount = 0;
        for (Map.Entry<Character, String> entry : code.entrySet()) {
            int x = freq.get(entry.getKey());
            freqCount += x;
            int y = entry.getValue().length();
            variableTotal += x * y;
            countVar++;
        }

        if (countVar >= 0) {
            if (countVar == 0) {
                countVar = 1;
            }
            System.out.println();
            int n = (int) (Math.log(countVar) / Math.log(2));
            int fixedTotal = freqCount * (n + 1);
            System.out.println("Variable length: " + variableTotal + " Fixed length : " + fixedTotal);
            double percentSaved = (double) (fixedTotal - variableTotal) * 100 / fixedTotal;
            System.out.println("Percentage saved here is: " + String.format("%.2f", percentSaved) + "%");
        }

    }

    public static void main(String[] args) {
        buildHuffmanTree("this string will be coded into binary string using huffman algorithm.");
    }
}