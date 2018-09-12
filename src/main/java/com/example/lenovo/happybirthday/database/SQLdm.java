package com.example.lenovo.happybirthday.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLdm {
	
	String filePath = "data/data/com.example.lenovo.happybirthday/teacher.db";
	
	String pathStr ="data/data/com.example.lenovo.happybirthday";
	
	SQLiteDatabase database;
	public SQLiteDatabase openDatabase(Context context){
		 //System.out.println("filePath:"+filePath); 
		 
		 File jhPath=new File(filePath);
		      
		 if(jhPath.exists()){  
         	Log.i("teacher", "存在数据库");
             //存在则直接返回打开的数据库
         	SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(jhPath, null);
         	//System.out.println("111111111111111111111111111");
         	//System.out.println(openOrCreateDatabase.toString());
             return openOrCreateDatabase;  
         }else{  

             File path=new File(pathStr);  
             Log.i("teacher", "pathStr="+path);
             if (path.mkdir()){
             	Log.i("teacher", "mkdir exsit");
             }else{  
             	Log.i("teacher", "mkdir not exsit");
             };

             try {
                 //得到资源
                 AssetManager am= context.getAssets();
                 //用输出流写到SDcard上面
                 InputStream is=am.open("teacher.db");

                 FileOutputStream out=new FileOutputStream(jhPath);

                 //FileOutputStream fos=new FileOutputStream(jhPath);
                 Log.i("teacher", "fos="+out);
                 Log.i("teacher", "jhPath="+jhPath);
                 //创建byte数组  用于1KB写一次
                 byte[] buffer=new byte[1024];  
                 int count = 0;  
                 while((count = is.read(buffer))>0){  
                 	Log.i("teacher", "写入中");
                     out.write(buffer,0,count);
                 }
                 //最后关闭就可以了
                 out.flush();
                 out.close();
                 is.close(); 
                 //System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
             } catch (IOException e) {  
                 // TODO Auto-generated catch block  
                 e.printStackTrace();  
                 return null;
             }
             //如果没有这个数据库  我们已经把他写到SD卡上了，然后在执行一次这个方法 就可以返回数据库了
             return openDatabase(context);  
         }  

	}



}
