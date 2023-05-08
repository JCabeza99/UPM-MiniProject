# mini-project-pairs

Mini-project is an management application on students and courses regitrations. This project covers the basic functionality from creating new courses and students to adding and removing a student in a course.

## File hierarchy

The general overview of the project's file hierarchy is the following:

```
├── src
   ├── main
   │   ├── java
   │   │   └── es
   │   │       └── upm
   │   │           └── pproject
   │   │               └── miniproject
   │   │                   ├── controller
   │   │                   ├── exceptions
   │   │                   └── models
   │   └── resources
   └── test
       ├── java
       │   └── es
       │       └── upm
       │           └── pproject
       │               └── miniproject
       │                   └── controller
       └── resources
```

### Main

| package | summary |
| ------ | ------ |
| es.upm.pproject.miniproject.controller | This package is used for the implementation of all the logic that our project needs. It contains all the functionality that is aviable in our application and holds the data of the existing courses and students.|
| es.upm.pproject.miniproject.exceptions | In this package all specific exceptions needed to warn the user about the wrong usage of the application are specified. |
| es.upm.pproject.miniproject.models | Holds the structure that a course and a student should have|

### Test

| package | summary |
| ------ | ------ |
| es.upm.pproject.miniproject.controller | All our unit tests are specified here. The code coverage is measured with the ammout of code the tests cover. The main goal is to reach 100% code coverage |

## Methods overview

You can see here a brief explanation on how the methods work and some  pre-conditions that are checked in them.

- **newCourse:** Adds a new course into the courses structure, it requires a positive integer that will serve as an identifier for the course and two non-empty strings for the name and the coordinator's name. If any of these preconditions fails a BlankValueException will be thrown.

- **registerNewStudent:** Adds a new course into the courses structure, it requires a positive integer that will serve as an identifier for the course and two non-empty strings for the name and the student's email. If any of these preconditions fails a BlankValueException will be thrown. Also if the email's format is wrong(it does not contain a '@' character nor a '.' at the end) the function will throw a EmailFormatException.

- **addStudentToCourse:** Adds the given student's id to the given course id, fails if either the course or the student is not registered, the course has reached it's max limit of students or if the students has been added twice.

- **getStudentsFromCourse:** Retrieves the students from the given course id, fails if the course is not registered.

- **removeStudentFromCourse:** Removes the the given student's id from the given course, fails if either the course or the student is not registered and if the student is not enrolled in it. 

- **restartCourse:** Clears the students enrolled in a course, if the course is not registered a exception is thrown.

- **getStudents:** Retrieves the students registered in the system.

- **getCourses:** Retrives the courses registered in the system.