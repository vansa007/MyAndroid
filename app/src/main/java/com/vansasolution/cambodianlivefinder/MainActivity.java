package com.vansasolution.cambodianlivefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button home = (Button)findViewById(R.id.button_home);
        Button finder = (Button)findViewById(R.id.button_finder);

        //handle Home button
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: home here
            }
        });

        //handle Finder button
        finder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMap = new Intent(MainActivity.this, FinderByMapsActivity.class);
                startActivity(goMap);
            }
        });

    }
}
