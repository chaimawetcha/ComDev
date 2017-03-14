package com.training.todo_list.activities.todo_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;
import com.training.todo_list.utils.SqliteHandlerUtils;

import java.util.ArrayList;
import java.util.UUID;

public class AddTodo extends AppCompatActivity {

    Spinner mspinner;
    ImageButton msave ;
    SqliteHandlerUtils mDb;
    EditText mDescriptionEditText;
    UUID mSelectedType;
    TodoManager mtodoManager;
    TodoTypeManager mtodoTypeManager;
    ArrayList<TodoType> mobjects;
    Todo mTodo;
    Boolean mIsEdit =false;
    int mPositionToEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        mtodoManager = new TodoManager();
        mtodoTypeManager = new TodoTypeManager();

        mspinner = (Spinner) findViewById(R.id.spinner);
        msave = (ImageButton)findViewById(R.id.bt_save);
        mDb = new SqliteHandlerUtils(getApplicationContext());
        mDb.ListeTodo();
        mDescriptionEditText = (EditText)findViewById(R.id.editText);

        Bundle lBundle = getIntent().getExtras();
        if(lBundle != null)
        {
            mTodo = (Todo) lBundle.getSerializable("todo");
            mDescriptionEditText.setText(mTodo.description());
            mIsEdit = true;
            mPositionToEdit = lBundle.getInt("position");
        }

        mobjects = (ArrayList<TodoType>) mtodoTypeManager.all();
        ArrayAdapter<TodoType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mobjects);
        mspinner.setAdapter(adapter);

        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("objects selected : "+mobjects.get(position).toString());
                mSelectedType = mobjects.get(position).id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void mSaveNewTodo(View pView)
    {
       // mDb.addTodo(mDescriptionEditText.getText().toString(),mSelectedType);
        if(mIsEdit)
        {
            mTodo.description(mDescriptionEditText.getText().toString());
            mTodo.idTodoType(mSelectedType);
            System.out.println("yyyyyyyyyy \n"+mTodo.toString());
            mtodoManager.replace(mTodo);
        }
        else
        {
            mtodoManager.create(mDescriptionEditText.getText().toString(),new java.util.Date(),mSelectedType,false);
            Log.w("la liste !! ",mtodoManager.all().toString());
        }

        Toast.makeText(this,"Save with success",Toast.LENGTH_LONG).show();
        mDescriptionEditText.setText("");
        finish();
    }
}
