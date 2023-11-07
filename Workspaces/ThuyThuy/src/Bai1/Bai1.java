package Bai1;

import java.io.File;

public class Bai1 {

    static boolean delete(String path) {
        File file = new File(path);
        if (file.exists() && file.canWrite()) {
            if (file.isFile()) {
                return file.delete();
            }
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    delete(f.getPath());
                }
            }
        }
        return false;
    }

    static void dirTree(String path) {
        File file = new File(path);
        if (file.exists() && file.canRead()) {
            System.out.println(file.getName());
            if (file.isDirectory()) {
                dirTreeHelper(file, "\t|---");
            }
        }
    }

    private static void dirTreeHelper(File file, String syntax) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(syntax + f.getName());
                dirTreeHelper(f, "\t" + syntax);
            }
        }
    }

    //    public static void eg(String...ex,String ext1) {
//    public static void eg(String... ex1) {
      public static void eg(String ext1,String...ex) {
        System.out.println("ext1: " + ext1);
        for (String e : ex) {
            System.out.println("ex: " + e);
        }
    }

    public static void main(String[] args) {
//        dirTree("/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/Test");
        eg(".txt", ".bat", ".java", ".bin", ".md");
    }

}
