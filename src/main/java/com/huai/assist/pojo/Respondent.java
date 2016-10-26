package com.huai.assist.pojo;

/**
 *
 * 填写调查问卷的人
 * Created by liangyh on 10/12/16.
 */
public class Respondent {

    private int id;
    private String name;
    private String gender;
    private String school; //学校
    private String education; //教育水平
    private String major; //专业
    private String nationality; //民族
    private String residence; //户口类型
    private int surveyFinish; //调查问卷完成情况

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public int getSurveyFinish() {
        return surveyFinish;
    }

    public void setSurveyFinish(int surveyFinish) {
        this.surveyFinish = surveyFinish;
    }
}
