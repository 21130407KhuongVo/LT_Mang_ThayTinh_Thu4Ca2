package Main;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Student {
    private int id;
    private String name;
    private int bYear;
    private double grade;

    public Student(int id, String name, int bYear, double grade) {
        this.id = id;
        this.name = name;
        this.bYear = bYear;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * write name with len X2 byte
     * 1 char = 2 byte
     * @param dos
     * @param len
     */
    public void writeFixedLengthName(DataOutput dos, int len){
        char c;
        for (int i = 0; i < len * 2; i++) {
           if (this.getName().length()<len){
               c=this.getName().charAt(i);
           }else{

           }
        }
    }

    /**
     *
     * @param dis
     * @param len
     */
    public String readFixedLengthNAme(DataInput dis, int len){

        return null;
    }


}
