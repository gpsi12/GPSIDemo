package com.example.feature_demo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 对外共享数据的ContentProvider
 */
public class PersonContentProvider extends ContentProvider {
    //用于存放并匹配Uri标示信息，一般在静态代码块中对其信息进行初始化操作
    private static UriMatcher matcher;
    //生命一个用于操作数据库随想
    private PersonopenHelper openHelper;
    //主机名信息：对应清淡文件的 authorities 属性
    private static final String AUTHORITY = "com.gpsidemo.person";
    //数据库：表名
    private static final String TABLE_PERSON_NAME = "person";
    //Uri匹配成功的返回值
    private static final int PERSON_INSERT_COOE = 1000;
    private static final int PERSON_DELETE_COOE = 10001;
    private static final int PERSON_UPDATE_COOE = 10002;
    private static final int PERSON_QUERYALL_COOE = 10003;
    private static final int PERSON_QUERYONE_COOE = 10004;

    //静态代码块，用于初始化UriMatcher
    static{
        //NO_MATCH:表示没有Uri匹配时候返回的状态吗（-1）
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        /**
         * 添加一个分机号：
         * 对person表进行添加操作，如果 Uri=content:com.gpsidemo.person/person/insert,
         * 则返回PERSON_INSERT_COOE
         */
        matcher.addURI(AUTHORITY,"insert",PERSON_INSERT_COOE);
        /**
         * 对person表进行删除操作，如果Uri=content://com.gpsidemo.person/person/delete,
         * 则返回PERSON_DELETE_COOE
         */
        matcher.addURI(AUTHORITY,"person.delete",PERSON_DELETE_COOE);
        /**
         * 对person表进行修改更新操作，如果Uri=content://com.gpsidemo.person/person/update,
         * 则返回PERSON_UPDATE_COOE
         */
        matcher.addURI(AUTHORITY,"person/update",PERSON_UPDATE_COOE);
        /**
         * 对person表进行查询全部操作，如果Uri=content://com.gpsidemo.person/person/person,
         * 则返回PERSON_QUERYALL_COOE
         */
        matcher.addURI(AUTHORITY,"person",PERSON_QUERYALL_COOE);
        /**
         * 对person表进行单个操作，如果Uri=content://com.gpsidemo.person/person/#(#为数字)
         * 则返回PERSON_QUERYONE_COOE
         */
        matcher.addURI(AUTHORITY,"person/#",PERSON_QUERYONE_COOE);


    }

    @Override
    public boolean onCreate() {
        openHelper = new PersonopenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //用匹配器去匹配Uri，如果匹配成功则返回匹配器中对应的状态码
        int matchCode = matcher.match(uri);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        switch (matchCode){
            case PERSON_QUERYALL_COOE:
                return db.query(TABLE_PERSON_NAME
                        ,projection,selection,selectionArgs
                        ,null,null,sortOrder);
            case PERSON_QUERYONE_COOE:
                //使用ContentUris工具类解析出uri中的id
                long parsseId = ContentUris.parseId(uri);
                return db.query(TABLE_PERSON_NAME
                        ,projection,"id=?",
                        new String[]{parsseId+""},
                        null,null,sortOrder);
                default:
                    throw new IllegalArgumentException("Uri匹配失败："+uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //新插入对象id
        long id = db.insert(TABLE_PERSON_NAME,null,contentValues);
        db.close();
        //使用ContentUris工具类将id追加到uri中，返回给客户
        return ContentUris.withAppendedId(uri,id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //返回删除个数
        int count = db.delete(TABLE_PERSON_NAME,selection,selectionArgs);
        //关闭数据库
        db.close();
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //返回更新的个数
        int count = db.update(TABLE_PERSON_NAME,contentValues,selection,selectionArgs);
        db.close();
        return count;
    }
}
