package com.t.teamten.GreenFoodTracker.Login_And_Registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.t.teamten.GreenFoodTracker.R;

public class About_Activity extends AppCompatActivity {

    PDFView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);
        about = (PDFView) findViewById(R.id.pdfaboutpage);
        about.fromAsset("aboutpage.pdf").load();

    }
}
