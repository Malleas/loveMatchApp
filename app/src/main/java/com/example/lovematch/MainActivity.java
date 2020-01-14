package com.example.lovematch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_compute;
    ImageView iv_needle, iv_gauge;
    TextView tv_yourName, tv_otherPersonsName;
    EditText et_yourName, et_otherPersonsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_compute = findViewById(R.id.btn_compute);
        iv_gauge = findViewById(R.id.iv_gauge);
        iv_needle = findViewById(R.id.iv_needle);
        tv_yourName = findViewById(R.id.tv_yourName);
        tv_otherPersonsName = findViewById(R.id.tv_otherPersonName);
        et_otherPersonsName = findViewById(R.id.et_otherPersonsName);
        et_yourName = findViewById(R.id.et_yourName);

        btn_compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourname = et_yourName.getText().toString().toLowerCase();
                String otherPersonsName = et_otherPersonsName.getText().toString().toLowerCase();
                int totalLetters = yourname.length() + otherPersonsName.length();
                int totalMatches = 0;

                for(int i = 0; i < yourname.length(); i++){
                    for(int j = 0; j < otherPersonsName.length(); j++){
                        if(yourname.charAt(i) == otherPersonsName.charAt(j)){
                            totalMatches++;
                        }
                    }
                }
                for(int i = 0; i < otherPersonsName.length(); i++){
                    for(int j = 0; j < yourname.length(); j++){
                        if(otherPersonsName.charAt(i) == yourname.charAt(j)){
                            totalMatches++;
                        }
                    }
                }
                float compatScore = (float) totalMatches / totalLetters;
                int loveScore = ((int) (compatScore* 100)) - 50;
                Toast.makeText(MainActivity.this, "Compat Score = " + compatScore, Toast.LENGTH_SHORT).show();

                RotateAnimation rotateNeedle = new RotateAnimation(0, 360 + loveScore,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

                rotateNeedle.setFillAfter(true);
                rotateNeedle.setDuration(2000);
                rotateNeedle.setInterpolator(new AccelerateDecelerateInterpolator());
                iv_needle.startAnimation(rotateNeedle);

                Toast.makeText(MainActivity.this, "Love score of: " + loveScore, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
