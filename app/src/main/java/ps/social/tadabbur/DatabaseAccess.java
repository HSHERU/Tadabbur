package ps.social.tadabbur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kanaan on 11/22/2015.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static  synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public List<String> searchAya(String letters ) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT text , aya ,sura FROM ayat WHERE nass_safy like ?", new String[]{"%" + letters + "%"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int sora_no=cursor.getInt(2);
            Cursor cursor1=database.rawQuery("SELECT soura_name FROM soura WHERE soura_no = "+sora_no+"",null);
            cursor1.moveToLast();
            String soura=cursor1.getString(0);
            list.add(cursor.getString(0)+"     :   الآية"+cursor.getString(1)+"     : السورة"+soura);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public int login(String user,String pass){
        Cursor mCursor = database.rawQuery("SELECT privilege FROM tusers WHERE username=? AND password=?", new String[]{user,pass});

        if (mCursor != null)
        {
            mCursor.moveToFirst();
            int i=mCursor.getInt(0);
            mCursor.close();
            return i;
/* record exist */
        }
        else
        {
            mCursor.close();
            return 0;
/* record not exist */
        }
    }
        public int getPrivilege(String user){

        Cursor cursor=database.rawQuery("SELECT privilege FROM tusers WHERE username= ' "+user+" ' ",null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public String addSuraName(int surano){
        Cursor cursor1=database.rawQuery("SELECT soura_name FROM soura WHERE soura_no = " + surano + "", null);
        cursor1.moveToFirst();
        return cursor1.getString(0);
    }
    public String toArabic(String str){
        char[] arabicChars = {'٠','١','٢','٣','٤','٥','٦','٧','٨','٩'};
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                builder.append(arabicChars[(int)(str.charAt(i))-48]);
            }
            else
            {
                builder.append(str.charAt(i));
            }
        }
        String text=builder.toString();
        return text;
    }
    public String getpage(int i){
        StringBuilder sb=new StringBuilder();
        int pageno=i;
        Cursor cursor=database.rawQuery("SELECT text , aya ,sura FROM ayat WHERE safha = "+pageno+ "", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ayano=cursor.getInt(1);
            int surano=cursor.getInt(2);
            if (ayano==1){
                sb.append("\n");
                sb.append("سورة ");
                sb.append(addSuraName(surano));
                sb.append("\n");
            }
            String aya=cursor.getString(0);
            sb.append(aya);
            sb.append("{");
            sb.append(ayano);
            sb.append("}");
            cursor.moveToNext();
        }
        cursor.close();
        String page=sb.toString();
        String text=toArabic(page);
        return text;


    }
    public void addTadabbur(String tadbr,String user,int page,String dt){
       database.execSQL("INSERT INTO tadabbur(page_no, comment, username,time_date) " + "VALUES ('" + page + "', '" + tadbr + "','" + user + "','" + dt + "')");

    }
    /*public List<String> getTadabbur(int page){
        List<String> list = new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT comment , username FROM tadabbur WHERE page_no = "+page+ "", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0)+" From : "+cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }*/
    public List<Tadabbur> getTadabbur() {
        List<Tadabbur> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tadabbur ORDER by likes_no DESC ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tadabbur td=new Tadabbur();
            td.setId(cursor.getInt(0));
            td.setPage_no(cursor.getInt(1));
            td.setComment(cursor.getString(2));
            td.setUser_name(cursor.getString(3));
            td.setTime_date(cursor.getString(4));
            td.setLikes_no(cursor.getInt(5));
            list.add(td);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public void addLike(int flike,int Id){
        ContentValues data=new ContentValues();
        data.put("likes_no", flike);
        database.update("tadabbur", data, "id=" + Id, null);
    }
    public boolean deleteTadbr(int Id){
        return database.delete("tadabbur", "id" + "=" + Id, null) > 0;
    }

    public List<String> getSoursIndex(){
        List<String> list = new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM soura",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            StringBuilder sb=new StringBuilder();
            sb.append("[");
            sb.append(cursor.getShort(0));
            sb.append("]");
            sb.append("  سورة : ");
            sb.append(cursor.getString(1));
            sb.append("  وعدد آياتها: ");
            sb.append(cursor.getString(3));
            sb.append(" آية.");
            list.add(sb.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public int getSouraFirstPage(int soura_no){
        Cursor mCursor = database.rawQuery("SELECT safha FROM ayat WHERE sura="+soura_no+" AND aya='1'",null);
        mCursor.moveToFirst();
        return mCursor.getInt(0);

    }
    /*public List<String> getBookmarks(String user_name){
        List<String> list = new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT time_date, safha FROM bookmarks WHERE user=?",new String [] {user_name});
        // WHERE user=' "+user_name+" '
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            StringBuilder sb=new StringBuilder();
            sb.append("Bookmark of page number:  ");
            sb.append(cursor.getInt(1));
            sb.append("  at: ");
            sb.append(cursor.getString(0));
            list.add(sb.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }*/
    public List<Bookmarks> getBookmarks(String user_name){
        List<Bookmarks> list = new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM bookmarks WHERE user=?",new String [] {user_name});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Bookmarks bk=new Bookmarks();
            bk.setID(cursor.getInt(0));
            bk.setUser(cursor.getString(1));
            bk.setTime_date(cursor.getString(2));
            bk.setSafha(cursor.getInt(3));
            cursor.moveToNext();
            list.add(bk);
        }
        cursor.close();
        return list;
    }
    public void setBookMark(String user_name,String time_date,int page_no){
        if(database == null || !database.isOpen() || database.isReadOnly()) {
            this.open();
        }
        database.execSQL("INSERT INTO bookmarks(user, time_date, safha) " +   "VALUES ('" + user_name + "', '" + time_date + "','" + page_no + "')");

    }

}
