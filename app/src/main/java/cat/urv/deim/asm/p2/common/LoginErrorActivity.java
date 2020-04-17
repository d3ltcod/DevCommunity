package cat.urv.deim.asm.p2.common;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginErrorActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        Button tryAgainBtn = findViewById(R.id.try_again_button);
        tryAgainBtn.setOnClickListener(tryAgainBtnListener);
    }

    private View.OnClickListener tryAgainBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
