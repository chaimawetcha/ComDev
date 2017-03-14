package com.training.todo_list.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.models.TodoType;

import java.util.ArrayList;

/**
 * Created by OrevonPC6 on 14/03/2017.
 */
public class SpinnerAdapter  extends ArrayAdapter<TodoType> {

    TodoTypeManager mTodoTypeManager;
    Context mContext;

    public SpinnerAdapter(Context pContext, ArrayList<TodoType> pUsers) {
        super(pContext, 0, pUsers);
        mTodoTypeManager = new TodoTypeManager();
        mContext = pContext;
    }

    @Override
    public View getView(final int pPosition, View pConvertView, ViewGroup pParent) {
        final TodoType lType = getItem(pPosition);


        if (pConvertView == null) {
            pConvertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner, pParent, false);
        }


        TextView lSpisserText = (TextView) pConvertView.findViewById(R.id.spinner_text);
        lSpisserText.setText(lType.name().toString());
        return pConvertView;
    }
}