package com.mmk.androidcomponents.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mmk.androidcomponents.Model.User;
import com.mmk.androidcomponents.Util.Constants;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM "+ Constants.USER_TABLE_NAME+" ORDER BY age")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE FROM "+Constants.USER_TABLE_NAME)
    void deleteAll();

}
