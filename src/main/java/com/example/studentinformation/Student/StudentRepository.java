package com.example.studentinformation.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from student_information2 where first_name like %:keyword% ", nativeQuery = true)
    List<Student> findByKeyword(@Param("keyword") String keyword);
}
