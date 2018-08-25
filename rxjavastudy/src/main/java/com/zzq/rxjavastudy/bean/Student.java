package com.zzq.rxjavastudy.bean;

import java.util.List;

public class Student {
    public String name;//学生名字
    public int id;
    public List<Course> mCourses;//每个学生的所有课程

    public Student(String name, int id, List<Course> course) {
        this.name = name;
        this.id = id;
        mCourses = course;
    }
}
