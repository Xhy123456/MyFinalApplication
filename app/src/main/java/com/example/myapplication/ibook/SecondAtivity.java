package com.example.myapplication.ibook;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.beans.Cuns;
import com.example.myapplication.luoji.MyDataBase;

import java.sql.Date;
import java.text.SimpleDateFormat;

/*
 *用来编辑日记
 *主要包括一个方法，isSave()用来保存数据;
 */
public class SecondAtivity extends Activity {

    EditText ed1,ed2;
    Button bt1;
    MyDataBase myDatabase;
    Cuns cun;
    int ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_ativity);
        ed1=(EditText) findViewById(R.id.editText1);
        ed2=(EditText) findViewById(R.id.editText2);
        bt1=(Button) findViewById(R.id.button1);
        myDatabase=new MyDataBase(this);

        Intent intent=this.getIntent();
        ids=intent.getIntExtra("ids", 0);
        //默认为0，不为0,则为修改数据时跳转过来的
        if(ids!=0){
            cun=myDatabase.getTiandCon(ids);
            ed1.setText(cun.getTitle());
            ed2.setText(cun.getContent());
        }
        //保存按钮的点击事件，他和返回按钮是一样的功能，所以都调用isSave()方法；
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO Auto-generated method stub
                isSave();
            }
        });
    }
    /*
     * 返回按钮调用的方法。
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy.MM.dd  HH:mm:ss");
        Date curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
        String times   =   formatter.format(curDate);
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();
        //是要修改数据
        if(ids!=0){
            cun=new Cuns(title,ids, content, times);
            myDatabase.toUpdate(cun);
            Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
        //新建日记
        else{
            if(title.equals("")&&content.equals("")){
                Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
                startActivity(intent);
                SecondAtivity.this.finish();
            }
            else{
                cun=new Cuns(title,content,times);
                myDatabase.toInsert(cun);
                Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
                startActivity(intent);
                SecondAtivity.this.finish();
            }

        }
    }
    private void isSave(){
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy.MM.dd  HH:mm:ss");
        Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
        String times   =   formatter.format(curDate);
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();
        //是要修改数据
        if(ids!=0){
            cun=new Cuns(title,ids, content, times);
            myDatabase.toUpdate(cun);
            Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
        //新建日记
        else{
            cun=new Cuns(title,content,times);
            myDatabase.toInsert(cun);
            Intent intent=new Intent(SecondAtivity.this,MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_ativity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "标题："+ed1.getText().toString()+"    内容："+ed2.getText().toString());
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }


}
