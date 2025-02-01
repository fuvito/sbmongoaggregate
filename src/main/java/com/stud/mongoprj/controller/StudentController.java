package com.stud.mongoprj.controller;

import com.stud.mongoprj.model.Student;
import com.stud.mongoprj.modelDTO.StudentCountByGrade;
import com.stud.mongoprj.modelDTO.StudentCountByState;
import com.stud.mongoprj.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        Student student;
        try {
            student = studentService.find(id);
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        if (student == null) {
            String errorMsg = String.format("Student could not be found with id %s", id);
            LOGGER.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMsg);
        }
        return student;
    }

    @GetMapping("/countsbystate")
    public List<StudentCountByState> getStudentCountsByState() {
        try {
            return studentService.findStudentCountsByState();
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/countsbygrade")
    public List<StudentCountByGrade> getStudentCountsByGrade() {
        try {
            return studentService.findStudentCountsByGrade();
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        try {
            return studentService.insert(student);
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Student not inserted: " + e.getMessage());
        }
    }
}
