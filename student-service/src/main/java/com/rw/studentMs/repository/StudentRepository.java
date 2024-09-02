package com.rw.studentMs.repository;

import com.rw.studentMs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("select s from Student s where s.email = :email")
    Student findStudentByEmail(String email);
}
