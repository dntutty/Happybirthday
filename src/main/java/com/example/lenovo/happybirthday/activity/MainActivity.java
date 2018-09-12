package com.example.lenovo.happybirthday.activity;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.lenovo.happybirthday.database.SQLdm;
import com.example.lenovo.happybirthday.model.CheckAdapter;
import com.example.lenovo.happybirthday.model.Teacher;



import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import com.example.lenovo.happybirthday.R;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<Teacher> teacherlist = new ArrayList<Teacher>();
    private BaseAdapter  checkadpter;
    private ListView listview;
    private String phone="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setContentView(R.layout.head);
        Date date  = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String time = df.format(date);
        ActionBar actionbar = getActionBar();
        if(actionbar != null){
            actionbar.setTitle("今天是:"+time);
        }

        try {
            showMainView();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        listview  = (ListView) findViewById(R.id.listView1);
        CheckAdapter checkAdapter = new CheckAdapter(teacherlist, this);//这是一种设计模式,适配器模式,这是构造方法
        listview.setAdapter(checkAdapter);//用来适配UI元素与数据列表的

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //int checkedItemPosition = listview.getCheckedItemPositions();
                //CheckBox ckb = (CheckBox) findViewById(R.id.ckb);
                CheckAdapter.ViewHolder viewholder = (CheckAdapter.ViewHolder) view.getTag();

                if(viewholder.checkBox.isChecked()){
                    viewholder.checkBox.setChecked(false);
                    Teacher teacher = teacherlist.get(position);
                    teacher.setChecked(false);
                    phone = phone.replaceAll(teacher.getPhone()+";","");
                }else{
                    viewholder.checkBox.setChecked(true);
                    Teacher teacher = teacherlist.get(position);
                    teacher.setChecked(true);

                    phone +=teacher.getPhone()+";";
                }

            }

			/*@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {

				Teacher temp = (Teacher) listview.getItemAtPosition(position);

				//Toast.makeText(MainActivity.this, "弄啥来", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,EditMessageActivity.class);
        		String phone=temp.getPhone();
        		intent.putExtra("phone", phone);
        		intent.setClass(MainActivity.this,EditMessageActivity.class);
        		startActivityForResult(intent, 1);
			}*/



        });


        final Button addContactButton = (Button) findViewById(R.id.button1);
        addContactButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                //Teacher temp = (Teacher) listview.getItemAtPosition(position);


                Toast.makeText(MainActivity.this, phone, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,EditMessageActivity.class);
                intent.putExtra("phone", phone);
                intent.setClass(MainActivity.this,EditMessageActivity.class);
                startActivityForResult(intent, 1);
            }
        });




    }

    public void showMainView() throws UnsupportedEncodingException{


        SQLdm s = new SQLdm();
        System.out.println(getApplicationContext().toString());
        SQLiteDatabase db =s.openDatabase(getApplicationContext());


        //TextView textv = (TextView) findViewById(R.id.textv);

        //Cursor cursor = db.rawQuery("select * from teacher where birthday=?", new String[]{"0811"});
        Cursor cursor = db.rawQuery("select * from teacher", null);
        System.out.println("eeeeeeeeeeeees");
        while(cursor.moveToNext()){
            String id =cursor.getString(cursor.getColumnIndex("id"));
            int id1 = Integer.parseInt(id);
            String username = cursor.getString(cursor.getColumnIndex("username"));
            // cursor.getBlob(cursor.getColumnIndex("usercname"))
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("usercname"));
            //String usercname = cursor.getString(cursor.getColumnIndex("usercname"));
            String usercname = new String(blob,"UTF-8");
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String dw = cursor.getString(cursor.getColumnIndex("dw"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));

            Teacher teacher = new Teacher(id1,username,usercname,phone,dw,birthday);
            teacherlist.add(teacher);
        }
        ListView ss;
        lv = (ListView)findViewById(R.id.listView1);
        //View headview = LayoutInflater.from(this).inflate(resource, root, attachToRoot)
        // lv.addHeaderView()();
        lv.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view ;
                if(convertView == null )
                {
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    view = inflater.inflate(R.layout.item, parent,false);
                    //view = View.inflate(getBaseContext(),R.layout.item,null);
                }
                else
                {
                    view = convertView;
                }

                Teacher teacher = teacherlist.get(position);
                /* TextView username = (TextView)view.findViewById(R.id.username);*/
                TextView usercname  = (TextView)view.findViewById(R.id.usercname);
                //usercname.setsi(15);
                TextView birthday  = (TextView)view.findViewById(R.id.birthday);
                // birthday.setHeight(15);
                TextView phone = (TextView)view.findViewById(R.id.phone);
                //phone.setHeight(15);

                /* username.setText(teacher.getUsername());*/
                usercname.setText(teacher.getUsercname());
                birthday.setText(teacher.getBirthday());
                phone.setText(teacher.getPhone());

                return view;

            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return teacherlist.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return teacherlist.size();
            }
        });

        cursor.close();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
            //获取第二个页面返回的data并且显示在控件tv中
            String str = data.getStringExtra("data");
            Toast.makeText(MainActivity.this, "发送成功:"+str, Toast.LENGTH_SHORT).show();
        }
    }



}
