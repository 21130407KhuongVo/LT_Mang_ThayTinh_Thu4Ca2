package Model;

import java.sql.Date;

public class Student {
	private String id, name;
	private Date birthday;
	private double score;

	public Student(String id, String name, Date birthday, double score) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.score = score;
	}

	public Student() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", score=" + score + "]";
	}

}
