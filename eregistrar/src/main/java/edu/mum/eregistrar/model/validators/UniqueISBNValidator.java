package edu.mum.eregistrar.model.validators;

import edu.mum.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueISBNValidator implements ConstraintValidator<UniqueStudentNumber, String> {

    private StudentService studentService;

    public UniqueISBNValidator() {
    }

    @Autowired
    public UniqueISBNValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void initialize(UniqueStudentNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String studentNumber, ConstraintValidatorContext context) {
        boolean valid;
        if(studentService != null) {
            valid = (studentNumber != null && !studentService.findByStudentNumber(studentNumber).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
