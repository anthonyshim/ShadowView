package imjmo.shadowview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import imjmo.shadowview.ShadowView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShadowView down = (ShadowView) findViewById(R.id.shadowview_down);
        down.setOnClickListener(new ShadowView.OnClickListener() {
            @Override
            public void onClick(ShadowView v) {
                Toast.makeText(MainActivity.this, "Click Down View!!", Toast.LENGTH_SHORT).show();
            }
        });

        ShadowView flat = (ShadowView) findViewById(R.id.shadowview_flat);
        flat.setOnClickListener(new ShadowView.OnClickListener() {
            @Override
            public void onClick(ShadowView v) {
                Toast.makeText(MainActivity.this, "Click Flat View!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
