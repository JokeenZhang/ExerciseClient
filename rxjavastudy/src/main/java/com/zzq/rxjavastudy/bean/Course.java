package com.zzq.rxjavastudy.bean;

public class Course {
    public int courseId;//id
    public String name;//课程名
    public int score;//成绩

    public Course(int sourceId, String name, int score) {
        this.courseId = sourceId;
        this.name = name;
        this.score = score;
    }
}
