package com.google.taskmanager.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.taskmanager.Model.Task;
import com.google.taskmanager.R;


public class TaskDetailFragment extends AppCompatDialogFragment {
    private static final String Task_ARG = "unique_task";
    private static final String ARG_PARAM2 = "param2";

    private Task mTask = new Task();
    private TextView mTextViewTitle;
    private TextView mTextViewDes;
    private TextView mTextViewInitialTime;
    private TextView mTextViewExpireDate;

    public TaskDetailFragment() {
        // Required empty public constructor
    }

    public static TaskDetailFragment newInstance(Task task) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(Task_ARG, task);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View TaskShow = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_task_detail,null);
        initUI(TaskShow);
        setContentOnView();

        return new AlertDialog.Builder(getActivity())
                .setTitle(mTask.getTitle())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                    }
                })
                .setView(TaskShow)
                .create();
    }

    private void setContentOnView() {
        mTextViewTitle.setText(mTask.getTitle());
        mTextViewDes.setText(mTask.getDescription());
        mTextViewInitialTime.setText(mTask.getInitialDate().toString());
        mTextViewExpireDate.setText(mTask.getExpireDate().toString());
    }

    private void initUI(View taskShow) {
        mTextViewTitle = taskShow.findViewById(R.id.title_text_view);
        mTextViewDes = taskShow.findViewById(R.id.des_text_view);
        mTextViewInitialTime = taskShow.findViewById(R.id.initial_time_text_view);
        mTextViewExpireDate = taskShow.findViewById(R.id.expire_date_text_view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTask = (Task) getArguments().getSerializable(Task_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false);
    }

}
