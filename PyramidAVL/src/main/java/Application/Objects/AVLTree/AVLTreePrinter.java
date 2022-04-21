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
                    inOrden(root, lines);
                    break;
                case 1:
                    preorden(root, lines);
                    break;
                default:
                    postOrden(root, lines);
            }
            // merge array result
            result = lines.stream().map(line -> line + "\n").reduce(result, String::concat);
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
    private void inOrden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            inOrden(current.getLeftChild(), lines);
            lines.add(String.format("\"%1$s\": \"%2$s\",", lines.size(), current.getData().toString()));
            inOrden(current.getRightChild(), lines);
        }
    }

    /**
     * Print all the tree with pre order
     *
     * @param current
     */
    private void preorden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            lines.add(String.format("\"%1$s\": \"%2$s\",", lines.size(), current.getData().toString()));
            preorden(current.getLeftChild(), lines);
            preorden(current.getRightChild(), lines);
        }
    }

    /**
     * Print all the tree with post order
     *
     * @param current
     */
    private void postOrden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            postOrden(current.getLeftChild(), lines);
            postOrden(current.getRightChild(), lines);
            lines.add(String.format("\"%1$s\": \"%2$s\",", lines.size(), current.getData().toString()));
        }
    }

}
