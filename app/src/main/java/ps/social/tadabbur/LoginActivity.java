package ps.social.tadabbur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        usernameEditText = (EditText) findViewById(R.id.username_edit_text_login);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text_login);


        Button sign_up = (Button) findViewById(R.id.editText_action_signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Sign_up.class);
                startActivity(intent);
            }
        });

        // Set up the submit button click handler
        findViewById(R.id.action_button_login).
                setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           // Validate the log in data
                                           boolean validationError = false;
                                           StringBuilder validationErrorMessage = new StringBuilder("getResources().getString(R.string.error_intro)");
                                           if (isEmpty(usernameEditText)) {
                                               validationError = true;
                                               validationErrorMessage.append("error_blank_username");
                                           }
                                           if (isEmpty(passwordEditText)) {
                                               if (validationError) {
                                                   validationErrorMessage.append("error_join");
                                               }
                                               validationError = true;
                                               validationErrorMessage.append("error_blank_password");
                                           }
                                           validationErrorMessage.append("wrong username or password");

                                           // If there is a validation error, display the error
                                           if (validationError) {
                                               Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                                               return;
                                           }

                                           // Set up a progress dialog
                                           final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                                           dlg.setTitle("Please wait.");
                                           dlg.setMessage("Logging in.  Please wait.");
                                           dlg.show();
                                           // Call the Parse login method
                                           ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {

                                               @Override
                                               public void done(ParseUser user, ParseException e) {
                                                   dlg.dismiss();
                                                   if (e != null) {
                                                       // Show the error message
                                                       Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                                   } else {
                                                       // Start an intent for the dispatch activity
                                                       Intent intent = new Intent(LoginActivity.this, ReadMainActivity.class);
                                                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                       startActivity(intent);
                                                   }
                                               }
                                           });

                                       }
                                   }

                );
    }



    private boolean isEmpty(EditText etText) {
        //returns true if it's empty otherwise it will return false
        return etText.getText().toString().trim().length() <= 0;
    }
}
