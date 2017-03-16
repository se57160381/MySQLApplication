package com.bas.mysqlapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by BassAye on 10/2/2560.
 */

public class TodoListDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public TodoListDAO (Context context){
        //get context and sent to helper
        dbHelper = new DbHelper (context);
    }
    //open database
    public  void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //เปลี่ยนจาก string เป็น TodoList
    public ArrayList<TodoList> getAllTodoList(){
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM tbTodo_list;", null); // database ชื่อนี้ต้องเหมือน บรรทัด 14
        cursor.moveToFirst();

        //add TodoList เรียน 23/2/60
        TodoList todoList1;

        while (!cursor.isAfterLast()){

            //add TodoList
            todoList1 = new TodoList();
            todoList1.setTaskid(cursor.getInt(0));
            todoList1.setTaskname(cursor.getString(1));

            todoList.add(todoList1);
            cursor.moveToNext();

        }
        cursor.close();
        return todoList;
    }

    public void add(TodoList todoList) {
        TodoList newTodoList = new TodoList();
        newTodoList = todoList;

        ContentValues values = new ContentValues();

        values.put("taskname", newTodoList.getTaskname());
        this.database.insert("tbTodo_list", null,values);

        Log.d("Todo List Demo :::", "Add OK");
    }

    public void update(TodoList todoList){
        TodoList updateTodoList = todoList;
        ContentValues values = new ContentValues();
        values.put("taskname",updateTodoList.getTaskname());
        values.put("taskid",updateTodoList.getTaskid());

        String where = "taskid=" + updateTodoList.getTaskid();

        this.database.update("tbtodo_list",values,where, null );
        Log.d("Todo_list Demo", "Update OK");
    }

    public void delete(TodoList todoList){
        TodoList delTodoList = todoList;
        String sqlText = "DELETE FROM tbtodo_list WHERE taskid=" + delTodoList.getTaskid();
        this.database.execSQL(sqlText);
    }
}

