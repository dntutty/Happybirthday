package com.example.lenovo.happybirthday.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.example.lenovo.happybirthday.R;

public class EditMessageActivity extends AppCompatActivity {

    private String happy ="";
	private EditText edittxtPhoneNo;
	private EditText edittextMessContext;
	private String phoneNumber ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editmessage);
		
		 edittxtPhoneNo = (EditText) findViewById(R.id.edittxtPhoneNo);
		 edittextMessContext = (EditText) findViewById(R.id.edittextMessContext);
		
		phoneNumber = getIntent().getStringExtra("phone");
        happy="今天是老师你的生日,祝你生日快乐";
		edittxtPhoneNo.setText(phoneNumber);
        edittextMessContext.setText(happy);
		Button bt = (Button) findViewById(R.id.button1);

	     bt.setOnClickListener(new OnClickListener() {
				
	        	@Override
				public void onClick(View v) {
					if(ContextCompat.checkSelfPermission(EditMessageActivity.this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
						ActivityCompat.requestPermissions(EditMessageActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
					} else if(ContextCompat.checkSelfPermission(EditMessageActivity.this , Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
						ActivityCompat.requestPermissions(EditMessageActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},2);
					}
					else {
						sendMsg();
					}



				}

				
			});
	}


	private void sendMsg() {

		String mess = edittextMessContext.getText().toString();
        if(edittxtPhoneNo.getText().toString()!=""&&edittxtPhoneNo.getText().toString()!=null){
            if(mess.length()>0){
                SmsManager sms = SmsManager.getDefault();
                String phoneNumber = edittxtPhoneNo.getText().toString();
                String[] phonearray = phoneNumber.split(";");
                for(int i = 0;i<phonearray.length;i++){
                    sms.sendTextMessage(phonearray[i], null, mess, null, null);
                }
                Toast.makeText(EditMessageActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                Intent data = new Intent();
                data.putExtra("data","OK");
                setResult(2,data);
                finish();
            }else{
                Toast.makeText(EditMessageActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(EditMessageActivity.this, "请输入电话", Toast.LENGTH_SHORT).show();
        }



	}

	@Override
	public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
		switch(requestCode) {
			case 1:
				if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
					sendMsg();
				} else {
					Toast.makeText(this,"您没有此权限！",Toast.LENGTH_SHORT).show();
				}
				break;
			case 2:
				if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
					sendMsg();
				} else {
					Toast.makeText(this,"您没有此权限！",Toast.LENGTH_SHORT).show();
				}
			default:
		}
	}


}
