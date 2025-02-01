package com.stud.mongoprj.service;

import com.stud.mongoprj.model.Address;
import com.stud.mongoprj.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void findAll() {
        List<Student> students = studentService.findAll();
        assertNotNull(students);
        assertFalse(students.isEmpty());
    }

    @Test
    void find() {
        String id = "679d308733082c1df32f6ad6";
        Student student = studentService.find(id);
        assertNotNull(student);

        String idNonExistent = "679d308733082c1df32f6ad0";
        Student studentNotExists = studentService.find(idNonExistent);
        assertNull(studentNotExists);
    }

    @Test
    void insertStudent() {
        Student student = new Student();
        student.setName("Student " + new Date());
        Address address = new Address("555 South Grant", "San Mateo", "95789", "CA");
        student.setAddress(address);

        Student studentInserted = studentService.insert(student);
        assertNotNull(studentInserted);
        assertNotNull(studentInserted.getId());
    }
}
