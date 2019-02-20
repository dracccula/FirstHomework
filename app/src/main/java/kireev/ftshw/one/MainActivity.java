package kireev.ftshw.one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_COLOR = "color";
    private Button open2activitybutton;
    public static TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String messageText = intent.getStringExtra(EXTRA_MESSAGE_COLOR);
        text = findViewById(R.id.textView);
        text.setText(messageText);
        open2activitybutton = findViewById(R.id.launchSecondActivityButton);
        open2activitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //открываем активити
                openActivity2();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null) {return;}

        text.setText(intent.getStringExtra(EXTRA_MESSAGE_COLOR));
    }

    private void openActivity2() {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivityForResult(intent,1);
    }

}