package com.example.user.ref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";






    /** Called when the user taps the Fractals button */
    public void goToFractals(View view) {
        Intent intent = new Intent(this, FractalsActivity.class);
       /* EditText editText = (EditText) findViewById(R.id);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
    }

    /** Called when the user taps the Randomness button */
    public void goToRandomness(View view) {
        Intent intent = new Intent(this, RandomnessActivity.class);
      /*  EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
    }


}
