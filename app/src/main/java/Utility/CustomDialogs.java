package Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.EditText;

import tulip.presentationLayer.MainActivity;

/**
 * Created by nilskjellbeck on 15.12.17.
 */

public class CustomDialogs {


    CustomDialogs(Context context, String title, String message, int inputtype) {
        /** Instantiate an AlertDialog.Builder with its constructor **/
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        /** Sets up some characteristics of the dialog **/
        builder.setMessage(message);
        builder.setTitle(title);

        /** Set up the input **/
        final EditText input = new EditText(context);

        /** Specify the type of input expected; this sets the input as a number, and will not mask the text **/
        switch(inputtype) {
            case 0: input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                break;

            case 1: input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                break;

            case 2: input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                break;

            default: ;
                break;
        }
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
}


