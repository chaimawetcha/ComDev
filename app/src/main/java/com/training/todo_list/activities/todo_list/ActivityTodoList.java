package com.training.todo_list.activities.todo_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.utils.TodoAdapter;

import java.util.ArrayList;


public class ActivityTodoList extends Activity {


    ListView mListView;
    ArrayList<Todo> mListTodo;
    TodoManager mTodoManager;
    TodoAdapter mTodoAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("on resmue",  mTodoManager.all().toString());
        mListTodo = (ArrayList<Todo>) mTodoManager.all();
        mTodoAdapter = new TodoAdapter(this,mListTodo);
        mListView.setAdapter(mTodoAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);

        mTodoManager = new TodoManager();
        mListTodo = (ArrayList<Todo>) mTodoManager.all();
        mListView = (ListView)findViewById(R.id.list);


        mTodoAdapter = new TodoAdapter(this,mListTodo);
        mListView.setAdapter(mTodoAdapter);

    }


    public void askAddTodo(View pView) {

        Intent i = new Intent(ActivityTodoList.this, AddTodo.class);
        startActivity(i);

    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }
}
