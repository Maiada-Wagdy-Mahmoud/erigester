package edu.mum.eregistrar.service;

import edu.mum.eregistrar.model.Student;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StudentService {
    public abstract Iterable<Student> getAllStudent();
    public abstract Page<Student> getAllStudentPaged(int pageNo);
    public abstract Student saveStudent(Student student);
    public abstract Student getStudentById(Long StudentId);
    public abstract void deleteStudentById(Long StudentId);
    public abstract Optional<Student> findByStudentNumber(String StudentNumber);
}
