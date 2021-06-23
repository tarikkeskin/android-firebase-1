package com.tictactoe.fb_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FB_listview extends AppCompatActivity {

    ListView FB_listview;

    int[] FB_images= {R.drawable.manti,
    R.drawable.iskender,
    R.drawable.kebab};

    String[] FB_names = {"manti","iskender","kebab"};

    int[] FB_fiyat={12,23,25};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_b_listview);

        FB_listview = (ListView) findViewById(R.id.listView);

        CustomAdaptor customAdaptor =new CustomAdaptor();
        FB_listview.setAdapter(customAdaptor);
    }

    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return FB_images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView mImageView = view.findViewById(R.id.imageView3);

            TextView mTextView = view.findViewById(R.id.textView);

            mImageView.setImageResource(FB_images[position]);
            mTextView.setText(FB_names[position]+FB_fiyat[position]);

            return view;
        }
    }
}