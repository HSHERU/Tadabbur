package ps.social.tadabbur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReadMainActivity extends AppCompatActivity {
    public static int privilege=2;
    public static String username;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_main);


        Button logg=(Button)findViewById(R.id.loginbtn);
        if (privilege!=0){
            logg.setText("log out"+username);
        }
        login=(Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (privilege==0){
                    Intent intent = new Intent(ReadMainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 2);// Activity is started with requestCode 2
                }
                else{
                    privilege=0;
                    username="";
                    login.setText("login");
                }

            }
        });

        Button search=(Button)findViewById(R.id.searchbtn);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent=new Intent(ReadMainActivity.this,SearchActivity.class);
                startActivity(intent);// Activity is started with requestCode 2
            }
        });
        Button tadbr=(Button)findViewById(R.id.tadbrbtn);
        tadbr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent=new Intent(ReadMainActivity.this,Tadabbur_page.class);
                startActivity(intent);// Activity is started with requestCode 2
            }
        });
        Button read=(Button)findViewById(R.id.readbtn);

        read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent=new Intent(ReadMainActivity.this,ReadActivity2.class);
                intent.putExtra("username",username);
                intent.putExtra("privilege",privilege);
                intent.putExtra("requestCode",1);
                startActivity(intent);// Activity is started with requestCode 1
            }
        });
        Button indx=(Button)findViewById(R.id.Indbtn);
        indx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent=new Intent(ReadMainActivity.this,IndexActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("privilege",privilege);
                intent.putExtra("requestCode",1);
                startActivity(intent);
            }
        });
        Button bmarks=(Button)findViewById(R.id.markbtn);
        bmarks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                    Intent intent = new Intent(ReadMainActivity.this, BookMarkActivity.class);
                Bundle extras = getIntent().getExtras();
                privilege=extras.getInt("privilege");
                username=extras.getString("username");
                    intent.putExtra("username",username);
                    intent.putExtra("privilege",privilege);
                    intent.putExtra("requestCode",1);
                    startActivity(intent);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            privilege=message.charAt(0) - '0';
            username=message.substring(1);
            login.setText("logout:  "+username);

        }
    }

}
