package com.example.lav7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);


        imageView.setImageResource(R.drawable.tree);
        textView.setText("Дерево");
    }


    public void goBack(View view) {
        finish();
    }
}
