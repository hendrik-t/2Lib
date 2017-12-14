package tulip.presentationLayer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import DataLayer.DataAccessLayer.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
