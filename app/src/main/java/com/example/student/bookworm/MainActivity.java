package com.example.student.bookworm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRead(View view){
        Intent intent1 = new Intent(MainActivity.this,ReadingActivity.class);
        startActivity(intent1);
    }

    public void clickBook(View view){
        Intent intent = new Intent(MainActivity.this, BooksMain.class);
        startActivity(intent);
    }
}
