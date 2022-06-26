package fr.celestgames.maze.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public static String[] readFileContent(String inputPath) {
        InputStream filePath = FileUtils.class.getResourceAsStream(inputPath);

        if (filePath != null) {
            try {
                InputStreamReader streamReader = new InputStreamReader(filePath, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader);

                int lines = (int) reader.lines().count();
                String[] content = new String[lines];

                for (int line = 0; line < lines; line++) {
                    content[line] = reader.readLine();
                }

                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static File readFile(String inputPath) {
        File tempFile = new File(inputPath);

        if (tempFile.exists() && tempFile.isFile() && tempFile.canRead() && tempFile.canRead()) {
            return tempFile;
        }

        return null;
    }

    public static File writeFile(String outputPath) {
        String[] slicedPath = outputPath.split("/");
        File tempFile = new File(outputPath);

        if (slicedPath.length > 1) {
            String path = "";
            for (int i = 0; i < slicedPath.length - 1; i++) {
                path += slicedPath[i] + "/";
                File temp = new File(path);

                System.out.println(path);

                if (!temp.exists()) {
                    if (temp.mkdir()) {
                        System.out.println("Le dossier " + path + " n'existe pas, création...");
                    }
                }
            }
        }

        if (!tempFile.exists()) {
            try {
                if (tempFile.createNewFile()) {
                    System.out.println("Fichier créé !");
                } else {
                    System.out.println("Fichier écrasé !");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tempFile;
    }
}