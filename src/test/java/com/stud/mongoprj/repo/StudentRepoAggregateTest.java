package com.stud.mongoprj.repo;

import com.stud.mongoprj.modelDTO.StudentCountByGrade;
import com.stud.mongoprj.modelDTO.StudentCountByState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StudentRepoAggregateTest {

    private final StudentRepoAggregate studentRepoAggregate;

    public StudentRepoAggregateTest(@Autowired StudentRepoAggregate studentRepoAggregate) {
        this.studentRepoAggregate = studentRepoAggregate;
    }

    @Test
    void getStudentCountByState() {
        List<StudentCountByState> studentCounts = studentRepoAggregate.getStudentCountByState();
        assertNotNull(studentCounts);
        assertFalse(studentCounts.isEmpty());
    }

    @Test
    void getStudentCountByGrade() {
        List<StudentCountByGrade> studentCounts = studentRepoAggregate.getStudentCountByGrade();
        assertNotNull(studentCounts);
        assertFalse(studentCounts.isEmpty());
    }
}
