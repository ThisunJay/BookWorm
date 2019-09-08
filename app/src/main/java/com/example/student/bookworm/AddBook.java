package com.example.student.bookworm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;

public class AddBook extends AppCompatActivity {
    DBHandler db;
    EditText txt_title, txt_author, txt_price, txt_pages, txt_review;
    private String title, author, price, pages, review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        txt_title = findViewById(R.id.ttin1);
        txt_author = findViewById(R.id.ttin2);
        txt_price = findViewById(R.id.ttin3);
        txt_pages = findViewById(R.id.ttin4);
        txt_review = findViewById(R.id.ttin5);

        db = new DBHandler(this);
    }

    public void onBack(View view){
       Intent intent = new Intent(AddBook.this,BooksMain.class);
       startActivity(intent);
    }





    public void addData(View view){

        title = txt_title.getText().toString();
        author = txt_author.getText().toString();
        price = txt_price.getText().toString();
        pages = txt_pages.getText().toString();
        review = txt_review.getText().toString();

        boolean result = db.addBook(title, author, price, pages, review);

        if(result == true){
            //Toast.makeText(getApplicationContext(), "Book Added Successsfully", Toast.LENGTH_SHORT);
            Toast.makeText(getApplicationContext(),"Book Added Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddBook.this, BooksMain.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Book adding faild", Toast.LENGTH_SHORT).show();
        }

    }
}
