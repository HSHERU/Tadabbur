package ps.social.tadabbur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReadActivity2 extends AppCompatActivity {
    public int pageno=1;
    ViewPager myVP;

    private PagerAdapter mPagerAdapter;
    public String username;
    public int privilege=2;
    public int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        privilege=extras.getInt("privilege");
        username=extras.getString("username");
        requestCode=extras.getInt("requestCode");
        myVP=new ViewPager(this);
        myVP.setId(View.generateViewId());
        // mPager = (ViewPager) findViewById(R.id.vpPager);
        mPagerAdapter = new myFragAdapter(getSupportFragmentManager());
        myVP.setAdapter(mPagerAdapter);
        LinearLayout linL = (LinearLayout) findViewById(R.id.container);

        linL.addView(myVP);
        if (requestCode == 2) {
            int indx=extras.getInt("index");

            myVP.setCurrentItem(indx-1);
        }
        if (requestCode == 3) {
            int indx=extras.getInt("index");

            myVP.setCurrentItem(indx-1);
        }

        if (savedInstanceState != null) {
            myVP.setCurrentItem(savedInstanceState.getInt("pageItem", 0));
        }
        //myVP.setAdapter(new myFragAdapter(getSupportFragmentManager()));


    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pageItem", myVP.getCurrentItem());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.read_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tdbr:
                if(privilege ==0){
                    Context context = getApplicationContext();
                    CharSequence text = "Log in first!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    pageno = myVP.getCurrentItem() + 1;
                    Intent intent = new Intent(ReadActivity2.this, addTadbrActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("page", pageno);
                    startActivity(intent);// Activity is started with requestCode 2
                }
                // Red item was selected
                return true;
            case R.id.menu_mark:
                if(privilege ==0){
                    Context context = getApplicationContext();
                    CharSequence text = "Log in first!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
                    pageno = myVP.getCurrentItem() + 1;
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                    String strDate = sdf.format(c.getTime());
                    databaseAccess.setBookMark(username,strDate,pageno);
                    databaseAccess.close();
                }
                // Green item was selected
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

