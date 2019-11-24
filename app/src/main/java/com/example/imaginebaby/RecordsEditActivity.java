package com.example.imaginebaby;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RecordsEditActivity extends AppCompatActivity {

    private EditText edit_prev_name, edit_new_name, edit_birth;
    private Button btn_change;
    private int gender = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_edit);

        btn_change = (Button)findViewById(R.id.btn_change);


        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick btn_change", "success");
                change_btn();
            }
        });

    }

    public void change_btn() {
        Log.d("change_btn()", "호출");

        edit_new_name = (EditText) findViewById(R.id.edit_new_name);
        edit_prev_name = (EditText) findViewById(R.id.edit_prev_name);
        edit_birth = (EditText) findViewById(R.id.edit_birth);

        String new_baby_name = edit_new_name.getText().toString();
        String prev_baby_name = edit_prev_name.getText().toString();
        String birth = edit_birth.getText().toString();

        if (new_baby_name.equals("") || prev_baby_name.equals("") || birth.equals("") || gender == 2) {
            Toast.makeText(getApplicationContext(), "꼭 입력해 주세요.", Toast.LENGTH_LONG).show();
        } else if (new_baby_name.equals(" ") || prev_baby_name.equals(" ") || birth.equals(" ")) {
            Toast.makeText(getApplicationContext(), "공백 금지", Toast.LENGTH_LONG).show();
        }

    }

    public void EditViewXClicked(View view){
        finish();
    }


}
