package com.development.scut_cdd;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.development.scut_cdd.Clientlayer.ClientDataGenerator;
import com.development.scut_cdd.Clientlayer.ClientRunnable;
import com.development.scut_cdd.Clientlayer.DataSender;
import com.development.scut_cdd.ServerLayer.ServerService;
import com.development.scut_cdd.UserState.ReadyState;
import com.development.scut_cdd.UserState.UserState;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.GameTurnCon;
import ControllerImpl.GameTurnConImpl;
import ControllerImpl.UIConImpl;
import Model.Entity.DataPackage;
import Model.Entity.RoomModel;
import Wifi.NetworkUtils;

public class CardBoardActivity extends AppCompatActivity {

    Messenger ServiceMessager;
    private static final String TAG = "wangshu";
    UserState userState;

    BlockingQueue<RoomModel> messageQueue = new LinkedBlockingQueue<>();

    DataPackage dataPackage=null;

    Vector<String> order = null;
    UIConImpl uiCon;
    DataSender dataSender;
    ClientRunnable clientRunnable;
    private ExecutorService executorService;//线程池


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Config.USER_ID="YeDahang";
        super.onCreate(savedInstanceState);
        CardBoardActivity tem=this;

        setScreenWidth();
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_card_board);


        //创建线程池
        executorService = Executors.newFixedThreadPool(6);
        uiCon=new UIConImpl(this);
//        executorService.submit(uiCon);
        uiCon.initAllUI();



        EditText editText = findViewById(R.id.ipEdit);
        Button serverbtn =findViewById(R.id.serverbtn);
        serverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initServer();
            }
        });

        Button ok=findViewById(R.id.okip);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initClient(editText.getText().toString());
            }
        });

        Button addRobot=findViewById(R.id.AddRobot);
        addRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSender.sendData(ClientDataGenerator.addRobotRoRoom());
            }
        });

        Button idbn = findViewById(R.id.idbtn);
        idbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t=findViewById(R.id.idedit);
                 Config.USER_ID=t.getText().toString();
                 TextView t1=findViewById(R.id.idtext);
                t1.setText(Config.USER_ID);
                t1.setTextColor(Color.WHITE);
//                t1.setBackgroundColor(Color.parseColor("#FFA500"));
            }
        });

//        initServer();
//////********************************************************* <p>描述作用,替换这段文字,可换行</p>*/
//        int pid = android.os.Process.myPid();
//        Log.d(TAG, "MyApplication is oncreate====="+"pid="+pid);
//

        //___________________________________________________________________________________


        //____________________________________________________________________________________
//        Debug.waitForDebugger();//debug用
//        this.startService(myServiceIntent);
//        Thread serverThread = new Thread(new ServerThread());
//        serverThread.start();

        changeStateTo(new ReadyState(this));
    }





    void initServer(){
        Button server=findViewById(R.id.serverbtn);
        Config.IPADDRESS= NetworkUtils.getIpAddress(getApplicationContext());
        TextView ipaddress = findViewById(R.id.sererIpAddress);
        ipaddress.setTextColor(Color.WHITE);
        ipaddress.setText(Config.IPADDRESS);
        //***********************************************服务器端***************************
        Intent myServiceIntent=new Intent(CardBoardActivity.this, ServerService.class);
//        bindService(myServiceIntent,myServiceConnection, Context.BIND_AUTO_CREATE);
        startService(myServiceIntent);
    }
    void initClient(String ip){
        //*********************************************本地客户端*****************************
        clientRunnable= new ClientRunnable(this,ip);
        executorService.submit(clientRunnable);
        dataSender=new DataSender(clientRunnable);

    }
    private String intToIp(int ip) {
        return ((ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 24) & 0xFF));
    }
    private String getIpAddress(String bssid) {
        String[] ipParts = bssid.split(":");
        String ipAddress = "";
        for (int i = 0; i < ipParts.length; i++) {
            if (i == ipParts.length - 1) {
                ipAddress += Integer.parseInt(ipParts[i], 16);
            } else {
                ipAddress += Integer.parseInt(ipParts[i], 16) + ".";
            }
        }
        return ipAddress;
    }

    //获取屏幕宽度
    private void setScreenWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Config.screenWidth = displayMetrics.widthPixels;

    }

    public void changeStateTo(UserState userState){
        this.userState=userState;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }


    public BlockingQueue<RoomModel> getMessageQueue() {
        return messageQueue;
    }

    public void putMessage(RoomModel s){
        try{
            messageQueue.put(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
//    public void pass_text_Display(ImageView imageView){
//        imageView.setVisibility(View.VISIBLE);
//        MyAnimation.zoomInAndOut(imageView);
//    }
//    public Handler mHandler = new Handler(Looper.getMainLooper()){
//
//        @Override
//        public void handleMessage( Message msg){
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    pass_text_Display((ImageView) msg.obj);
//                }
//            });
//        }
//    };

    public void setOrder(Vector<String> order) {
        this.order = order;
    }

    public Vector<String> getOrder() {
        return order;
    }

    public ClientRunnable getClientRunnable() {
        return clientRunnable;
    }

    public DataPackage getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(DataPackage dataPackage) {
        this.dataPackage = dataPackage;
    }

    public DataSender getDataSender() {
        return dataSender;
    }

    public UIConImpl getUiCon() {
        return uiCon;
    }
}