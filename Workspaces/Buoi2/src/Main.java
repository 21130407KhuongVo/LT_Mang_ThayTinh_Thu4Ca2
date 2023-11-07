import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static boolean fileCopy(String sFile, String destFile, boolean moved) {
        File source = new File(sFile);
        if (source.exists() && source.canRead()) {
            try {
                InputStream inputStream = new FileInputStream(sFile);
                OutputStream outputStream = new FileOutputStream(destFile);
                byte[] buffered = new byte[1024 * 10];
                int data;
                while ((data = inputStream.read(buffered)) != -1) {
                    outputStream.write(buffered, 0, data);
                }
                outputStream.close();
                inputStream.close();
                if (moved) {
                    return source.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void splitter(String path, int partSize) {
        try (InputStream is = new FileInputStream(path)) {

            byte[] buffer = new byte[partSize];
            int bytesRead;
            int partNumber = 1;

            while ((bytesRead = is.read(buffer)) != -1) {

                try (OutputStream os = new FileOutputStream(fileExt(path, partNumber))) {
                    os.write(buffer, 0, bytesRead);
                }

                partNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fileExt(String file, int order) {
        String ext = "";
        if (order < 10) ext = ".00" + order;
        else if (order < 100) ext = ".0" + order;
        else if (order < 1000) ext = "." + order;
        return file + ext;
    }

    public static void main(String[] args) throws IOException {
        String source = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/Test/LICENSE";
        splitter(source, 10000);
    }
}