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
    public String print(int type, AVLNode<T> root) {
        ArrayList<String> lines = new ArrayList<>();
        String result = "";
        try {

            switch (type) {
                case 0:
                    lines.add("<br> ---- INORDERD ----");
                    inOrden(root, lines);
                    break;
                case 1:
                    lines.add("<br>---- PREORDER ----");
                    preorden(root, lines);
                    break;
                default:
                    lines.add("<br>---- POSTORDER ----");
                    postOrden(root, lines);
            }
            // merge array result
            result = lines.stream().map(line -> "<br>" + line).reduce(result, String::concat);
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
            lines.add("NODE:<br>   Index=" + current.getValue() + " ef=" + current.getEf() + "   data=" + current.getData().toString() + "<br>   children=[<br>   " + current.getLeftChild() + "<br>   " + current.getRightChild() + " ]<br><br>");
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
            lines.add("NODE:<br>   Index=" + current.getValue() + " ef=" + current.getEf() + "   data=" + current.getData().toString() + "<br>   children=[<br>   " + current.getLeftChild() + "<br>   " + current.getRightChild() + " ]<br><br>");
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
            lines.add("NODE:<br>   Index=" + current.getValue() + " ef=" + current.getEf() + "   data=" + current.getData().toString() + "<br>   children=[<br>   " + current.getLeftChild() + "<br>   " + current.getRightChild() + " ]<br><br>");
        }
    }

}
