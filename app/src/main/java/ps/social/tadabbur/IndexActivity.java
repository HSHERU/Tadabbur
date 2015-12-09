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

import java.util.List;

public class IndexActivity extends AppCompatActivity {
    ListView ilist;
    public static int privilege=0;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Bundle extras = getIntent().getExtras();
        privilege=extras.getInt("privilege");
        username=extras.getString("username");
        ilist=(ListView)findViewById(R.id.Indexlist);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getSoursIndex();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        ilist.setAdapter(adapter);
        ilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent=new Intent(IndexActivity.this,ReadActivity2.class);
                int  safha= databaseAccess.getSouraFirstPage(position+1);
                databaseAccess.close();
                intent.putExtra("username",username);
                intent.putExtra("privilege",privilege);
                intent.putExtra("requestCode", 2);
                intent.putExtra("index",safha);
                startActivity(intent);// Activity is started with requestCode 2


            }
        });

    }

}
