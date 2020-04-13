package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                if(sharedPref.getBoolean(getString(R.string.preference_first_time), true))
                    goToActivity(TutorialActivity.class);
                else
                    goToActivity(LoginActivity.class);
            }
        }, 1000);   //1 seconds
    }

    private void goToActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        finish();
    }
}
