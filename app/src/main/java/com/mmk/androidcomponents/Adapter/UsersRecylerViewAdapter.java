package com.mmk.androidcomponents.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mmk.androidcomponents.Model.User;
import com.mmk.androidcomponents.R;

import java.util.List;

public class UsersRecylerViewAdapter extends ListAdapter<User,UsersRecylerViewAdapter.ViewHolder> {


    private Context context;
    private IUserClickListener userClickListener;



    public UsersRecylerViewAdapter(Context context) {

        super(diffCallback);
        this.context=context;
    }

   private static DiffUtil.ItemCallback<User> diffCallback=new DiffUtil.ItemCallback<User>() {
       @Override
       public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
           return oldItem.getId()==newItem.getId();
       }

       @Override
       public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
           return oldItem.equals(newItem);
       }
   };

    @NonNull
    @Override
    public UsersRecylerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersRecylerViewAdapter.ViewHolder holder, int position) {
        final User user=getItem(position) ;
        Log.d("UserDispName",user.getDisplayName());
        holder.displayNameTextView.setText(user.getDisplayName());
        holder.ageTextView.setText(String.valueOf(user.getAge()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClickListener.onClick(user);
            }
        });

    }





    public User getUserAtPosition(int position) {


        return getItem(position);
    }

    public void setUserClickListener(IUserClickListener userClickListener){
        this.userClickListener=userClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView displayNameTextView,ageTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            displayNameTextView=(TextView)itemView.findViewById(R.id.row_user_displayname);
            ageTextView=(TextView)itemView.findViewById(R.id.row_user_age);
        }
    }

    public interface IUserClickListener{
        void onClick(User user);
    }
}
