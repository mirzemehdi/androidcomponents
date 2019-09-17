package com.mmk.androidcomponents.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mmk.androidcomponents.DAO.UserDao;
import com.mmk.androidcomponents.Model.User;
import com.mmk.androidcomponents.Util.Constants;

@Database(entities = {User.class},version = Constants.DATABASE_VERSION,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;
    public abstract UserDao userDao();


    public static synchronized UserDatabase getInstance(Context context) {

        if (instance==null){

          instance=Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}
