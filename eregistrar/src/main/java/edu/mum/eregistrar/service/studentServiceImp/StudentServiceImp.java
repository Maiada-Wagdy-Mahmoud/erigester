package edu.mum.eregistrar.service.studentServiceImp;

import edu.mum.eregistrar.model.Student;
import edu.mum.eregistrar.reposotory.StudentReposotory;
import edu.mum.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentReposotory studentReposotory;
    @Override
    public Iterable<Student> getAllStudent() {
        return studentReposotory.findAll(Sort.by("studentNumber"));
    }

    @Override
    public Page<Student> getAllStudentPaged(int pageNo) {
        return studentReposotory.findAll(PageRequest.of(pageNo, 3, Sort.by("studentNumber")));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentReposotory.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentReposotory.findById(studentId).orElse(null);
    }

    @Override
    public void deleteStudentById(Long studentId) {

     studentReposotory.deleteById(studentId);
    }

    @Override
    public Optional<Student> findByStudentNumber(String StudentNumber) {
        return studentReposotory.findByStudentNumber(StudentNumber);

    }
}
