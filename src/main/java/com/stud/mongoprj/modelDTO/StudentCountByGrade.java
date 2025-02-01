package com.stud.mongoprj.modelDTO;

public record StudentCountByGrade(String _id, Integer count) {
    public StudentCountByGrade(String _id, Integer count) {
        this._id = _id;
        this.count = count;
    }
}
