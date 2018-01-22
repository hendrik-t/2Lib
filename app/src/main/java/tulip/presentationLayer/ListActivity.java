package tulip.presentationLayer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ListActivity extends Activity {

    /** Attributes **/
    Button scanButton, manualInputButton, openMyListButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from activity_main.xml **/
        setContentView(R.layout.activity_main);

        /** Locate the button in activity_main.xml **/
        scanButton = (Button) findViewById(R.id.scanButton);
        manualInputButton = (Button) findViewById(R.id.searchButton);
        openMyListButton = (Button) findViewById(R.id.openMyListButton);


        /** Capture scanButton clicks **/
        scanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Start ScanActivity.class **/
                Intent myIntent = new Intent(ListActivity.this,
                        ScanActivity.class);
                startActivity(myIntent);
            }
        });

        /** Capture manualInputButton clicks **/
        manualInputButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                /** Sets up some characteristics of the dialog **/
                builder.setMessage("Please enter the EAN-Code manually.");
                builder.setTitle("Manual Search");

                /** Set up the input **/
                final EditText input = new EditText(ListActivity.this);

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
        scanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Start ScanActivity.class **/
                Intent myIntent = new Intent(ListActivity.this,
                        ListActivity.class);
                startActivity(myIntent);
            }
        });
    }

}

