package es.upm.pproject.miniproject.models;

import es.upm.pproject.miniproject.exceptions.CourseLimitReached;
import es.upm.pproject.miniproject.exceptions.StudentAlreadyEnrolled;
import es.upm.pproject.miniproject.exceptions.StudentNotEnrolled;

import java.util.List;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class Course {
	private static final int MAX_STUDENTS_SIZE = 50;

	private int id;
	private String name;
	private String coordinator;
	private SortedMap<Integer, Student> students;

	public Course(int id, String name, String coordinator) {
		this.id = id;
		this.name = name;
		this.coordinator = coordinator;
		this.students = new TreeMap<>();
	}

	public void addStudent(Student student) throws CourseLimitReached,StudentAlreadyEnrolled {
		// preconditions
		if (students.size() == MAX_STUDENTS_SIZE) {
			throw new CourseLimitReached();
		} else if (students.containsKey(student.getId())) {
			throw new StudentAlreadyEnrolled();
		}
		students.put(student.getId(), student);
	}

	public void removeStudent(Student student) throws StudentNotEnrolled {
		if (!students.containsKey(student.getId())) {
			throw new StudentNotEnrolled();
		}
		students.remove(student.getId());
	}

	public void clearCourse() {
		students.clear();
	}

	// Getters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCoordinatior() {
		return coordinator;
	}

	public  List<Student> getStudents() {
		return new ArrayList<>(students.values());
	}
	
}
