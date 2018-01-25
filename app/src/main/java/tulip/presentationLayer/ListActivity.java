package tulip.presentationLayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import DataLayer.DataAccessLayer.TableList;

/**
 * created by nilskjellbeck on 22.01.18
 * changed by Kevin Struckmeyer on 23.01.18
 */

public class ListActivity extends Activity {

    /** Attributes **/

    ListView simpleList;
    ArrayAdapter<String> arrayAdapter;
    Button floatButton;
    String tableNames[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Init **/
        tableNames = new TableList(getApplicationContext()).getTableNames();

        /** Get the view from list_activity.xml **/
        setContentView(R.layout.list_activity);

        floatButton = (Button) findViewById(R.id.newListButton);
        simpleList = (ListView)findViewById(R.id.listView);
        registerForContextMenu(simpleList);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
        simpleList.setAdapter(arrayAdapter);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);



                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_add_table, null);

                /** Sets up some characteristics of the dialog **/
                builder.setTitle("Create new List");
                builder.setMessage("Please enter the specifications for your new list.");

                /** Set up the input **/
                final EditText inputTitle = (EditText) dialogView.findViewById(R.id.listname);
                final EditText inputColumn = (EditText) dialogView.findViewById(R.id.columnname0);

                /** Specify the type of input expected; this sets the input as a number, and will not mask the text **/
                inputTitle.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                inputColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(dialogView);

                /** Add the buttons and their functionalities **/
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /** STILL NEED EXCEPTION HANDLING */

                        ArrayList<String> columns = new ArrayList<>();
                        columns.add(inputColumn.getText().toString());
                        new TableList(getApplicationContext()).createTable(inputTitle.getText().toString(), columns);

                        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, new TableList(getApplicationContext()).getTableNames());
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Add Item");
        menu.add("Rename");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        /* Context Menu Click Event Handler */
        if(item.getTitle().equals("Add Item")) {

            return true;
        }
        else if(item.getTitle().equals("Rename")) {

            return true;
        }
        else if(item.getTitle().equals("Delete")) {
            /* Call method to delete table */
            new TableList(getApplicationContext()).deleteTable(tableNames[info.position]);

            /* Update the ListView */
            arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, new TableList(getApplicationContext()).getTableNames());
            simpleList.setAdapter(arrayAdapter);

            return true;
        }
        return true;

    }

}

