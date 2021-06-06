package sg.edu.np.practical6;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final String TAG = "Create Activity";

    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if (dbHandler.getUsers().size() == 0) {
            User user;
            String name;
            String desc;
            Random rand = new Random();
            for (int i = 1; i < 21; i++) {
                name = "Name";
                desc = "Description ";
                name += String.valueOf(rand.nextInt());
                desc += String.valueOf(rand.nextInt());
                user = new User(name, desc, i, rand.nextBoolean());
                dbHandler.addUser(user);
            }
        }
        RecyclerView rv = findViewById(R.id.rv);
        ProfileAdaptor pa = new ProfileAdaptor(dbHandler.getUsers(), this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(pa);
    }
}
