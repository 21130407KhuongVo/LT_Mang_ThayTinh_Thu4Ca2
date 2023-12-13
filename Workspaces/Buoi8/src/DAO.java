import java.util.ArrayList;
import java.util.List;

public class DAO {
    List<User> users = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    public DAO() {
        users.add(new User("21130407", "21130407"));
        users.add(new User("21130436", "21130436"));

        students.add(new Student("21130407", "Vo Nguyen Nhat Khuong", 9.8));
        students.add(new Student("21130436", "Le Phi Long", 9.6));
        students.add(new Student("21130560", "Nguyen Thi Thuy Thuy", 8.9));
        students.add(new Student("21130465", "Phan Ai Nhung", 8.1));
        students.add(new Student("21130437", "Nguyen Phi Long", 5.6));

    }

    public boolean findUname(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) return true;
        }
        return false;
    }

    public List<Student> findById(String id) {
        List<Student> result = new ArrayList<>();
        for (Student u : students) {
            if (u.getId().equals(id)) result.add(u);
        }
        return result;
    }

    public List<Student> findByName(String name){

        List<Student> result = new ArrayList<>();
        for (Student u : students) {
            if (u.getFullname().endsWith(name)) result.add(u);
        }
        return result;
    }
}

class User {
    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Student {
    private String id, fullname;
    private double score;

    public Student(String id, String fullname, double score) {
        this.id = id;
        this.fullname = fullname;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", score=" + score +
                '}';
    }
}
