package com.example.student.bookworm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.BookAdapter;
import Database.DBHandler;
import Model.BookInfo;
import Model.RBooks;

public class BooksMain extends AppCompatActivity implements BookAdapter.onBookListner{

    private ArrayList<BookInfo> arrayList;
    DBHandler db;
    RecyclerView rv;
    BookAdapter adapter;
    BookInfo b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_main);

       db = new DBHandler(this);

        arrayList = db.readAllBookinfo();
        Log.i("DB", arrayList.size() + "Size ");
        rv = findViewById(R.id.recView);
        adapter = new BookAdapter(arrayList, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv);
    }

    public void addBook(View view){
        Intent intent = new Intent(BooksMain.this, AddBook.class);
        startActivity(intent);
    }

    public void viewBook(View view){
        Intent intent = new Intent(BooksMain.this, ViewBook.class);
        startActivity(intent);
    }

    public void onBack(View view){
        Intent intent = new Intent(BooksMain.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnBookClick(int position) {
        b = arrayList.get(position);
        //RecyclerView.ViewHolder viw = null;
        //int i = arrayList.get(rv.getAdapterPosition()).getID();

        //String rev = b.getReview();

        //int i = b.getID();
        //Toast.makeText(getApplicationContext(), b.getID() + " ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,ViewBook.class);
        intent.putExtra("id", b.getID() + " ");
        intent.putExtra("title", b.getTitle());
        intent.putExtra("review", b.getReview());
        startActivity(intent);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            int id = arrayList.get(viewHolder.getAdapterPosition()).getID();
            db.deleteBook(id);
            arrayList.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"Deleted" ,Toast.LENGTH_LONG).show();

        }
    };


}
