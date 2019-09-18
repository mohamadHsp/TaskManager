package com.google.taskmanager.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.taskmanager.Controller.TaskRepository;
import com.google.taskmanager.Model.Task;
import com.google.taskmanager.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddNewTaskFragment extends DialogFragment {

    private TextInputEditText mTextInputEditTextTitle;
    private TextInputEditText mTextInputEditTextDesc;
    //..................................................//
    private DatePicker mDatePickerDo;
    private TimePicker mTimePickerDo;
    //..................................................//
    private Button mButtonAddTask;
    private Button mButtonSetTimeDo;
    private Button mButtonSetDateDo;
    //..................................................//
    private CheckBox mCheckBoxDone;


    public AddNewTaskFragment() {
        //Required empty public constructor
    }

    public static AddNewTaskFragment newInstance() {
        AddNewTaskFragment fragment = new AddNewTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_task, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mButtonSetTimeDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimePickerDo.setVisibility(View.VISIBLE);
            }
        });

        mButtonSetDateDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDo.setVisibility(View.VISIBLE);
            }
        });

        mButtonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task();
                task.setTitle(mTextInputEditTextTitle.getText().toString());
                task.setDescription(mTextInputEditTextDesc.getText().toString());
                task.setDone(false);
                String time;
                if (mDatePickerDo.getVisibility() == View.VISIBLE){
                    int day = mDatePickerDo.getDayOfMonth();
                    int month = mDatePickerDo.getMonth();
                    int year = mDatePickerDo.getYear();
                    Calendar calendar = new GregorianCalendar(year,month,day);
                    task.setExpireDate(calendar.getTime());
                }
                if (mTimePickerDo.getVisibility() == View.VISIBLE){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        int hour = mTimePickerDo.getHour();
                        int min = mTimePickerDo.getMinute();
                        time = hour + ":" + min ;
                        task.setEpireTime(time);
                    }
                }
                TaskRepository.getInstance().addTask(task);
            }
        });

    }

    public void initUI(View view){
        //..............................EditText..................................//
        mTextInputEditTextTitle = view.findViewById(R.id.title_editText_add);
        mTextInputEditTextDesc = view.findViewById(R.id.desc_editText_add);
        //..............................Pickers...............................//
        mDatePickerDo = view.findViewById(R.id.Date_picker_do);
        mDatePickerDo.setVisibility(View.GONE);
        mTimePickerDo = view.findViewById(R.id.Time_picker_do);
        mTimePickerDo.setVisibility(View.GONE);
        //..............................Buttons...............................//
        mButtonAddTask = view.findViewById(R.id.addTask_btn);
        mButtonSetTimeDo = view.findViewById(R.id.setDoTime_btn);
        mButtonSetDateDo = view.findViewById(R.id.setDoDate_btn);
        //.............................CheckBox................................//
        mCheckBoxDone = view.findViewById(R.id.add_done_checkbox_add);
        mCheckBoxDone.setEnabled(false);
    }
}
