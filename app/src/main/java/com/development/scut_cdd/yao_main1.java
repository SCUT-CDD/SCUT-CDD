package com.development.scut_cdd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import CDD.view.InputIPView;

public class yao_main1 extends AppCompatActivity {

    protected VideoView videoView;
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                // 蓝牙已成功打开，进行跳转到游戏页面的操作
                Intent gameIntent = new Intent(this, yao_main3.class);
                startActivity(gameIntent);
            } else {
                // 用户取消了打开蓝牙操作，可以进行相应的处理
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.yao_main1);

        videoView = findViewById(R.id.video);
        String videoUrl = "android.resource://" + getPackageName() + "/" + R.raw.title;
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();

        // logo 的监听器
        ImageView button_logo = findViewById(R.id.logo);
        button_logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 创建AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(yao_main1.this);
                builder.setMessage("该游戏由 华南理工大学-三人行 开发，侵权必究");
                builder.setPositiveButton("确定", null);
                builder.show();
            }
        });

        // 设置 的监听器
        ImageButton button_setting = findViewById(R.id.setting);
        button_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(yao_main1.this);
                builder.setTitle("设置");
                builder.setMessage("设置");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 排行榜 的监听器
        ImageButton button_rank = findViewById(R.id.rank);
        button_rank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(yao_main1.this);
                builder.setTitle("排行榜");
                builder.setMessage("排行榜");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 单机游戏 的监听器
        ImageButton button_singlePlayerGame = findViewById(R.id.single_player_game);
        button_singlePlayerGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
            }
        });

        // 好友约局 的监听器
        ImageButton button_multiPlayerGame = findViewById(R.id.multiplayer_game);
        button_multiPlayerGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.single_player_game).setVisibility(View.GONE);
                findViewById(R.id.multiplayer_game).setVisibility(View.GONE);
                findViewById(R.id.setting).setVisibility(View.GONE);
                findViewById(R.id.rank).setVisibility(View.GONE);
                findViewById(R.id.blueToothConnection).setVisibility(View.VISIBLE);
                findViewById(R.id.WLANConnection).setVisibility(View.VISIBLE);
                findViewById(R.id.returnLastPage).setVisibility(View.VISIBLE);
            }
        });


        // 返回 的监听器
        ImageButton button_return = findViewById(R.id.returnLastPage);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.returnLastPage)
                {
                    findViewById(R.id.single_player_game).setVisibility(View.VISIBLE);
                    findViewById(R.id.multiplayer_game).setVisibility(View.VISIBLE);
                    findViewById(R.id.setting).setVisibility(View.VISIBLE);
                    findViewById(R.id.rank).setVisibility(View.VISIBLE);
                    findViewById(R.id.blueToothConnection).setVisibility(View.GONE);
                    findViewById(R.id.WLANConnection).setVisibility(View.GONE);
                    findViewById(R.id.returnLastPage).setVisibility(View.GONE);
                }
            }
        });


        // 蓝牙连接 的监听器
        ImageButton button_blueTooth = findViewById(R.id.blueToothConnection);
        button_blueTooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(yao_main1.this);
                builder.setMessage("是否允许应用打开蓝牙？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (bluetoothAdapter == null)
                            Toast.makeText(yao_main1.this, "当前手机设备不支持蓝牙", Toast.LENGTH_SHORT).show();
                        else {
                            //手机设备支持蓝牙，判断蓝牙是否已开启
                            if (bluetoothAdapter.isEnabled())
                            {
                                Toast.makeText(yao_main1.this, "手机蓝牙已开启", Toast.LENGTH_SHORT).show();
                                Intent gaming_page = new Intent(yao_main1.this, yao_main3.class);
                                startActivity(gaming_page);
                            }
                            else
                            {
                                if (ActivityCompat.checkSelfPermission(yao_main1.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(yao_main1.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_ENABLE_BT);
                                    return;
                                }
                                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT);
                            }
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        // 热点连接 的监听器
        ImageButton button_wlan = findViewById(R.id.WLANConnection);
        button_wlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(yao_main1.this);
                builder.setMessage("是否允许应用打开热点？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作

                        button_logo.setEnabled(false);
                        button_return.setEnabled(false);
                        button_blueTooth.setEnabled(false);
                        button_wlan.setEnabled(false);

                        InputIPView inputIPView = findViewById(R.id.input_iping);
                        inputIPView.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    protected void onResume()
    {
        super.onResume();
        videoView.start();
    }
}