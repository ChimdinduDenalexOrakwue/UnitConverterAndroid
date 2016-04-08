package com.example.denalex.unitconverter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    public static boolean night;
    Switch nightSwitch;
    Button backButton;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        nightSwitch = (Switch) findViewById(R.id.switch1);
        backButton = (Button) findViewById(R.id.button2);
        layout = (RelativeLayout) findViewById(R.id.setting_layout);
        Intent intent = getIntent();
        night = intent.getExtras().getBoolean("on");
        if (night) {
            layout.setBackgroundColor(Color.GRAY);
            nightSwitch.setChecked(true);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextAppearance(this, R.style.MyTitleTextApperance);


        String fontPath = "fonts/LobsterTwo-Regular.ttf";
        TextView font = (TextView) findViewById(R.id.toolbar_title);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        font.setTypeface(tf);

        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                night = !night;
                if (night) {
                    layout.setBackgroundColor(Color.GRAY);
                } else {
                    layout.setBackgroundColor(Color.WHITE);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    @Override
    public void onBackPressed() {
        //MainActivity.setNight(night);
        Intent save = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(save);
        super.onBackPressed();
    }
}
