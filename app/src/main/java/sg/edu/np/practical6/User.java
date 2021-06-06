package sg.edu.np.practical6;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String desc;
    private int id;
    private boolean followed;

    public User() {}

    public User(String t1, String t2, int id, boolean b) {
        this.name = t1;
        this.desc = t2;
        this.id = id;
        this.followed = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
