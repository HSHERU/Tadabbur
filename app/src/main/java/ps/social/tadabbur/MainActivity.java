package ps.social.tadabbur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Browse(View view){
        Intent browse = new Intent(this,ReadMainActivity.class);
        startActivity(browse);
    }
    public void Login(View view){
        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);
    }


}
