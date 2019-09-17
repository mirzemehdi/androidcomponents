package com.mmk.androidcomponents.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.mmk.androidcomponents.Util.Constants.USER_TABLE_NAME;

@Entity(tableName = USER_TABLE_NAME)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String displayName;
    private int age;

    public User(String displayName, int age) {
        this.displayName = displayName;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getAge() {
        return age;
    }


    @Override
    public boolean equals(Object obj) {

        User user=(User)obj;
        return this.getDisplayName().equals(user.getDisplayName())&&
                this.getAge()==user.getAge()&&
                this.getId()==user.getId();
    }
}
