package com.training.todo_list.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.training.todo_list.model.models.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by OrevonPC6 on 13/03/2017.
 */
public class SqliteHandlerUtils extends SQLiteOpenHelper
{
    private static final String sDATABASE_NAME = "todoaltagem";
    private static final int sDATABASE_VERSION = 1;
    private static final String sTAG = SqliteHandlerUtils.class.getSimpleName();


    private TodoHandler mTodoHandler;
    public SqliteHandlerUtils(Context pcontext) {
        super(pcontext, sDATABASE_NAME, null, sDATABASE_VERSION);
        mTodoHandler = new TodoHandler();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tCreateTable = mTodoHandler.createTable();
        sqLiteDatabase.execSQL(tCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(mTodoHandler.deleteTable());
    }
    public void addTodo(String pDescription, UUID pType) {
        SQLiteDatabase db = this.getWritableDatabase();
        String req = mTodoHandler.insertTodo();

        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("LLLL d, y");
        String created_at = df.format(d);
        Log.w("REQ insert:: ", req);
        try
        {
            db.execSQL(req,new Object[]{pDescription,pType,0});
        }catch(Exception e)
        {
            Log.e("ERREUR!::: ","Erreur lors de l'insertion");
            e.printStackTrace();
        }

        db.close(); // Closing database connection
        Log.d(sTAG, "New TODO inserted into sqlite: " );
    }

    public ArrayList<Todo> ListeTodo()
    {
        ArrayList<Todo> tTodoList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " +TodoHandler.sTodoTableName+" ;" ;
        Log.d("REQ ** ",selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {
                Log.e("$$$$$$$$$$$$$$$$$$   ",cursor.getString(1)+" | "+ cursor.getString(2)+" | "+ cursor.getString(3));
                //tTodoList.add(new Todo())
            }
        } finally {
            cursor.close();
        }
        db.close();
        return tTodoList;
    }

}
