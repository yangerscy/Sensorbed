package com.example.sensorbed;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sensorbed.fragment.FragmentOne;
import com.example.sensorbed.fragment.FragmentThree;
import com.example.sensorbed.fragment.FragmentTwo;

public class Bedview extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    showNav(R.id.navigation_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    showNav(R.id.navigation_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    showNav(R.id.navigation_notifications);
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        AlertDialog.Builder dialog = new AlertDialog.Builder(Bedview.this);
        dialog.setTitle("退出");
        dialog.setMessage("是否要退出程式?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("是的",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();  // 关闭App
                    }
                });

        dialog.setNegativeButton("按錯了",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 这里什么也不写，也能达到关闭对话框的目的，不知道合不合适
                    }
                });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedview);

        //設定隱藏狀態
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
               navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void init(){
        fragmentOne=new FragmentOne();
        fragmentTwo=new FragmentTwo();
        fragmentThree=new FragmentThree();
        android.support.v4.app.FragmentTransaction beginTransaction=getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.content,fragmentOne).add(R.id.content,fragmentTwo).add(R.id.content,fragmentThree);//开启一个事务将fragment动态加载到组件
        beginTransaction.hide(fragmentOne).hide(fragmentTwo).hide(fragmentThree);//隐藏fragment
        //beginTransaction.addToBackStack(null);//返回到上一个显示的fragment
        beginTransaction.commit();//每一个事务最后操作必须是commit（），否则看不见效果
        showNav(R.id.navigation_home);
    }

    private void showNav(int navid){
        FragmentTransaction beginTransaction=getSupportFragmentManager().beginTransaction();
        switch (navid){
            case R.id.navigation_home:
                beginTransaction.hide(fragmentTwo).hide(fragmentThree);
                beginTransaction.show(fragmentOne);
                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_dashboard:
                beginTransaction.hide(fragmentOne).hide(fragmentThree);
                beginTransaction.show(fragmentTwo);

                //beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_notifications:
                beginTransaction.hide(fragmentTwo).hide(fragmentOne);
                beginTransaction.show(fragmentThree);
               // beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
        }
    }

}


