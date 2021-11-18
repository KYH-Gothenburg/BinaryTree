public class Main {
    public static void main(String[] args) {
        BinaryTree binTree = new BinaryTree();
        binTree.add(10);
        binTree.add(5);
        binTree.add(15);
        binTree.add(7);
        binTree.add(8);
        binTree.add(1);
        binTree.add(17);
        binTree.printInorder();
        System.out.println("**************");
        binTree.printPreOder();
        System.out.println("**************");
        binTree.printPostOder();
    }
}
