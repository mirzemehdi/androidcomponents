package com.mmk.androidcomponents.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mmk.androidcomponents.Model.User;
import com.mmk.androidcomponents.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> userList;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository=new UserRepository(application);
        userList=userRepository.getAll();

    }

    public void insert(User user){
        userRepository.insert(user);
    }

    public void update(User user){
        userRepository.update(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public LiveData<List<User>> getAll(){
        return userRepository.getAll();
    }
}
