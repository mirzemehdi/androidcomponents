package com.mmk.androidcomponents;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mmk.androidcomponents.Adapter.UsersRecylerViewAdapter;
import com.mmk.androidcomponents.Model.User;
import com.mmk.androidcomponents.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button addUserBtn;
    private RecyclerView usersRecylerView;
    private UsersRecylerViewAdapter adapter;
    private List<User>userList;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList=new ArrayList<>();
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        adapter=new UsersRecylerViewAdapter(this);



        usersRecylerView=(RecyclerView)findViewById(R.id.usersRecylerView);
        addUserBtn=(Button)findViewById(R.id.addUserBtn);
        usersRecylerView.setHasFixedSize(true);
        usersRecylerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecylerView.setAdapter(adapter);


        userViewModel.getAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (adapter!=null)
                adapter.submitList(users);
            }
        });






            //Insert
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(null);



            }
        });


        //Update

        adapter.setUserClickListener(new UsersRecylerViewAdapter.IUserClickListener() {
            @Override
            public void onClick(User user) {
                showAlertDialog(user);
            }
        });

        //Delete with swipe

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                User user=adapter.getUserAtPosition(position);
                userViewModel.delete(user);
                Toast.makeText(MainActivity.this,"User is deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(usersRecylerView);







    }

    public void showAlertDialog(final User user){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.user_alert_dialog_view,null);
        builder.setView(view);

        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
        final EditText nameEditText,ageEditText;
        Button saveBtn;
        nameEditText=view.findViewById(R.id.user_displayname_alert_dialog);
        ageEditText=view.findViewById(R.id.user_age_alert_dialog);
        saveBtn=view.findViewById(R.id.user_btnSave_alert_dialog);
        if (user!=null){
            nameEditText.setText(user.getDisplayName());
            ageEditText.setText(String.valueOf(user.getAge()));
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayName=nameEditText.getEditableText().toString();
                String age=ageEditText.getEditableText().toString();
                User newUser=new User(displayName,Integer.valueOf(age));

                if (user!=null){
                    newUser.setId(user.getId());
                    userViewModel.update(newUser);
                    Toast.makeText(MainActivity.this,"User is updated",Toast.LENGTH_SHORT).show();
                }

                else {
                    userViewModel.insert(newUser);
                    Toast.makeText(MainActivity.this,"User is added",Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }

        });



    }
}
