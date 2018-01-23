package com.example.android.myquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
String text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView Result = findViewById(R.id.res);

        Intent i = getIntent();
        text = i.getStringExtra("result");
        Result.setText(text);
    }

    public void reset(View view){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    /**
     * This method make an intent to send an email
     */
    public void composeEmail(View view ) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Personal analysis");
        intent.putExtra(Intent.EXTRA_TEXT,text);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
