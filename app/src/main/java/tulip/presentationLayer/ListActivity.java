package tulip.presentationLayer;

import android.app.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.*;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import DataLayer.DataAccessLayer.TableList;

/**
 * created by nilskjellbeck on 22.01.18
 * changed by Kevin Struckmeyer on 23.01.18
 */

public class ListActivity extends Activity {

    /** Attributes **/

    ListView simpleList;
    Button floatButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Init **/
        TableList tableList = new TableList(getApplicationContext());
        String tableNames[] = tableList.getTableNames();

        /** Get the view from list_activity.xml **/
        setContentView(R.layout.list_activity);

        floatButton = (Button) findViewById(R.id.newListButton);
        simpleList = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
        simpleList.setAdapter(arrayAdapter);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);



                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_add_table, null);

                /** Sets up some characteristics of the dialog **/
                builder.setMessage("Please enter the name for your new list.");
                builder.setTitle("Create new List");

                /** Set up the input **/
                final EditText input = (EditText) dialogView.findViewById(R.id.listname);
                final EditText input1 = (EditText) dialogView.findViewById(R.id.columnname0);

                /** Specify the type of input expected; this sets the input as a number, and will not mask the text **/
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                input1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(dialogView);

                /** Add the buttons and their functionalities **/
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ArrayList<String> columns = new ArrayList<>();
                        columns.add(input1.getText().toString());
                        new TableList(getApplicationContext()).createTable(input.getText().toString(), columns);

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, new TableList(getApplicationContext()).getTableNames());
                        simpleList.setAdapter(arrayAdapter);
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
                dialog.show();
            }
        });




        // TableList.getTablenames
    }

}

