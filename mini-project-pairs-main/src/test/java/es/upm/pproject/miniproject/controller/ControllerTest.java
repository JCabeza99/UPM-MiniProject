package es.upm.pproject.miniproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.pproject.miniproject.exceptions.BlankValueException;
import es.upm.pproject.miniproject.exceptions.CourseLimitReached;
import es.upm.pproject.miniproject.exceptions.CourseNotRegistered;
import es.upm.pproject.miniproject.exceptions.EmailFormatException;
import es.upm.pproject.miniproject.exceptions.StudentAlreadyEnrolled;
import es.upm.pproject.miniproject.exceptions.StudentNotEnrolled;
import es.upm.pproject.miniproject.exceptions.StudentNotRegistered;
import es.upm.pproject.miniproject.models.Course;
import es.upm.pproject.miniproject.models.Student;

class ControllerTest {

	private Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new Controller();
	}

	@Test
	void testNewCourse_OK() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			ArrayList<Course> courses = controller.getCourses();
			Course course = courses.get(0);
			assertEquals(1, course.getId());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testNewCourse_NOK_id() {
		assertThrows(BlankValueException.class, () -> controller.newCourse(0, "PPROJECT", "Guillermo Roman"));
	}

	@Test
	void testNewCourse_NOK_name() {
		assertThrows(BlankValueException.class, () -> controller.newCourse(1, "", "Guillermo Roman"));
	}

	@Test
	void testNewCourse_NOK_coordinator() {
		assertThrows(BlankValueException.class, () -> controller.newCourse(1, "PPROJECT", ""));
	}

	@Test
	void testRegisterNewStudent_OK() {
		try {
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			ArrayList<Student> students = controller.getStudents();
			Student student = students.get(0);
			assertEquals(1, student.getId());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testRegisterNewStudent_NOK_id() {
		assertThrows(BlankValueException.class,
				() -> controller.registerNewStudent(0, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es"));
	}

	@Test
	void testRegisterNewStudent_NOK_name() {
		assertThrows(BlankValueException.class,
				() -> controller.registerNewStudent(1, "", "jaime.cabeza.expolio@alumnos.upm.es"));
	}

	@Test
	void testRegisterNewStudent_NOK_email_blank() {
		assertThrows(BlankValueException.class,
				() -> controller.registerNewStudent(1, "Jaime", ""));
	}

	@Test
	void testRegisterNewStudent_NOK_email_format_1() {
		assertThrows(EmailFormatException.class,
				() -> controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio"));
	}

	@Test
	void testRegisterNewStudent_NOK_email_format_2() {
		assertThrows(EmailFormatException.class,
				() -> controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm."));
	}

	@Test
	void testAddStudentToCourse_OK() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			controller.addStudentToCourse(1, 1);
			Student student = controller.getStudentsFromCourse(1).get(0);
			assertEquals(1, student.getId());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testAddStudentToCourse_NOK_NoStudent() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			assertThrows(StudentNotRegistered.class, () -> controller.addStudentToCourse(1, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testAddStudentToCourse_NOK_NoCourse() {
		try {
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			assertThrows(CourseNotRegistered.class, () -> controller.addStudentToCourse(1, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testAddStudentToCourse_NOK_MaxLimitReached() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			// add more than 50 students
			for (int i = 1; i < 51; i++) {
				controller.registerNewStudent(i, "Pepito", "pepito.cabeza@alumnos.upm.es");
				controller.addStudentToCourse(i, 1);
			}
			controller.registerNewStudent(51, "Pepito", "pepito.cabeza@alumnos.upm.es");
			assertThrows(CourseLimitReached.class, () -> controller.addStudentToCourse(50, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testAddStudentToCourse_NOK_AlreadyEnrolled() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			controller.addStudentToCourse(1, 1);
			assertThrows(StudentAlreadyEnrolled.class, () -> controller.addStudentToCourse(1, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testGetStudentsFromCourse_OK() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			controller.addStudentToCourse(1, 1);
			ArrayList<Student> students = new ArrayList<>(controller.getStudentsFromCourse(1));
			assertEquals(1, students.size());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testGetStudentsFromCourse_NOK() {
		assertThrows(CourseNotRegistered.class, () -> controller.getStudentsFromCourse(1));
	}

	@Test
	void testRemoveStudentFromCourse_OK() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			controller.addStudentToCourse(1, 1);
			controller.removeStudentFromCourse(1, 1);
			ArrayList<Student> students = new ArrayList<>(controller.getStudentsFromCourse(1));
			assertTrue(students.isEmpty());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testRemoveStudentFromCourse_NOK_NoStudent() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			assertThrows(StudentNotRegistered.class, () -> controller.removeStudentFromCourse(1, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testRemoveStudentFromCourse_NOK_NoCourse() {
		try {
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			assertThrows(CourseNotRegistered.class, () -> controller.removeStudentFromCourse(1, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testRemoveStudentFromCourse_NOK_StudentNotEnrolled() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			assertThrows(StudentNotEnrolled.class, () -> controller.removeStudentFromCourse(1, 1));
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testRestartCourse_OK() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			controller.addStudentToCourse(1, 1);
			controller.restartCourse(1);
			ArrayList<Student> students = new ArrayList<>(controller.getStudentsFromCourse(1));
			assertEquals(0, students.size());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testRestartCourse_NOK() {
		assertThrows(CourseNotRegistered.class, () -> controller.restartCourse(1));
	}


	@Test
	void testGetRegisteredStudents() {
		try {
			controller.registerNewStudent(1, "Jaime", "jaime.cabeza.expolio@alumnos.upm.es");
			ArrayList<Student> students = controller.getStudents();
			assertEquals(1, students.size());
		} catch (Exception e) {
			fail(e);
		}
	}

	@Test
	void testGetCourses() {
		try {
			controller.newCourse(1, "PPROJECT", "Guillermo Roman");
			ArrayList<Course> courses = controller.getCourses();
			assertEquals(1, courses.size());
		} catch (Exception e) {
			fail(e);
		}
	}

}
