package ps.social.tadabbur;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class TadbrActivity extends AppCompatActivity {
    ListView tdlist;
    List<Tadabbur> td = new ArrayList<>();
    int privilege=0;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tadbr);
        Bundle extras = getIntent().getExtras();
        privilege=extras.getInt("privilege");
        username=extras.getString("username");
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        tdlist = (ListView) findViewById(R.id.tadbrlist);
        td = databaseAccess.getTadabbur();
        List<String> quotes = getListString(td);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        tdlist.setAdapter(adapter);
        databaseAccess.close();
        tdlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(TadbrActivity.this, TadabburDetail.class);
                intent.putExtra("username", username);
                intent.putExtra("privilege", privilege);
                intent.putExtra("content", td.get(position).getComment());
                intent.putExtra("likes", td.get(position).getLikes_no());
                intent.putExtra("post by", td.get(position).getUser_name());
                intent.putExtra("time", td.get(position).getTime_date());
                intent.putExtra("pageno", td.get(position).getPage_no());
                intent.putExtra("ID",td.get(position).getId());
                startActivity(intent);// Activity is started with requestCode 2


            }
        });

    }


    public List<String> getListString(List<Tadabbur> bb) {
        int s = bb.size();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            StringBuilder sb = new StringBuilder();
            Tadabbur tobj = bb.get(i);
            sb.append(tobj.getComment());
            list.add(sb.toString());


        }
        return list;
    }


}
