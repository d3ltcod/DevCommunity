package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button anonBtn = findViewById(R.id.anonymous_signIn_btn);
        anonBtn.setOnClickListener(anonUserBtnListener);

        Button signInBtn = findViewById(R.id.signIn_btn);
        signInBtn.setOnClickListener(signInBtnListener);
    }

    // Create an anonymous implementation of OnClickListener for skip button
    private View.OnClickListener anonUserBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            writeInSharedPreferencesKindUser(false);
            changeActivity(MainActivity.class, true);
        }
    };

    // Create an anonymous implementation of OnClickListener for skip button
    private View.OnClickListener signInBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText emailEditText = findViewById(R.id.email_editText);
            EditText passwordEditText = findViewById(R.id.password_editText);
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if(!email.trim().matches(emailPattern)) {
                Toast.makeText(getApplicationContext(),R.string.signIn_error_email_pattern,Toast.LENGTH_SHORT).show();
                return ;
            }

            if(checkCredentials(email, password)) {
                writeInSharedPreferencesKindUser(true);
                changeActivity(MainActivity.class, true);
            } else {
                changeActivity(LoginErrorActivity.class, false);
            }
        }
    };

    private void changeActivity(Class activity, boolean end) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if(end) finish();
    }

    private void writeInSharedPreferencesKindUser(boolean type) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.preference_first_time), type);
        editor.apply();
    }

    private boolean checkCredentials(String email, String password) {
        //TODO - IMPLEMENT REAL LOGIN, THIS IS ONLY FOR SIMULATE SITUATION
        return (email.equals(getString(R.string.email_example)) && password.equals(getString(R.string.password_example)));
    }
}
