package Application.Objects.AVLTree;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class AVLTreePrinter<T> {

    public String getData(int type, AVLNode<T> root) {
        String result = "";
        ArrayList<String> lines = new ArrayList<>();
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
        // cast lines to single string
        // return lines.stream().map(line -> "<br>" + line).reduce(result, String::concat);
        return result;
    }

    //recorridos
    //recorrer in orden
    private void inOrden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            inOrden(current.getLeftChild(), lines);
            lines.add("I: " + current.getValue() + " D: " + current.getData().toString());
            System.out.println("I: " + current.getValue() + " D: " + current.getData().toString());
            inOrden(current.getRightChild(), lines);
        }
    }

    //recorrer en preorden
    private void preorden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            lines.add("I: " + current.getValue() + " D: " + current.getData().toString());
            preorden(current.getLeftChild(), lines);
            preorden(current.getRightChild(), lines);
        }
    }

    //recorrer postorden
    private void postOrden(AVLNode<T> current, ArrayList<String> lines) {
        if (current != null) {
            postOrden(current.getLeftChild(), lines);
            postOrden(current.getRightChild(), lines);
            lines.add("I: " + current.getValue() + " D: " + current.getData().toString());
        }
    }
}
