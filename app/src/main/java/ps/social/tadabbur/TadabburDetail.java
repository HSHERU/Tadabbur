package ps.social.tadabbur;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TadabburDetail extends AppCompatActivity {
    int privilege=0;
    String username="";
    TextView ctv;
    TextView ltv;
    TextView posttv;
    TextView atime;
    TextView pagetv;
    int likeno=0;
    int TID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tadabbur_detail);
        Bundle extras = getIntent().getExtras();
        privilege=extras.getInt("privilege");
        username=extras.getString("username");
        String content=extras.getString("content");
        likeno=extras.getInt("likes");
        String ussr=extras.getString("post by");
        String dtime=extras.getString("time");
        int pno=extras.getInt("pageno");
        TID=extras.getInt("ID");
        ctv=(TextView)findViewById(R.id.tdcontent);
        ltv=(TextView)findViewById(R.id.likesno);
        posttv=(TextView)findViewById(R.id.post);
        atime=(TextView)findViewById(R.id.tdbrat);
        pagetv=(TextView)findViewById(R.id.pagetdbr);
        ctv.setText(content);
        ltv.setText("there are "+Integer.toString(likeno)+" people like it");
        posttv.setText("Was posted by : "+ussr);
        atime.setText("Created at: "+dtime);
        pagetv.setText("This post about page number :  "+Integer.toString(pno));
    }
    public void LikeOnClick(View v) {
        if(privilege==0){
            Context context = getApplicationContext();
            CharSequence text = "You need to login first ";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            likeno++;
            databaseAccess.addLike(likeno,TID);
            ltv.setText("there are "+Integer.toString(likeno)+" people like it");
            databaseAccess.close();
        }



    }

    public void DeleteOnClick(View v) {
        if(privilege!=2){
            Context context = getApplicationContext();
            CharSequence text = "You need an admin permission";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else{
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        boolean suc=databaseAccess.deleteTadbr(TID);
        databaseAccess.close();
            if(suc){
                finish();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Error on delete tadabbur";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }


    }
}
