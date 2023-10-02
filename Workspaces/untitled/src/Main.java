import java.io.File;

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

    public static boolean findFirst(String path, String pattern){
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
                        recursion(f, "\t");
                    }
                }
            }
        }
    }

    private static void recursion(File file, String syntac) {
        if (file.canRead()) {
            System.out.println(syntac + "|---" + file.getName());
            if (file.isDirectory()) {
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File f : files) {
                            recursion(f, "\t" + syntac);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String path = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/Test";
//        System.out.println(removeFile(path));
        dirTree(path);
    }
}