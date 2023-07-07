package com.development.scut_cdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import CDD.view.BluetoothView;
import CDD.view.InputIPView;
import CDD.view.RegisterView;
import CDD.view.StartView;

public class yao_main2 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.yao_main2);

        StartView.setAppCompatActivity(this);
        InputIPView.setAppCompatActivity(this);
        RegisterView.setAppCompatActivity(this);
        BluetoothView.setAppCompatActivity(this);

        /*SettlementScoreView settlementScoreView = findViewById(R.id.settlementScoreView);
        settlementScoreView.setVisibility(View.VISIBLE);
        settlementScoreView.displayResult("player1",10,1,100,
                "player2",10,4,100,
                "player3",10,2,100,
                "player4",10,3,100);*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 蓝牙已成功打开，进行跳转到游戏页面的操作
            findViewById(R.id.bluetoothView).setVisibility(View.VISIBLE);
        } else {
        }
    }

    protected void onResume()
    {
        super.onResume();
        StartView.videoView.start();
    }
}
