package com.digitaldealsolution.quizly.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDOA {
    @Insert
    void insert(QuestionModel model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(QuestionModel model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(QuestionModel model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM question_table")
    void deleteAllQuestion();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM question_table ORDER BY id ASC")
    List<QuestionModel> getAllQuestion();
}
