package com.tictactoe.fb_midterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FB_listview extends AppCompatActivity {

    RecyclerView rv;
    List<ArticleList> articleLists;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_b_listview);

        rv=findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decoration);
        articleLists=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("myImages");
        getImageData();

    }
    private void getImageData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    ArticleList articleList=di.getValue(ArticleList.class);
                    articleLists.add(articleList);
                }
                ArticleAdapter adapter=new ArticleAdapter(articleLists,getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}