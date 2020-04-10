package cat.urv.deim.asm.p2.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cat.urv.deim.asm.p2.common.adapters.ViewPageAdapter;

public class TutorialActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int[] layouts = {R.layout.tutorial_screen_1, R.layout.tutorial_screen_2, R.layout.tutorial_screen_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        viewPager = findViewById(R.id.tutorial_viewPager);
        ViewPageAdapter adapter = new ViewPageAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(adapter);

        Button skipBtn = findViewById(R.id.skip_tutorial_btn);
        Button nextBtn = findViewById(R.id.next_tutorial_btn);

        skipBtn.setOnClickListener(skipListener);
        nextBtn.setOnClickListener(nextListener);
    }

    // Create an anonymous implementation of OnClickListener for skip button
    private View.OnClickListener skipListener = new View.OnClickListener() {
        public void onClick(View v) {
            startMainActivity();
        }
    };

    // Create an anonymous implementation of OnClickListener for next button
    private View.OnClickListener nextListener = new View.OnClickListener() {
        public void onClick(View v) {
            int currentPage = viewPager.getCurrentItem()+1;
            if(currentPage < layouts.length) {
                viewPager.setCurrentItem(currentPage);
            } else {
                startMainActivity();
            }
        }
    };

    private void writeEndTutorial() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.preference_first_time), false);
        editor.apply();
    }

    private void startMainActivity() {
        writeEndTutorial();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
