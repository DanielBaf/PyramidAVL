/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Utilities.Graphics;

import Application.Objects.AVLTree.AVLNode;
import Application.Utilities.FileManager;
import Application.Web.Exceptions.ApiRequestException;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;

/**
 *
 * @author jefemayoneso
 */
public class GraphvizGen<T> {

    /**
     * Generate an .png image into a relative path
     *
     * @param root the root node of an existing AVL Tree
     */
    public void getDotFileFromTree(AVLNode<T> root) {
        try {
            // data
            String header = "digraph G {\n";
            ArrayList<String> nodesDec = new ArrayList<>();
            ArrayList<String> nodesPointers = new ArrayList<>();
            FileManager fm = new FileManager();
            File fileGraph = new File(FileManager.getGRAPHS_DIR() + "/graph.png");
            fm.setup();
            // update middle data
            graphFromTree(root, nodesDec, nodesPointers);
            // gen final String
            header = nodesDec.stream().map(string -> "\t" + string + "\n").reduce(header, String::concat);
            header = nodesPointers.stream().map(string -> "\t" + string + "\n").reduce(header, String::concat);
            header += "}";
            // create graph
            Graphviz.fromString(header).render(Format.PNG).toFile(fileGraph);
            throw new ApiRequestException(fileGraph.getAbsolutePath(), HttpStatus.OK);
        } catch (IOException ex) {
            throw new ApiRequestException("Error creando grafico " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Generate a temporal .dot file text, used later to compile a file
     *
     * @param current current node, re cursive calls
     * @param nodesDec nodes section, ex nodeN [label="label"];
     * @param nodesPointers nodes pointer section, ex nodeN -> nodeN2;
     */
    private void graphFromTree(AVLNode<T> current, ArrayList<String> nodesDec, ArrayList<String> nodesPointers) {
        // check current is not null
        if (current != null) {
            nodesDec.add(String.format("node%1$x [label=\"%2$s\"];", current.getValue(), current.getData())); // add node to list
            // add node pointers, only when children aren't null
            if (current.getLeftChild() != null) {
                nodesPointers.add(String.format("node%1$x -> node%2$x", current.getValue(), current.getLeftChild().getValue()));
            }
            if (current.getRightChild() != null) {
                nodesPointers.add(String.format("node%1$x -> node%2$x", current.getValue(), current.getRightChild().getValue()));
            }
            // recursively call
            graphFromTree(current.getLeftChild(), nodesDec, nodesPointers);
            graphFromTree(current.getRightChild(), nodesDec, nodesPointers);
        }
    }
}
