package javacore3.files.saving;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void deleteFiles(List<File> fileList) {
        for (File file :
                fileList) {
            try {
                file.delete();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static GameProgress openprogress(String save) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(save);
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            gameProgress = (GameProgress) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return gameProgress;
        }
    }

    public static File savegames(String dir, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(dir)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            ex.getMessage();
        }
        return new File(dir);
    }

    public static void zip(File zipdir, List<File> savings) {
        try (ZipOutputStream zot = new ZipOutputStream(new FileOutputStream(zipdir));) {
            for (File zipFile :
                    savings) {
                FileInputStream fis = new FileInputStream(zipFile);
                ZipEntry entry = new ZipEntry(String.valueOf(zipFile));
                zot.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zot.write(buffer);
                zot.closeEntry();
                fis.close();
            }
        } catch (IOException ex) {
            ex.getMessage();

        }

    }

    public static void unZip(String zipdir, String dir) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipdir))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream(entry.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        GameProgress gameProgress = new GameProgress(100, 2, 1, 15.2);
        GameProgress gameProgress1 = new GameProgress(80, 4, 6, 65.2);
        GameProgress gameProgress2 = new GameProgress(20, 7, 18, 153.2);
        File save = savegames("D://Games/savegames/save.dat", gameProgress);
        File save1 = savegames("D://Games/savegames/save1.dat", gameProgress1);
        File save2 = savegames("D://Games/savegames/save2.dat", gameProgress2);
        List<File> savings = Arrays.asList(save, save1, save2);
        File zipdir = new File("D://Games/savegames/save.zip");
        zip(zipdir, savings);
        deleteFiles(savings);
        unZip("D://Games/savegames/save.zip", "D://Games/savegames");
        GameProgress gameProgressOpen = openprogress("D://Games/savegames/save.dat");
        System.out.println(gameProgressOpen;
    }
}
