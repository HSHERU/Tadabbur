package ps.social.tadabbur;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addTadbrActivity extends AppCompatActivity {
    public String username="";
    int pageno=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tadbr);
        Bundle extras = getIntent().getExtras();
        username=extras.getString("username");
        pageno=extras.getInt("page");


    }
    public void submitOnClick(View v) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        EditText ttx=(EditText)findViewById(R.id.tadbrText);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
        String strDate = sdf.format(c.getTime());
        databaseAccess.addTadabbur(ttx.getText().toString(), username, pageno,strDate);
        databaseAccess.close();

        finish();
    }

}
