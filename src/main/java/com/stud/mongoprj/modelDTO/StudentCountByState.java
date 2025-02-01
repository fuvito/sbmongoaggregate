package com.stud.mongoprj.modelDTO;

public record StudentCountByState(String _id, Integer count) {
    public StudentCountByState(String _id, Integer count) {
        this._id = _id;
        this.count = count;
    }
}

