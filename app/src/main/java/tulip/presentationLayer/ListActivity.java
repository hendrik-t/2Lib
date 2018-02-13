package tulip.presentationLayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    final String template[] = {"Books", "Games", "Movies", "Music", "Custom"};
    int saveInput=0;

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

        simpleList.setClickable(true);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object o = simpleList.getItemAtPosition(position);

                /** Start ListElementActivity.class **/
                Intent myIntent = new Intent(ListActivity.this,
                        ListElementActivity.class);
                startActivity(myIntent);
            }
        });


        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ListActivity.this);

                /** Sets up some characteristics of the dialog **/
                builder1.setTitle("Pick a List-Template");
                builder1.setMessage("Select the kind of Template you need.");

                builder1.setSingleChoiceItems(template, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (template[which] == "Books") {
                            saveInput = 1;
                        } else if (template[which] == "Games") {
                            saveInput = 2;
                        } else if (template[which] == "Movies") {
                            saveInput = 3;
                        } else if (template[which] == "Music") {
                            saveInput = 4;
                        } else if (template[which] == "Custom") {
                            saveInput = 5;
                        }
                    }
                });


                /** Add the buttons and their functionalities **/
                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

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
                            builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
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
                        AlertDialog dialog1 = builder.create();

                        /** Shows dialog **/
                        dialog1.show();

                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                /** Get the AlertDialog from create() **/
                AlertDialog dialog = builder1.create();

                /** Shows Dialog **/
                dialog.show();

                }

            });
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
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        /* Context Menu Click Event Handler */
        if(item.getTitle().equals("Add Item")) {

            return true;
        }
        else if(item.getTitle().equals("Rename")) {
            /** Instantiate an AlertDialog.Builder with its constructor **/
            AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

            /** Sets up some characteristics of the dialog **/
            builder.setTitle("Rename");
            builder.setMessage("No spaces or names that are already used.");

            /** Set up the input **/
            final EditText input = new EditText(ListActivity.this);

            /** Specify the type of input expected; this sets the input as a number, and will not mask the text **/
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            /** Sets the cursors into the input field **/
            input.requestFocus();


            /** Add the buttons and their functionalities **/
            builder.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // Event Handler a bit below
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            /** Get the AlertDialog from create() **/
            final
            AlertDialog dialog = builder.create();

            /** Open the Soft Keyboard for user and shows dialog **/
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            dialog.show();

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    /* If the input isnt empty, doesnt contain spaces and no table with that name already exists */
                    if((!input.getText().toString().isEmpty() && !input.getText().toString().contains(" ")) && !Arrays.asList(tableNames).contains(input.getText().toString())) {
                        /* Rename table in DB */
                        new TableList(getApplicationContext()).renameTable(tableNames[info.position], input.getText().toString());

                        /* Update the ListView */
                        tableNames = new TableList(getApplicationContext()).getTableNames();
                        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
                        simpleList.setAdapter(arrayAdapter);

                        /* Close Alert Dialog */
                        dialog.dismiss();
                    } else {

                        
                    }
                }
            });

            return true;
        }
        else if(item.getTitle().equals("Delete")) {
            /* Call method to delete table */
            new TableList(getApplicationContext()).deleteTable(tableNames[info.position]);

            /* Update the ListView */
            tableNames = new TableList(getApplicationContext()).getTableNames();
            arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
            simpleList.setAdapter(arrayAdapter);

            return true;
        }
        return true;

    }

}

