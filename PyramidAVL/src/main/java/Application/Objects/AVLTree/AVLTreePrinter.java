package Application.Objects.AVLTree;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreePrinter<T> {

    /**
     * Public method to get the tree info as string
     *
     * @param type 1 = inorder, 2 = preorder, other = postorder
     * @param root where to start printing
     * @return
     */
    public String getData(int type, AVLNode<T> root) {
        ArrayList<String> lines = new ArrayList<>();
        String result = "";
        try {

            switch (type) {
                case 0:
                    inOrder(root, lines);
                    break;
                case 1:
                    preOrder(root, lines);
                    break;
                default:
                    postOrder(root, lines);
            }
            // merge array result
            result = lines.stream().map(line -> "\t" + line + "\n").reduce(result, String::concat);
        } catch (Exception e) {
            System.out.println("ERROR printing tree: " + e.getMessage());
        }
        return result;
    }

    /**
     * Print all the tree with in order
     *
     * @param current
     */
    private void inOrder(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            inOrder(current.getLeftChild(), lines);
            lines.add(String.format("\"%1$s\": \"%2$s\",", lines.size(), current.getData().toString()));
            inOrder(current.getRightChild(), lines);
        }
    }

    /**
     * Print all the tree with pre order
     *
     * @param current
     */
    private void preOrder(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            lines.add(String.format("\"%1$s\": \"%2$s\",", lines.size(), current.getData().toString()));
            preOrder(current.getLeftChild(), lines);
            preOrder(current.getRightChild(), lines);
        }
    }

    /**
     * Print all the tree with post order
     *
     * @param current
     */
    private void postOrder(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            postOrder(current.getLeftChild(), lines);
            postOrder(current.getRightChild(), lines);
            lines.add(String.format("\"%1$s\": \"%2$s\",", lines.size(), current.getData().toString()));
        }
    }

}
