package com.futurex.services.FutureXCatalogApp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;


@JsonIgnoreProperties({"hibernateLazyIntialize", "handler"})
public class Course {



    private  BigInteger courseId;

    private  String courseName;

    private  String authorName;

    public Course(){

    }

    public Course(BigInteger courseId, String courseName, String authorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.authorName = authorName;
    }



    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


}
