package sg.edu.np.practical6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Create Activity";
    private User user;

    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "Create Activity");

        TextView name = findViewById(R.id.textView3);
        TextView desc = findViewById(R.id.textView);
        Button followed = findViewById(R.id.button);
        Button msg = findViewById(R.id.button2);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("User");
        name.setText(user.getName());
        desc.setText(user.getDesc());

        if (user.isFollowed()) {
            followed.setText("Unfollow");
        } else {
            followed.setText("Follow");
        }

        followed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.isFollowed()){
                    followed.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", '0').show();
                }
                else{
                    followed.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", '0').show();
                }
                user.setFollowed(!user.isFollowed());
                dbHandler.updateUser(user);
                //Troubleshoot
                // System.out.println(user.isFollowed());
            }
        });
    }
}