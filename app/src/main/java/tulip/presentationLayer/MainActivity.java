package tulip.presentationLayer;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.zxing.Result;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View.OnClickListener;

import DataLayer.DataAccessLayer.TableList;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class MainActivity extends Activity {

    /* Attributes */
    Button button1,button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Get the view from activity_main.xml */
        setContentView(R.layout.activity_main);

        /* Locate the button in activity_main.xml */
        button1 = (Button) findViewById(R.id.scanButton);
        button2 = (Button) findViewById(R.id.searchButton);


        /* Capture button1 clicks */
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        NewActivity.class);
                startActivity(myIntent);
            }
        });

        /* Capture button2 clicks */
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
/*
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Please enter the EAN-Code manually.");
                builder.setTitle("Manual Search");


                // Set up the input
                final EditText input = new EditText(MainActivity.this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                builder.setView(input);

                // Add the buttons
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
*/
                TableList table1 = new TableList();
                table1.setTableName("testTable");
                String[] columns = new String[2];
                columns[0] = "att1";
                columns[1] = "att2";
                table1.createTable(getApplicationContext(), columns);
            }
        });
    }

}

