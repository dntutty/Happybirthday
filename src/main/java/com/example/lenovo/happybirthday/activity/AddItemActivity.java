package com.example.lenovo.happybirthday.activity;


import com.example.lenovo.happybirthday.database.myDB;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.happybirthday.R;

public class AddItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_add_item);
        final myDB dataHelper = new myDB(AddItemActivity.this);
        final EditText usercnameText = (EditText) findViewById(R.id.addusercname);
        final EditText  phoneText = (EditText)findViewById(R.id.addphone);
        final EditText addbirthdayText = (EditText)findViewById(R.id.addbirthday);

        Button btn_add = (Button) findViewById(R.id.additembutton);
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String  usercname = usercnameText.getText().toString();
                String  phone = phoneText.getText().toString();
                String  birthday = addbirthdayText.getText().toString();

                if(usercname.equals("")){
                    Toast.makeText(AddItemActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }else{
                    if(phone.equals("")){
                        Toast.makeText(AddItemActivity.this, "请输入电话", Toast.LENGTH_SHORT).show();
                    }else{
                        if(birthday.equals("")){
                            Toast.makeText(AddItemActivity.this, "请输入生日", Toast.LENGTH_SHORT).show();
                        }else{
                            SQLiteDatabase db =dataHelper.getWritableDatabase();
                            ContentValues cv = new ContentValues();
                            cv.put("usercname",usercname);
                            cv.put("phone",phone);
                            cv.put("birthday",birthday);
                            db.insert("teacher",null,cv);
                            db.close();
                        }
                    }
                }
            }
        });
    }


}
