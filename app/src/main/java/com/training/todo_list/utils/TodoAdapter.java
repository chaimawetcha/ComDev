package com.training.todo_list.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.activities.todo_list.AddTodo;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.Todo;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<Todo> {

    TodoTypeManager mTodoTypeManager;
    Context mContext;

    public TodoAdapter(Context pContext, ArrayList<Todo> pUsers) {
        super(pContext, 0, pUsers);
        mTodoTypeManager = new TodoTypeManager();
        mContext = pContext;
    }

    @Override
    public View getView(final int pPosition, View pConvertView, ViewGroup pParent) {
        final Todo luser = getItem(pPosition);
        String lSColor =mTodoTypeManager.todoTypeFor(luser.idTodoType()).color();


        if (pConvertView == null) {
            pConvertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, pParent, false);
        }
        TextView ltvName = (TextView) pConvertView.findViewById(R.id.todo_text);
        pConvertView.setBackgroundColor(Color.parseColor(lSColor));
        System.out.println(mTodoTypeManager.todoTypeFor(luser.idTodoType()));
        ltvName.setText(luser.description());

        pConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, AddTodo.class);
                Bundle lb = new Bundle();
                lb.putSerializable("todo",luser);
                lb.putInt("position",pPosition);
                i.putExtras(lb);
                mContext.startActivity(i);
            }
        });
        return pConvertView;
    }
}