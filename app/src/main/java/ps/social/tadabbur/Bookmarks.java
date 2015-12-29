package ps.social.tadabbur;

/**
 * Created by abdulrahman on 12/5/2015.
 */
public class Bookmarks {
    public int ID;
    public String user;
    public String time_date;
    public int safha;
    public Bookmarks() {
    }

    public int getSafha() {
        return safha;
    }

    public void setSafha(int safha) {
        this.safha = safha;
    }

    public String getTime_date() {

        return time_date;
    }

    public void setTime_date(String time_date) {
        this.time_date = time_date;
    }

    //public String getUser() { return user;    }

    //public void setUser(String user) {this.user = user;    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
