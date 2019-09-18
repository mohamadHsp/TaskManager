package com.google.taskmanager.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.taskmanager.Fragments.AddNewTaskFragment;
import com.google.taskmanager.Fragments.ShowTasksFragmnt;
import com.google.taskmanager.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //...............Fields...................................//

    private ViewPager mViewForFragment;
    private FloatingActionButton mFloatingActionButton;
    private Button mButtonTodo;
    private Button mButtonDoing;
    private Button mButtonDone;
    private int mViewPagerPossition;

    //.......................Methods............................//

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        initUI();

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                AddNewTaskFragment addTask = AddNewTaskFragment.newInstance();
                addTask.show(manager,"DialogFragmentForAddNewTask");
            }
        });

        mViewForFragment.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                mViewPagerPossition = position;
                ShowTasksFragmnt ms = ShowTasksFragmnt.newInstance(mViewPagerPossition);
                return ms;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mViewForFragment.setCurrentItem(mViewPagerPossition);
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_usr_item:

                break;
            case R.id.setting_item:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void initUI() {
        mViewForFragment = findViewById(R.id.fragment_task_list);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
        mButtonTodo = findViewById(R.id.to_do_btn);
        mButtonDoing = findViewById(R.id.doing_btn);
        mButtonDone = findViewById(R.id.done_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.to_do_btn:
                mViewPagerPossition = 0;
                mViewForFragment.setCurrentItem(mViewPagerPossition);
                break;
            case R.id.doing_btn:
                mViewPagerPossition = 1;
                mViewForFragment.setCurrentItem(mViewPagerPossition);
                break;
            case R.id.done_btn:
                mViewPagerPossition = 2;
                mViewForFragment.setCurrentItem(mViewPagerPossition);
                break;
        }
    }
}
