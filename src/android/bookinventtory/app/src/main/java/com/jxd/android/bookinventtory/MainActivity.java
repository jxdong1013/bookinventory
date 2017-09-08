package com.jxd.android.bookinventtory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_booksearch:
                    mTextMessage.setText(R.string.title_booksearch);
                    return true;
                case R.id.navigation_shelfsearch:
                    mTextMessage.setText(R.string.title_shelfsearch);
                    return true;
                case R.id.navigation_shelfadapt:
                    mTextMessage.setText(R.string.title_shelfadapt);
                    return true;
                case R.id.navigation_shelfarrage:
                    mTextMessage.setText(R.string.title_shelfarrage);
                    return true;
                case R.id.navigation_differencemanage:
                    mTextMessage.setText(R.string.title_differencemanage);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
