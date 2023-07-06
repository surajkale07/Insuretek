package com.example.insuretek;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class HomeScreen extends AppCompatActivity {

    // creating object of ViewPager
    ViewPager mViewPager;
    ImageView imageView;
    Timer timer;
    Handler handler;

    // images array
    int[] images = {R.drawable.tcsn,R.drawable.motori,R.drawable.albera,R.drawable.insuremo,R.drawable.nowhealth,R.drawable.turtlemint,R.drawable.yallacompare,R.drawable.fidelity,
            R.drawable.azentio,R.drawable.insillion,R.drawable.dni,R.drawable.salama,R.drawable.policybazaar,R.drawable.altamimi,
            R.drawable.fmcnetwork,R.drawable.ammin,R.drawable.insurancemarket,R.drawable.cyberwrite,R.drawable.albuharia,
            R.drawable.democrance,R.drawable.eia,R.drawable.gif,R.drawable.ibg,R.drawable.insuretechmena,R.drawable.difc,
            R.drawable.internationalbusinessmagazine,R.drawable.privatebanking,R.drawable.wbologo,R.drawable.exante,
            R.drawable.im,R.drawable.startup,R.drawable.tah,R.drawable.xagroup};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(HomeScreen.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);

        handler=new Handler();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int i=mViewPager.getCurrentItem();
                        if (i==images.length-1){
                            i=0;
                            mViewPager.setCurrentItem(i,true);
                        }else{
                            i++;
                            mViewPager.setCurrentItem(i,true);
                        }
                    }
                });
            }
        },2000,2000);

//        switch(images[0]){
//            case R.drawable.tcsn:
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                        startActivity(intent);
//
//               }
//                });
//
//        }

        CardView watchus,agenda,delegate;
        Button btn_chat;
        watchus= findViewById(R.id.watchus);
        watchus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,watchUs.class);
                startActivity(intent);
            }
        });

        agenda= findViewById(R.id.Agenda);
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,Agenda.class);
                startActivity(intent);
            }
        });
        btn_chat=findViewById(R.id.btn_chat);
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,chatactivity.class);
                startActivity(intent);
            }
        });
        delegate= findViewById(R.id.delegate);
        delegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,delegate.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,loginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}