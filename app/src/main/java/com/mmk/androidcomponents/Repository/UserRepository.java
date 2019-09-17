package com.mmk.androidcomponents.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.mmk.androidcomponents.DAO.UserDao;
import com.mmk.androidcomponents.Model.User;
import com.mmk.androidcomponents.RoomDatabase.UserDatabase;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> userList;

    public UserRepository(Application application) {

        userDao= UserDatabase.getInstance(application).userDao();
        userList= userDao.getAllUsers();
    }


    public void insert(User user){

        new InsertAsyncTask(userDao).execute(user);
    }


    public void update(User user){
        new UpdateAsyncTask(userDao).execute(user);
    }

    public void delete(User user){
        new DeleteAsyncTask(userDao).execute(user);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(userDao).execute();
    }

    public LiveData<List<User>> getAll(){
        return userDao.getAllUsers();
    }


    private static class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            Log.d("UserRepoInsert","Repo Insert is called    +User.name: "+users[0].getDisplayName());
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public DeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        public DeleteAllAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }

}
