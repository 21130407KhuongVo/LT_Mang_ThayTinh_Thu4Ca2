import java.io.File;
import java.util.List;

public class Main {

    /**
     * remove file or directory with path;
     *
     * @param path path absolute to destination
     * @return <ul>
     * <li><b>True</b> if command executed</li>
     * <li><b>False</b> if command didn't execute</li>
     * </ul>
     */
    public static boolean delete(String path) {
        File file = new File(path);

        if (file.exists() && file.canWrite()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        delete(f.getPath());
                    }
                }
            }
            return file.delete();
        }
        return false;
    }

    /**
     * just delete File in path
     *
     * @param path path absolute to destination
     * @return
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);

        if (file.exists() && file.canWrite()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        deleteFile(f.getPath());
                    }
                }
            } else file.delete();
            return true;
        }
        return false;
    }

    public static boolean findFirst(String path, String pattern) {
        return false;
    }

    /**
     * print out a directory tree from path
     *
     * @param path
     */
    public static void dirTree(String path) {
        File file = new File(path);
        if (file.exists() && file.canRead()) {
            System.out.println(file.getName());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        if (f.isDirectory()) recursionDirTree(f, "\t");
                    }
                    for (File f : files) {
                        if (f.isFile()) recursionDirTree(f, "\t");
                    }
                }
            }
        }
    }

    private static void recursionDirTree(File file, String syntac) {
        if (file.canRead()) {
            System.out.println(syntac + "|---" + file.getName());
            if (file.isDirectory()) {
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File f : files) {
                            recursionDirTree(f, "\t" + syntac);
                        }
                    }
                }
            }
        }
    }
//
//    public static void dirStat(String path){
//        File file = new File(path);
//        if (file.exists() && file.canRead()){
//            System.out.println(file.getName()+" ("+file.length()/(Math.pow(1024,1.0))+"KB)");
//            if (file.isDirectory()) {
//                File[] files = file.listFiles();
//                if (files != null) {
//                    for (File f : files) {
//                        recursionDirStat(f, "\t");
//                    }
//                }
//            }
//        }
//    }
//
//    private static void recursionDirStat(File file, String syntac){
//        if (file.canRead()) {
//            System.out.println(syntac + "|---" + file.getName()+" ("+file.length()/(Math.pow(1024,1.0))+"KB)");
//            if (file.isDirectory()) {
//                if (file.isDirectory()) {
//                    File[] files = file.listFiles();
//                    if (files != null) {
//                        for (File f : files) {
//                            recursionDirStat(f, "\t" + syntac);
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static void findAll(String path, String... ext) {
        File file = new File(path);
        if (file.exists() && file.canRead() && file.canExecute()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        findAll(f.getPath(), ext);
                    }
                }
            } else {
                if (contains(file, ext)) System.out.println(file.getAbsolutePath());
            }
        }
    }

    private static boolean contains(File file, String... ext) {
        if (file.exists() && file.canRead()) {
            String name = file.getName();
            int index = name.lastIndexOf('.');
            if (index >= 0) {
                for (String e : ext) {
                    if (name.substring(name.lastIndexOf('.')).equals(e)) return true;
                }
            }
        }
        return false;
    }

    public static boolean deleteAll(String path, String... ext) {
        File file = new File(path);

        if (file.exists() && file.canRead() && file.canExecute()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        deleteAll(f.getPath(), ext);
                    }
                }
            } else {
                if (contains(file, ext)) {
                    file.delete();
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String path = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/Test";
//        System.out.println(removeFile(path));
        dirTree(path);
//        dirStat(path);
        System.out.println();
        findAll(path, ".txt", ".pdf", ".docx", ".md", ".bitmap", ".html");
        System.out.println();
        System.out.println(deleteAll(path, ".txt", ".pdf", ".docx", ".md", ".bitmap", ".html"));
    }
}