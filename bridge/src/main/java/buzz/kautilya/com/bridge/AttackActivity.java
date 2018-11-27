package buzz.kautilya.com.bridge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AttackActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);
        textView = findViewById(R.id.joke);
        textView.setText(((String) getIntent().getExtras().get("joke")));

    }
}
