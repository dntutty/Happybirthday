package com.example.lenovo.happybirthday.database;

import android.content.Context;
import java.io.File;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class myDB extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "teacher";
    private static final String TABLE_NAME = "teacher";
    private static final int DB_VERSION = 1;


    public myDB(Context context) {     
        super(context, DB_NAME, null, DB_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE if not exists "
                + TABLE_NAME
                + " ( id INTEGER PRIMARY KEY, username TEXT,  usercname TEXT, phonne TEXT,birthday TEXT,dw TEXT)";
		
		SQLiteDatabase sqLiteDatabase =SQLiteDatabase.openOrCreateDatabase("data/data/com.example.happybirthday/teacher.db",null);
        sqLiteDatabase.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

	public SQLiteDatabase getDatabase(){
		String filePath = "data/data/com.example.lenovo.happybirthday/teacher.db";
		File jhPath=new File(filePath);
		SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(jhPath, null);

		return openOrCreateDatabase;
	}

}
