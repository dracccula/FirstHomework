package kireev.ftshw.one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static kireev.ftshw.one.SecondActivity.mProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button button;
    public static TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.circleProgressBar);
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        button = findViewById(R.id.launchSecondActivityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //открываем активити
                openActivity2();
                mProgressBar.setVisibility(ProgressBar.VISIBLE);
            }
        });



    }

    private void openActivity2() {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

}