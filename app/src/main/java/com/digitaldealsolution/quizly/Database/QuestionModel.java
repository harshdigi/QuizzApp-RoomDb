/*
 * *
 *  * Created by harsh on 03/06/22, 8:26 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 03/06/22, 8:26 PM
 *
 */
package com.digitaldealsolution.quizly.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class QuestionModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "question")
    private String question;
    @ColumnInfo(name = "option1")
    private String option1;
    @ColumnInfo(name = "option2")
    private String option2;
    @ColumnInfo(name = "option3")
    private String option3;
    @ColumnInfo(name = "option4")
    private  String option4;
    @NonNull
    @ColumnInfo(name = "answer")
    private int answer;

    public QuestionModel(String question, String option1, String option2, String option3, String option4, int answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
