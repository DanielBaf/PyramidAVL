/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Utilities.Graphics;

import Application.Utilities.FileManager;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.InputStream;

/**
 *
 * @author jefemayoneso
 */
public class GraphvizGen {

    public void testGraph() {
        FileManager fm = new FileManager();
        fm.setup();

        // color path = src/main/resources/static/graphvizData/color.dot
        try (InputStream dot = getClass().getResourceAsStream("/static/graphvizData/color.dot")) {
            MutableGraph g = new Parser().read(dot);
            Graphviz.fromGraph(g).width(1500).render(Format.PNG).toFile(new File(FileManager.getGRAPHS_DIR() + "/graph.png"));

            g.graphAttrs()
                    .add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90))
                    .nodeAttrs().add(Color.WHITE.fill())
                    .nodes().forEach(node
                            -> node.add(
                            Color.named(node.name().toString()),
                            Style.lineWidth(4), Style.FILLED));
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File(FileManager.getGRAPHS_DIR() + "/graph.png"));
        } catch (Exception e) {
            System.out.println("Error creating graph: " + e.getMessage());
        }
    }

    public void customGraph() {
    }
}
