package vn.edu.usth.twitterclient;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class FullsizeImage extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullsize_image);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Fullsize Image");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1DA1F2")));

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//        img = findViewById(R.id.fullsizeImage);
//        img.setImageResource(R.drawable.top1hltv);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}