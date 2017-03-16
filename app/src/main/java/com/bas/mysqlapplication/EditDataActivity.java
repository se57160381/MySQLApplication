package com.bas.mysqlapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        final TodoList editTodoList = (TodoList) getIntent().getSerializableExtra("editTodoList");

        final EditText editText = (EditText) findViewById(R.id.editTextEditTask);
        editText.setText(editTodoList.getTaskname());

        Button editBtn = (Button)findViewById(R.id.btnSaveEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList eTodoList = new TodoList();
                eTodoList.setTaskid(editTodoList.getTaskid());
                eTodoList.setTaskname(String.valueOf(editText.getText()));

                TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
                todoListDAO.open();
                todoListDAO.update(eTodoList);
                todoListDAO.close();

                finish();
            }

        });

        /////////////////////ลบ ///////////////////
        Button delBtn = (Button) findViewById(R.id.delete_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoListDAO todoListDAODel = new TodoListDAO(getApplicationContext());

                todoListDAODel.open();
                todoListDAODel.delete(editTodoList);
                todoListDAODel.close();

                finish();
            }
        });
    }
}
