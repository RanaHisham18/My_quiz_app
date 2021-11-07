package com.rana.myquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rana.myquiz.quizcontract.*;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class quizdphelper  extends SQLiteOpenHelper {
private static final String database_name = "MY QUIZ.db";
private static final int database_version = 1;
private SQLiteDatabase db;
    public quizdphelper(@Nullable Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db= db;
        final String SQL_questions_table = "CREATE TABLE" + Questiontable.table_name + "( + " +
                Questiontable._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Questiontable.column_question + "TEXT, "+
                Questiontable.column_option1 + "TEXT ,"+
                Questiontable.column_option1 + "TEXT ,"+
                Questiontable.column_option2 + "TEXT ,"+

                Questiontable.column_option3 + "TEXT ,"+
                Questiontable.column_answer_no + " INTEGER"+
                ")";
        db.execSQL(SQL_questions_table);
        fillquestionstable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + Questiontable.table_name);
onCreate(db);
    }
    private void fillquestionstable(){
        question q1 = new question("A is correct", "A" , "B" ,"c" , 1);
        addquestion(q1);
        question q2 = new question("B is correct", "A" , "B" ,"c" , 2);
        addquestion(q2);
        question q3 = new question("C is correct", "A" , "B" ,"c" , 3);
        addquestion(q3);
        question q4 = new question("A is correct again", "A" , "B" ,"c" , 1);
        addquestion(q4);
        question q5 = new question("B is correct again", "A" , "B" ,"c" , 2);
        addquestion(q5);

    }
    private void addquestion(question question){
        ContentValues cv = new ContentValues();
        cv.put(Questiontable.column_question, question.getQuestion());
        cv.put(Questiontable.column_option1, question.getOption1());
        cv.put(Questiontable.column_option2, question.getOption2());
        cv.put(Questiontable.column_option3, question.getOption3());
        cv.put(Questiontable.column_answer_no, question.getAnswerno());
        db.insert(Questiontable.table_name, null, cv);
    }

    public List<question> getAllquestions(){
        List<question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + Questiontable.table_name, null);

        if (c.moveToFirst()) {
            do {
                question question = new question();
                question.setQuestion(((Cursor) c).getString(c.getColumnIndex(Questiontable.column_question)));
                question.setOption1(c.getString(c.getColumnIndex(Questiontable.column_option1)));
                question.setOption2(c.getString(c.getColumnIndex(Questiontable.column_option2)));
                question.setOption3(c.getString(c.getColumnIndex(Questiontable.column_option3)));
                question.setAnswerno(c.getInt(c.getColumnIndex(Questiontable.column_answer_no)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }



}




