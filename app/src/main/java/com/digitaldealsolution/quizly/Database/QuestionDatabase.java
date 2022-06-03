/*
 * *
 *  * Created by harsh on 03/06/22, 8:26 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 03/06/22, 8:26 PM
 *
 */
package com.digitaldealsolution.quizly.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = QuestionModel.class, version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    public  abstract  QuestionDOA questionDOA();

    public static QuestionDatabase INSTANCE;

    public  static  QuestionDatabase getDbInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class,  "QuestionDatabase.db")
                    .createFromAsset("database/QuestionDatabase.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
