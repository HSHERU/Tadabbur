package ps.social.tadabbur;

/**
 * Created by Kanaan on 12/6/2015.
 */
public class Tadabbur {
    public int id;
    public int page_no;
    public String comment;
    public String user_name;
    public int likes_no;
    public String time_date;

    public String getTime_date() {
        return time_date;
    }

    public void setTime_date(String time_date) {
        this.time_date = time_date;
    }

    public int getLikes_no() {
        return likes_no;
    }

    public void setLikes_no(int likes_no) {
        this.likes_no = likes_no;
    }

    public Tadabbur() {
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPage_no() {

        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

