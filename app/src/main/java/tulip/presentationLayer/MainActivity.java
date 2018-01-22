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

public class MainActivity extends Activity {

    /** Attributes **/
    Button button1,button2,button3,button4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from activity_main.xml **/
        setContentView(R.layout.activity_main);

        /** Locate the button in activity_main.xml **/
        button1 = (Button) findViewById(R.id.scanButton);
        button2 = (Button) findViewById(R.id.searchButton);
        button3 = (Button) findViewById(R.id.openMyListButton);


        /** Capture button1 clicks **/
        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Start NewActivity.class **/
                Intent myIntent = new Intent(MainActivity.this,
                        NewActivity.class);
                startActivity(myIntent);
            }
        });

        /** Capture button2 clicks **/
        button2.setOnClickListener(new OnClickListener() {
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

        /** Capture button3 clicks **/
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /** Start NewActivity.class **/
                Intent myIntent = new Intent(MainActivity.this,
                        ListActivity.class);
                startActivity(myIntent);
            }
        });
    }

}

