package com.stud.mongoprj.repo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.stud.mongoprj.model.Student;
import com.stud.mongoprj.modelDTO.StudentCountByGrade;
import com.stud.mongoprj.modelDTO.StudentCountByState;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class StudentRepoAggregateImpl implements StudentRepoAggregate {
    private final MongoTemplate mongoTemplate;
    private final MongoClient mongoclient;
    private MongoCollection<Student> studentsCollection;

    public StudentRepoAggregateImpl(MongoTemplate mongoTemplate, MongoClient mongoclient) {
        this.mongoTemplate = mongoTemplate;
        this.mongoclient = mongoclient;
    }

    @PostConstruct
    void init() {
        studentsCollection = mongoclient.getDatabase("appdb").getCollection("students", Student.class);
    }

    @Override
    public List<StudentCountByState> getStudentCountByState() {
        GroupOperation groupStage = group("address.state").count().as("count");
        ProjectionOperation projectStage = project("address.state", "count");
        SortOperation sortStage = sort(Sort.Direction.ASC, "_id");
        Aggregation aggregation = newAggregation(groupStage, sortStage, projectStage);
        AggregationResults<StudentCountByState> results = mongoTemplate.aggregate(aggregation, "students", StudentCountByState.class);
        return results.getMappedResults();
    }

    @Override
    public List<StudentCountByGrade> getStudentCountByGrade() {
        GroupOperation groupStage = group("grade").count().as("count");
        ProjectionOperation projectStage = project("grade", "count");
        SortOperation sortStage = sort(Sort.Direction.ASC, "_id");
        Aggregation aggregation = newAggregation(groupStage, sortStage);
        AggregationResults<StudentCountByGrade> results = mongoTemplate.aggregate(aggregation, "students", StudentCountByGrade.class);
        return results.getMappedResults();
    }
}
