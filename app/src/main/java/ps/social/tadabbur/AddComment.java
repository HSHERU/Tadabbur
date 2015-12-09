package ps.social.tadabbur;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class AddComment extends AppCompatActivity {

    private EditText surah_name_editText;
    private EditText ayah_number_editText;
    private EditText write_comment_editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        findViewById(R.id.add_comment).setOnClickListener(mButton1_OnClickListener);

        surah_name_editText = (EditText) findViewById(R.id.Surah_name_editText);
        ayah_number_editText = (EditText) findViewById(R.id.Ayah_number_editText);
        write_comment_editText = (EditText) findViewById(R.id.Write_comment_editText);

    }

    //Global On click listener for all views
    final View.OnClickListener mButton1_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            final int Ayah_number;
            final int Sourah_ID;
            try
            {
                Ayah_number = Integer.parseInt(ayah_number_editText.getText().toString());
                Sourah_ID = Integer.parseInt(surah_name_editText.getText().toString());
            }
            catch (NumberFormatException e)
            {
                Toast.makeText(AddComment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            final String write_comment = write_comment_editText.getText().toString().trim();

            final ParseObject Comments = new ParseObject("Comments");
            final ParseObject Sourah;
            final ParseQuery<ParseObject> query = ParseQuery.getQuery("Sourah");
            query.getInBackground("OuHQFa1QuK", new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        submit_comment(Comments,Ayah_number,write_comment,Sourah_ID,object);
                    } else {
                        // something went wrong

                    }
                }
            });


        }
    };


    private void submit_comment(ParseObject Comments, int ayah_number,String write_comment,int sourah_ID,ParseObject sourah)
    {
        Comments.put("Comment", write_comment);
        Comments.put("Ayah_number", ayah_number);
        Comments.put("Sourah_ID", sourah_ID);
        Comments.put("status", 0);
        //Comments.put("Surah_ID", ParseObject.createWithoutData("Sourah", sourah.getObjectId()).toString());

        try {
            Comments.put("UserID", ParseUser.getCurrentUser());
        } catch (Exception e){
            Toast.makeText(AddComment.this, e.getMessage() , Toast.LENGTH_LONG).show();
        }

        // Set up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(AddComment.this);
        dlg.setTitle("Please wait.");
        dlg.setMessage("Sending your comment to the system.  Please wait.");
        dlg.show();
        Comments.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                dlg.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(AddComment.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    //Inform the user the button has been clicked
                    Toast.makeText(AddComment.this, "Your comment has been added to the system", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
