import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<File> listFile(File file) {
        List<File> list = new ArrayList<File>();
        if (file.exists() && file.isDirectory() && file.canRead()) {
            for (File f : file.listFiles()) {
                if (f.isFile()) list.add(f);
            }
            return list;
        }
        return null;
    }

    public static void pack(String folder, String packedFile) {
        File file = new File(folder);
        if (file.exists() && file.isDirectory() && file.canRead()) {
            List<File> list = listFile(file);
            if (list != null) {
                try {
                    RandomAccessFile raf = new RandomAccessFile(packedFile + "test.pack", "rw");

                    long pos = 0;
                    long[] positions = new long[list.size()];
                    // tao phan HEAD cho file
                    for (int i = 0; i < list.size(); i++) {
                        raf.writeUTF(list.get(i).getName());
                        positions[i] = raf.getFilePointer();
                        raf.writeLong(pos); // postion cua file dang bi gan SAI
                        raf.writeLong(list.get(i).length());
                    }
                    long index = 0;
                    byte[] buffered = new byte[1024 * list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("-" + list.get(i).getName());
                        index = raf.getFilePointer();
                        raf.seek(positions[i]);
                        raf.writeLong(index);
                        raf.seek(index);
                        InputStream inputStream = new FileInputStream(list.get(i));
                        int data;
                        while ((data = inputStream.read(buffered)) != -1) {
                            raf.write(buffered);
                        }
                        inputStream.close();
                    }
                    raf.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean unPack(String sourceFile, String extractTo, String fileName) {
        File sFile = new File(sourceFile), dFile = new File(extractTo);
        if (sFile.exists() && dFile.exists() && sFile.getName().lastIndexOf(".pack") > -1 && sFile.canExecute() && dFile.isDirectory() && dFile.canWrite()) {
            try {
                RandomAccessFile raf = new RandomAccessFile(extractTo, "rw");

                InputStream ins = new FileInputStream(sourceFile);



                raf.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String sFile = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/Test", dFile = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/";
        pack(sFile, dFile);
    }
}