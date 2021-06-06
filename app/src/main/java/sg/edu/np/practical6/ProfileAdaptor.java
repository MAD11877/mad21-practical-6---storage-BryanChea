package sg.edu.np.practical6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdaptor extends RecyclerView.Adapter<ProfileViewHolder> {
    ArrayList<User> profiles;
    Activity myActivity;

    public ProfileAdaptor(ArrayList<User> userList, Activity listActivity) {
        profiles = userList;
        myActivity = listActivity;
    }

    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        User user = profiles.get(viewType);
        View item;

        if (user.getName().endsWith("7")) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.obj_layout_special7, parent, false);
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.obj_layout, parent, false);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
        builder.setTitle("Profile");
        builder.setMessage(user.getName());
        builder.setCancelable(false);

        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle userInfo = new Bundle();
                userInfo.putString("Name", user.getName());
                userInfo.putSerializable("User", user);
                Intent intent = new Intent(myActivity, MainActivity.class);
                intent.putExtras(userInfo);
                myActivity.startActivity(intent);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        item.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return new ProfileViewHolder(item);
    }

    public void onBindViewHolder(ProfileViewHolder vh, int pos) {
        User users = profiles.get(pos);
        vh.t1.setText(users.getName());
        vh.t2.setText(users.getDesc());
    }

    public int getItemCount() {
        return profiles.size();
    }

    @Override
    public int getItemViewType(int pos) {
        return pos;
    }
}
