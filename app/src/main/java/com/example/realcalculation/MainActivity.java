package com.example.realcalculation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    ImageView bgapp, clover;
    ImageButton buttonc,buttona,buttonb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        buttona=findViewById(R.id.buttona);
        buttonb=findViewById(R.id.buttonb);
        //buttonc=findViewById(R.id.buttonc);
        buttona.setOnClickListener(this);
        buttonb.setOnClickListener(this);
       // buttonc.setOnClickListener(this);



    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id) {
            case R.id.buttona:
               // Toast.makeText(this,"btnaxlicked",Toast.LENGTH_SHORT).show();
                openActivity1();
                break;
            case R.id.buttonb:
                Toast.makeText(MainActivity.this ,"btnbxlicked",Toast.LENGTH_SHORT).show();
                openActivity2();
                break;
            case R.id.buttonc:
                Toast.makeText(this,"btncxlicked",Toast.LENGTH_SHORT).show();
              //  i = new Intent(this, Split_app.class);
              //  startActivity(i);
                openActivity3();
                break;
        }
    }
    public void openActivity1(){
        Intent intent =new Intent(this,Basic_Calculator.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent =new Intent(this,Scientific_Calci.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent =new Intent(this,Split_app.class);
        startActivity(intent);
    }


}
