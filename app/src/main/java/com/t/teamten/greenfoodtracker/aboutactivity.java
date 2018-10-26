package com.t.teamten.greenfoodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class aboutactivity extends AppCompatActivity {

    PDFView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);
        about = (PDFView) findViewById(R.id.pdfaboutpage);
        about.fromAsset("aboutpage.pdf").load();

    }
}
