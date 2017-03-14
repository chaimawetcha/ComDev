package com.training.todo_list.utils;

/**
 * Created by OrevonPC6 on 19/04/2016.
 */
public class TodoHandler
{
    /*

     mSDescription;
    private Date mTimeCreation;
    @Nullable private UUID mIdTodoType;
    private boolean mBIsDone;
    private UUID mId;
     */
    public static final String sTodoTableName="todo";
    public static  final String sDescription="description";
    public static  final String sTimeCreation="time";
    public static  final String sType="type";
    public static  final String sIsDone="isdone";
    public static  final String sId="id";


    public String createTable()
    {
        String rCREATE_USER_TABLE = "CREATE TABLE " + sTodoTableName + "("
                + sId + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + sDescription + " TEXT, "
                +sTimeCreation + " TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ,"
                + sType + " TEXT, "
                + sIsDone + " INTEGER)";

        return rCREATE_USER_TABLE;
    }

    public String deleteTable()
    {
        return "DROP TABLE IF EXISTS " + sTodoTableName;
    }

    public String insertTodo()
    {
                String req = "INSERT INTO "+sTodoTableName
                +"("+sDescription+", "
                +sType+", "
                +sIsDone+" "
                +") VALUES (?,?,?)";

        return req;
    }
}