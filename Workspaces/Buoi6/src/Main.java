import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Student> loadData(String sv, String diem, String charset) {

        List<Student> result = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(sv));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("\t");
                Student student = new Student();
                student.setId(arr[0]);
                student.setName(arr[1]);
                student.setbYear(Integer.parseInt(arr[2]));

                // setGrade
                search(diem,student);
//                System.out.println(student.toString());
                result.add(student);
            }

            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private static void search(String path, Student student) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = bufferedReader.readLine()) !=null){
                String[]arr = line.split("\t");
                if (arr[0].equals(student.getId())){
                    int count =0; double value=0;
                    for (int i = 1; i < arr.length; i++) {
                        value += Double.parseDouble(arr[i]);
                        count++;
                    }
                    student.setGrade(value/count);
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void export(String )

    public static void main(String[] args) {
        String sv = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/sv.txt", diem = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/diem.txt";
        System.out.println(loadData(sv,diem, "UTF-8"));
    }
}