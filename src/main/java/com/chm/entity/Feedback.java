package com.chm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题反馈的实体类
 */
public class Feedback implements Serializable {

    private Integer id;
    private String question;
    private Date time;

    public Feedback() {
    }

    public Feedback(Integer id, String question, Date time) {
        this.id = id;
        this.question = question;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", time=" + time +
                '}';
    }
}
