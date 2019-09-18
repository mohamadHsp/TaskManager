package com.google.taskmanager.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.taskmanager.Controller.TaskRepository;
import com.google.taskmanager.Model.Task;
import com.google.taskmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ShowTasksFragmnt extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private List<Task> Tasks = TaskRepository.getInstance().getTaskList();
    List<Task> ToDoTasks = new ArrayList<>();
    List<Task> DoingTasks = new ArrayList<>();
    List<Task> DoneTasks = new ArrayList<>();
    private int mParam1;

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;

    public ShowTasksFragmnt() {
    }

    public static ShowTasksFragmnt newInstance(int param1) {
        ShowTasksFragmnt fragment = new ShowTasksFragmnt();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_tasks, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_task);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fillRecycleerView();
        fillRecyclretViewAdapter();
        return view;
    }

    public void fillRecycleerView() {
        if (Tasks != null) {
            for (Task tsk : Tasks) {
                if (tsk.getInitialDate().before(tsk.getExpireDate()) && !tsk.isDone()) {
                    ToDoTasks.add(tsk);
                } else if (tsk.getInitialDate().before(tsk.getExpireDate()) && tsk.isDone()) {
                    DoneTasks.add(tsk);
                } else {
                    DoingTasks.add(tsk);
                }
            }
        }
    }

     public void fillRecyclretViewAdapter() {
       if (mParam1 == 0) {
           if (mTaskAdapter == null) {
               mTaskAdapter = new TaskAdapter(ToDoTasks);
               mRecyclerView.setAdapter(mTaskAdapter);
           }
           else
               mTaskAdapter.notifyDataSetChanged();
       }
         if (mParam1 == 1) {
             if (mTaskAdapter == null) {
                 mTaskAdapter = new TaskAdapter(DoingTasks);
                 mRecyclerView.setAdapter(mTaskAdapter);
             }
             else
                 mTaskAdapter.notifyDataSetChanged();
         }
         if (mParam1 == 2) {
             if (mTaskAdapter == null) {
                 mTaskAdapter = new TaskAdapter(DoneTasks);
                 mRecyclerView.setAdapter(mTaskAdapter);
             }
             else
                 mTaskAdapter.notifyDataSetChanged();
         }
    }


    @Override
    public void onResume() {
        super.onResume();
        fillRecyclretViewAdapter();
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{

        private List<Task> mTasks;

        public TaskAdapter(List<Task> Tasks){
            mTasks = Tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.row_of_task, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            holder.bindTask(mTasks.get(position));
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

    }

    private class TaskHolder extends RecyclerView.ViewHolder{

        private TextView mTextViewTitle;
        private TextView mTextViewDesc;
        private CheckBox mCheckBoxDone;
        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.title_text_view_row_of_rec);
            mTextViewDesc = itemView.findViewById(R.id.desc_text_view_row_of_rec);
            mCheckBoxDone = itemView.findViewById(R.id.done_check_box_row_of_rec);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager manager = getFragmentManager();
                    TaskDetailFragment detailFragment = TaskDetailFragment.newInstance(mTask);
                    detailFragment.show(manager,"DetailFragmentIsShow");
                }
            });
        }

        public void bindTask(Task task){
            mTask = task;
            mTextViewTitle.setText(task.getTitle());
            mTextViewDesc.setText(mTask.getDescription());
            mCheckBoxDone.setChecked(mTask.isDone());
        }
    }

}
