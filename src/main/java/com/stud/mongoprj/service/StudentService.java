package com.stud.mongoprj.service;

import com.stud.mongoprj.model.Student;
import com.stud.mongoprj.modelDTO.StudentCountByGrade;
import com.stud.mongoprj.modelDTO.StudentCountByState;
import com.stud.mongoprj.repo.StudentRepo;
import com.stud.mongoprj.repo.StudentRepoAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepo studentRepo;
    private final StudentRepoAggregate studentRepoAggregate;

    public StudentService(StudentRepo studentRepo, StudentRepoAggregate studentRepoAggregate) {
        this.studentRepo = studentRepo;
        this.studentRepoAggregate = studentRepoAggregate;
    }

    public Student find(String id) {
        LOGGER.info("find: id: " + id);
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> findAll() {
        LOGGER.info("findAll");
        return studentRepo.findAll();
    }

    public Student insert(Student student) {
        LOGGER.info("insert: " + student);
        return studentRepo.insert(student);
    }

    public List<StudentCountByState> findStudentCountsByState() {
        return studentRepoAggregate.getStudentCountByState();
    }

    public List<StudentCountByGrade> findStudentCountsByGrade() {
        return studentRepoAggregate.getStudentCountByGrade();
    }

}
