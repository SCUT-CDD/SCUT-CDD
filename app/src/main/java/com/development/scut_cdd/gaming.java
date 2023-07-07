package com.development.scut_cdd;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gaming extends AppCompatActivity {

    protected Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settlement_view);


        //*********************************************************************************
        displayResult("player1",10,1,100,
                "player2",10,4,100,
                "player3",10,2,100,
                "player4",10,3,100);
        //*********************************************************************************

        
        // 退出 的监听器
        ImageButton button_exit = findViewById(R.id.exit);
        button_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(v.getId() == R.id.exit)
                    finish();
            }
        });

        // 继续 的监听器
        ImageButton button_next = findViewById(R.id.nextGame);
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
            }
        });

        // 聊天 的监听器
        ImageButton button_chat = findViewById(R.id.chatting_button);
        button_chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View chat = findViewById(R.id.chatting_background);
                if(chat.getVisibility() == View.VISIBLE)
                    chat.setVisibility(View.GONE);
                else
                    chat.setVisibility(View.VISIBLE);
            }
        });


        Button button_message1 = findViewById(R.id.message1);
        Button button_message2 = findViewById(R.id.message2);
        Button button_message3 = findViewById(R.id.message3);
        Button button_message4 = findViewById(R.id.message4);

        // 消息1 的监听器
        button_message1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView message = findViewById(R.id.sendMessage);
                message.setText("大家好，很高兴见到各位！");
                message.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        message.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });

        // 消息2 的监听器
        button_message2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView message = findViewById(R.id.sendMessage);
                message.setText("你是GG还是MM");
                message.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        message.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });

        // 消息3 的监听器
        button_message3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView message = findViewById(R.id.sendMessage);
                message.setText("快点吧，我等的花都谢了");
                message.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        message.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });

        // 消息4 的监听器
        button_message4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView message = findViewById(R.id.sendMessage);
                message.setText("真牛！");
                message.setVisibility(View.VISIBLE);
                button_message1.setEnabled(false);
                button_message2.setEnabled(false);
                button_message3.setEnabled(false);
                button_message4.setEnabled(false);
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        message.setVisibility(View.GONE);
                        button_message1.setEnabled(true);
                        button_message2.setEnabled(true);
                        button_message3.setEnabled(true);
                        button_message4.setEnabled(true);
                    }
                }, 3000); // 3000表示延迟的时间，单位为毫秒
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public void displayResult(String nickname1,int cardPoint1,int scoreOfThisTurn1,int scoreOfAllTurn1,
                                        String nickname2,int cardPoint2,int scoreOfThisTurn2,int scoreOfAllTurn2,
                                        String nickname3,int cardPoint3,int scoreOfThisTurn3,int scoreOfAllTurn3,
                                        String nickname4,int cardPoint4,int scoreOfThisTurn4,int scoreOfAllTurn4)
    {
        int[] arr = {scoreOfThisTurn1, scoreOfThisTurn2, scoreOfThisTurn3, scoreOfThisTurn4};
        int max = arr[0]; // 假设第一个元素为最大值

        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        displayPlayer1Information(nickname1,cardPoint1,scoreOfThisTurn1,scoreOfAllTurn1);
        displayPlayer2Information(nickname2,cardPoint2,scoreOfThisTurn2,scoreOfAllTurn2);
        displayPlayer3Information(nickname3,cardPoint3,scoreOfThisTurn3,scoreOfAllTurn3);
        displayPlayer4Information(nickname4,cardPoint4,scoreOfThisTurn4,scoreOfAllTurn4);

        if(max == scoreOfThisTurn1)
            findViewById(R.id.player1_background).setVisibility(View.VISIBLE);
        else if(max == scoreOfThisTurn2)
            findViewById(R.id.player2_background).setVisibility(View.VISIBLE);
        else if(max == scoreOfThisTurn3)
            findViewById(R.id.player3_background).setVisibility(View.VISIBLE);
        else if(max == scoreOfThisTurn4)
            findViewById(R.id.player4_background).setVisibility(View.VISIBLE);
    }

    public void displayPlayer1Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player1);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player1_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player1_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player1_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

    public void displayPlayer2Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player2);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player2_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player2_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player2_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

    public void displayPlayer3Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player3);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player3_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player3_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player3_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

    public void displayPlayer4Information(String nickname,int cardPoint,int scoreOfThisTurn,int scoreOfAllTurn)
    {
        TextView nickName_text = findViewById(R.id.player4);
        nickName_text.setText(nickname);
        TextView cardPoint_text = findViewById(R.id.player4_cardPoint);
        cardPoint_text.setText(String.valueOf(cardPoint));
        TextView scoreOfThisTurn_text = findViewById(R.id.player4_score);
        scoreOfThisTurn_text.setText(String.valueOf(scoreOfThisTurn));
        TextView scoreOfAllTurn_text = findViewById(R.id.player4_AllScore);
        scoreOfAllTurn_text.setText(String.valueOf(scoreOfAllTurn));
    }

}
