/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 *
 * @author jefemayoneso
 */
public class FileManager {

    private static final String GRAPHS_DIR = "tmp/graphviz";

    /**
     * Create all folders used int the program, this folders are relative to JAR
     * file
     *
     * @return
     */
    public boolean setup() {
        try {
            // delete all content if exist
            File dir = new File("tmp/");
            if (dir.exists()) {
                clearFolder(dir.toPath());
            }
            // custom dirs
            dir = new File(String.format("%1$s", GRAPHS_DIR)); // create P1 folder
            dir.mkdirs();
            return true;
        } catch (IOException e) {
            System.out.println("ex: " + e.getMessage());
            return false;
        }
    }

    private void clearFolder(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public static String getGRAPHS_DIR() {
        return GRAPHS_DIR;
    }

}
