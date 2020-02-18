package com.kingDev.Instagram_video_downloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class sharedFile extends Activity {

    String url = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        finish();

        Bundle extras = getIntent().getExtras();
        url = extras.get("android.intent.extra.TEXT").toString();

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("url", url);
        startActivity(i);

    }


}