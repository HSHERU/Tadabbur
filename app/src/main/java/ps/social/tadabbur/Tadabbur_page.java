package ps.social.tadabbur;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Tadabbur_page extends AppCompatActivity implements View.OnClickListener {


    Button Login_button;
    Button add_comment_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tadabbur_page);

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Comments");
        query.whereEqualTo("status", 1);
        query.include("UserID");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    List<comment_object> comments_list = new ArrayList<comment_object>();
                    for (int i = 0; i < list.size(); i++) {

                        comments_list.add(new comment_object(
                                list.get(i).get("Comment").toString(),
                                list.get(i).getObjectId(),
                                list.get(i).getCreatedAt().toString(),
                                list.get(i).getParseObject("UserID").get("username").toString(),
                                list.get(i).get("Ayah_number").toString()));
                    }
                    ListView lv = (ListView) findViewById(R.id.comment_listView);
                    comment_array_adapter array_adapter = new comment_array_adapter(Tadabbur_page.this,comments_list);
                    lv.setAdapter(array_adapter);



                } else {
                    Toast.makeText(Tadabbur_page.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        Login_button = (Button) findViewById(R.id.login_tadabbur_page);

        Login_button.setOnClickListener(this);
        add_comment_button = (Button) findViewById(R.id.add_comment);

        add_comment_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.login_tadabbur_page:
                Intent intent = new Intent(Tadabbur_page.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.add_comment:
                Intent intent1 = new Intent(Tadabbur_page.this, AddComment.class);
                startActivity(intent1);
                break;

            case R.id.comment_list_textView:
                Intent intent2 = new Intent(Tadabbur_page.this, TadabburDetail.class);
                startActivity(intent2);
                break;
        }
    }



}