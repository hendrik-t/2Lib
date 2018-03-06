package tulip.presentationLayer;

import android.app.*;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View.OnClickListener;

/**
 * created by nilskjellbeck on 12.12.17
 * changed by Kevin Struckmeyer on 13.12.17
 * changed by Kevin Struckmeyer on 22.01.18
 * changed by nilskjellbeck on 23.01.18
 */

public class MainActivity extends Activity {

    /** Attributes **/
    Button scanButton, searchButton, openMyListButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from activity_main.xml **/
        setContentView(R.layout.activity_main);

        /** Locate the button in activity_main.xml **/
        scanButton = (Button) findViewById(R.id.scanButton);
        searchButton = (Button) findViewById(R.id.searchButton);
        openMyListButton = (Button) findViewById(R.id.openMyListButton);


        /** Capture scanButton clicks **/
        scanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Start NewActivity.class **/
                Intent myIntent = new Intent(MainActivity.this,
                        ScanActivity.class);
                startActivity(myIntent);
            }
        });

        /** Capture searchButton clicks **/
        searchButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                /** Sets up some characteristics of the dialog **/
                builder.setMessage("Please enter the EAN-Code manually.");
                builder.setTitle("Manual Search");

                /** Set up the input **/
                final EditText input = new EditText(MainActivity.this);

                /** Specify the type of input expected; this sets the input as a number, and will not mask the text **/
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                builder.setView(input);

                /** Sets the cursors into the input field **/
                input.requestFocus();


                /** Add the buttons and their functionalities **/
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

                /** Get the AlertDialog from create() **/
                AlertDialog dialog = builder.create();

                /** Open the Soft Keyboard for user and shows dialog **/
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.show();
            }
        });

        /** Capture openMyListButton clicks **/
        openMyListButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Start NewActivity.class **/
                Intent myIntent = new Intent(MainActivity.this,
                        TableListActivity.class);
                startActivity(myIntent);
            }
        });
    }

}

