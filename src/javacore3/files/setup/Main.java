package javacore3.files.setup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {
    public static String getLog(File file) {
        Date date = new Date();
        String log = file.getName() + " successful created ! " + date.toString();
        return log;
    }

    public static void main(String[] args) {
        File dir1 = new File("D://Games");
        File src = new File(dir1, "src");
        File res = new File(dir1, "res");
        File saveGames = new File(dir1, "savegames");
        File temp = new File(dir1, "temp");
        File main = new File(src, "main");
        File test = new File(src, "test");
        File drawables = new File(res, "drawables");
        File icons = new File(res, "icons");
        File vectors = new File(res, "vectors");
        File mainFile = new File(main, "Main.java");
        File utils = new File(main, "Utils.java");
        File tempLogs = new File(temp, "temp.txt");
        StringBuilder logs = new StringBuilder();
        File[] catalogs = {dir1, src, res, saveGames, temp, main, test, drawables, icons, vectors};
        File[] filesJava = {mainFile, utils,tempLogs};
        try {
            for (File catalog :
            catalogs) {
                if (!catalog.exists()) {
                    catalog.mkdir();
                    logs.append(getLog(catalog));
                    logs.append("\n");

                }
            }
            for (File file :
                    filesJava) {
                if (!file.exists()) {
                    file.createNewFile();
                    logs.append(getLog(file));
                    logs.append("\n");

                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (FileWriter fileWriter = new FileWriter(tempLogs);) {
            fileWriter.write(String.valueOf(logs));
        } catch(IOException r){
            System.out.println(r.getMessage());
        }

    }

}
