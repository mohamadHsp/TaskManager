package com.google.taskmanager.Controller;

import com.google.taskmanager.Model.Task;
import com.google.taskmanager.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository ourInstance;
    //.................Fields.................//
    private List<Task> mTaskList;
    private List<User> mUserList;
    private User mCurrectUser;
    private boolean isAdmin;
    //................methods..................//
    public static TaskRepository getInstance() {
        if (ourInstance == null)
            ourInstance = new TaskRepository();
        return ourInstance;
    }

    private TaskRepository() {
        mTaskList = new ArrayList<>();
        mUserList = new ArrayList<>();
        addSimpleTask();
    }

    private void addSimpleTask() {
        for (int i = 0 ; i < 100 ; i++){
            Task task = new Task();
            task.setTitle("Tast" + i);
            task.setDescription("it is Task" + i);
            task.setDone(true);
            addTask(task);
        }
    }

    public List<User> getUserList(){
        if (isAdmin)
        return mUserList;
        return null;
    }

    public void addUser(User newUser){
        mUserList.add(newUser);
        mCurrectUser = newUser;
    }

    public void signUp(String userName , String PassWord){
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassWord(PassWord);
    }

    public boolean login(String userName , String passWord){
        for (User usr:mUserList) {
            if (usr.getUserName().equals(userName))
                if (usr.getPassWord().equals(passWord)) {
                    if (userName.equals("Admin"))
                        if (passWord.equals("1111")) {
                            mCurrectUser = usr;
                            isAdmin = true;
                            return true;
                        }
                    mCurrectUser = usr;
                    return true;
                }
        }
        return false;
    }

    public void removeuser(UUID UserID){
        if (isAdmin)
        if (isHaveCurrectUser()) {
            for (User user : mUserList) {
                if (user.getUserID().equals(UserID)) {
                    mUserList.remove(user);
                    return;
                }
            }
        }
    }

    public Task findTask(UUID taskID) {
        if (isHaveCurrectUser()) {
            for (Task task : mTaskList) {
                if (task.getID().equals(taskID)) {
                    return task;
                }
            }
        }
        return null;
    }

    public User findUser(UUID UserID){
        if (isAdmin)
        {
            for (User usr:mUserList) {
                if (usr.getUserID().equals(UserID))
                    return usr;
            }
        }
        return null;
    }

    public boolean isHaveCurrectUser(){
        if (mCurrectUser != null)
            return true;
        return false;
    }

    public List<Task> getTaskList() {
        if (isHaveCurrectUser())
            return mTaskList;
        return null;
    }

    public void addTask(Task task){
        if (isHaveCurrectUser())
        mTaskList.add(task);
    }

    public void removeTask(UUID taskID) {
        if (isHaveCurrectUser()) {
            for (Task task : mTaskList) {
                if (task.getID().equals(taskID)) {
                    mTaskList.remove(task);
                    return;
                }
            }
        }
    }

    }
