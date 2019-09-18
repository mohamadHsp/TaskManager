package com.google.taskmanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.taskmanager.Controller.TaskRepository;
import com.google.taskmanager.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextUserName;
    private EditText mEditTextPassWord;

    private Button mButtonLogIn;
    private Button mButtonSignUp;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();

        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrName = mEditTextUserName.getText().toString().trim();
                String PassWord = mEditTextPassWord.getText().toString().trim();
                if (TaskRepository.getInstance().login(usrName , PassWord)){
                    MainActivity.newIntent(LoginActivity.this);
                }
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void initUI(){
        mEditTextPassWord = findViewById(R.id.pass_word_login);
        mEditTextUserName = findViewById(R.id.user_name_login);
        mButtonLogIn = findViewById(R.id.log_in_button);
        mButtonSignUp = findViewById(R.id.sign_up_button);
    }
}
