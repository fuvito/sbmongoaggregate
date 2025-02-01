package com.stud.mongoprj.repo;

import com.stud.mongoprj.modelDTO.StudentCountByGrade;
import com.stud.mongoprj.modelDTO.StudentCountByState;

import java.util.List;

public interface StudentRepoAggregate {
    List<StudentCountByState> getStudentCountByState();
    List<StudentCountByGrade> getStudentCountByGrade();
}
