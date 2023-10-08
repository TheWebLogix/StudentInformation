package com.example.studentinformation;

import com.example.studentinformation.Student.Student;
import com.example.studentinformation.Student.StudentController;
import com.example.studentinformation.Student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTest {
@Autowired
private StudentRepository repo;
@Test
public void listAll(){
Iterable<Student> students = repo.findAll();
    Assertions.assertThat(students).hasSizeGreaterThan(0);
    for (Student student: students){
        System.out.println(student);
    }
}
//@Test
//    public void updateQuery(){
//    Integer id = 14;
//    Student student = repo.findById(id).get();
//
//    student.setFirstName("changed");
//    student.setLastName("changedM");
//    student.setEmail("changed@ch");
//    student.setDepartment("jjch");
//    repo.save(student);
//}
}
