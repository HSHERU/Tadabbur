package ps.social.tadabbur;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BookMarkActivity extends AppCompatActivity {
    ListView ilist;
    public static int privilege=0;
    public static String username;
    List<Bookmarks> bk= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);
        Bundle extras = getIntent().getExtras();
        privilege=extras.getInt("privilege");
        username=extras.getString("username");
        ilist=(ListView)findViewById(R.id.Indexlist);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        bk = databaseAccess.getBookmarks(username);
        List<String> quotes=getListString(bk);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        ilist.setAdapter(adapter);
        databaseAccess.close();

        ilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(BookMarkActivity.this, ReadActivity2.class);
                int safha = bk.get(position).getSafha();
                intent.putExtra("username", username);
                intent.putExtra("privilege", privilege);
                intent.putExtra("requestCode", 3);
                intent.putExtra("index", safha);
                startActivity(intent);// Activity is started with requestCode 2


            }
        });
    }
    public List<String> getListString(List<Bookmarks> bb){
        int s=bb.size();
        List<String> list = new ArrayList<>();
        for(int i=0;i<s;i++){
            StringBuilder sb=new StringBuilder();
            Bookmarks bobj=bb.get(i);
            sb.append("Bookmark of page number:  ");
            sb.append(bobj.getSafha());
            sb.append("  at: ");
            sb.append(bobj.getTime_date());
            list.add(sb.toString());

        }
        return list;
    }

}
