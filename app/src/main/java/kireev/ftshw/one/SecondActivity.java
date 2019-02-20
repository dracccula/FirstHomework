package kireev.ftshw.one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {

    private ImageButton red;
    private ImageButton yellow;
    private ImageButton green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        red = findViewById(R.id.RedButton);
        yellow = findViewById(R.id.YellowButton);
        green = findViewById(R.id.GreenButton);

        //слушаем красную кнопку
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redColor = "RED!";
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, redColor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //слушаем желтую кнопку
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yellowColor = "YELLOW!";
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, yellowColor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //слушаем зеленую кнопку
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String greenColor = "GREEN!";
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, greenColor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}


