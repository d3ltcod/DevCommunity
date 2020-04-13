package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            startMainActivity();
        }
    };

    // Create an anonymous implementation of OnClickListener for skip button
    private View.OnClickListener signInBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            // TODO - Call methods to get input data and check email and password
            startMainActivity();
        }
    };

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
