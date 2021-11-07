package com.rana.myquiz;

import android.provider.BaseColumns;

public final class quizcontract {

    private quizcontract(){}

    public static class Questiontable implements BaseColumns {

        public static final String table_name = "quiz_questions" ;
        public static final String column_question ="question";
        public static final String column_option1 = "option1";
        public static final String column_option2 = "option2";
        public static final String column_option3 = "option3";
        public static final String column_answer_no = "answer_no";
    }
}
