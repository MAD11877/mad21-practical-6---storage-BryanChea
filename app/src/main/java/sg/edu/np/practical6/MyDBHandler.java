package sg.edu.np.practical6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "userDB.db";
    public static String USERS = "Users";
    public static String COLUMN_NAME = "Username";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "ID" ;
    public static String COLUMN_FOLLOWED = "Followed";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "  + USERS + "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " TEXT," + COLUMN_FOLLOWED + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        User u;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS, null);
        while (cursor.moveToNext()) {
            u = new User(cursor.getString(0), cursor.getString(1),
                    Integer.valueOf(cursor.getString(2)), Boolean.valueOf(cursor.getString(3)));
            userList.add(u);
        }
        return userList;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDesc());
        values.put(COLUMN_ID, user.getID());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public void updateUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, String.valueOf(u.isFollowed()));
        db.update(USERS, values, COLUMN_NAME + " = ?", new String[]{u.getName()});
        /* Troubleshoot
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_FOLLOWED + " FROM " + USERS + " WHERE " + COLUMN_NAME + " = ?", new String[]{u.getName()});
        if (cursor.moveToFirst()) {
            System.out.println(cursor.getString(0));
        }
        */
        db.close();
    }
}

