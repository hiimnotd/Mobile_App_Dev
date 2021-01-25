package vn.edu.usth.twitterclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder> {
    Context context;
    List<ModelUsers> usersList;

    public AdapterUsers(Context context, List<ModelUsers> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public AdapterUsers.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.user, viewGroup, false);

        return new AdapterUsers.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        String username = usersList.get(i).getUsername();
        String name = usersList.get(i).getName();

        holder.nameView.setText(name);
        holder.usernameView.setText(username);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView nameView, usernameView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.FullName);
            usernameView = itemView.findViewById(R.id.username);
        }
    }
}
