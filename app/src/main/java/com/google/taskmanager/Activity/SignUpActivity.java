package com.google.taskmanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.taskmanager.Controller.TaskRepository;
import com.google.taskmanager.Model.Task;
import com.google.taskmanager.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEditTextUsrName;
    private EditText mEditTextPassWord;

    private Button mButtonAddNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();

        mButtonAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = mEditTextUsrName.getText().toString().trim();
                String PassWord = mEditTextPassWord.getText().toString().trim();
                TaskRepository.getInstance().signUp(UserName,PassWord);
                LoginActivity.newIntent(SignUpActivity.this);
            }
        });
    }

    public void initUI(){
        mButtonAddNewUser = findViewById(R.id.add_new_user_btn);
        mEditTextUsrName = findViewById(R.id.add_user_name_edit_text);
        mEditTextPassWord = findViewById(R.id.add_pass_word_edit_text);
    }
}
