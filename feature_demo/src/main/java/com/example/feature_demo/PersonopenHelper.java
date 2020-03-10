package com.example.feature_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonopenHelper extends SQLiteOpenHelper {

    public PersonopenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     *对外提供一个简单的构造函数，使用默认的数据库和默认的版本号
     * @param context
     */
    PersonopenHelper(Context context){
        super(context,"person.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table person (id integer primary key autoincrement," +
                "name varchar(20)," +
                "phone varchar(20)," +
                "age integer,addres varchar(20));";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
