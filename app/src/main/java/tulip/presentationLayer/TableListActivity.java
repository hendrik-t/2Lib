package tulip.presentationLayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import DataLayer.DataAccessLayer.TableList;
import DataLayer.Item;

/**
 * created by nilskjellbeck on 22.01.18
 * changed by Kevin Struckmeyer on 23.01.18
 */

public class TableListActivity extends Activity {

    /** Attributes **/

    ListView simpleList;
    ArrayAdapter<String> arrayAdapter;
    Button floatButton;
    String tableNames[];
    CharSequence[] template = {"Books", "Games", "Movies", "Music", "Custom"};
    int saveInput=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Init **/
        tableNames = new TableList(getApplicationContext()).getTableNames();

        /** Get the view from list_activity.xml **/
        setContentView(R.layout.list_activity);

        floatButton = (Button) findViewById(R.id.createListButton);
        simpleList = (ListView)findViewById(R.id.listView);
        registerForContextMenu(simpleList);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
        simpleList.setAdapter(arrayAdapter);

        simpleList.setClickable(true);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /** Start ItemListActivity.class **/
                Intent myIntent = new Intent(TableListActivity.this,
                        ItemListActivity.class);
                myIntent.putExtra("tableName", tableNames[position]);
                startActivity(myIntent);
            }
        });


        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builderSingleChoices = new AlertDialog.Builder(TableListActivity.this);

                /** Sets up some characteristics of the dialog **/
                builderSingleChoices.setTitle("Pick a List-Template");
                // DONT TRY ADDING A MESSAGE, DOENST WORK!!!!

                builderSingleChoices.setSingleChoiceItems(template, -1, new DialogInterface.OnClickListener() {
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
                builderSingleChoices.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /* Books */
                        if (saveInput == 1) {

                        }
                        /* Games */
                        else if (saveInput == 2) {

                        }
                        /* Movies */
                        else if (saveInput == 3) {

                        }
                        /* Music */
                        else if (saveInput == 4) {

                        }
                        /* Custom */
                        else if (saveInput == 5) {
                            /** Instantiate an AlertDialog.Builder with its constructor **/
                            final AlertDialog.Builder builder = new AlertDialog.Builder(TableListActivity.this);
                            final LinearLayout layout = new LinearLayout(getApplicationContext());
                            layout.setOrientation(LinearLayout.VERTICAL);

                            /** Sets up some characteristics of the dialog **/
                            builder.setTitle("Create new List");
                            builder.setMessage("Please enter the specifications for your new list.");

                            /** Set up the input **/
                            final EditText inputTitle = new EditText(getApplicationContext());
                            inputTitle.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                            inputTitle.setHint("List title");

                            final EditText inputFirstColumn = new EditText(getApplicationContext());
                            inputFirstColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                            inputFirstColumn.setHint("Property name");

                            layout.addView(inputTitle);
                            layout.addView(inputFirstColumn);

                            final ArrayList<EditText> columnInputs = new ArrayList<EditText>();
                            columnInputs.add(inputFirstColumn);

                            builder.setView(layout);

                            /** Add the buttons and their functionalities **/
                            builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Button Handler a bit further down
                                }
                            });
                            builder.setNeutralButton("Add Property", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Button Handler a bit further down
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });

                            /** Get the AlertDialog from create() **/
                            final AlertDialog customListCreationDialog = builder.create();

                            /** Shows dialog **/
                            customListCreationDialog.show();

                            customListCreationDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v) {
                                    if(!inputTitle.getText().toString().trim().isEmpty()) {
                                        String regEx = "[a-zA-Z0-9]*";
                                        if (inputTitle.getText().toString().matches(regEx)) {
                                            ArrayList<String> columns = new ArrayList<String>();
                                            for (int i = 0; i < columnInputs.size(); i++) {
                                                if (!columnInputs.get(i).getText().toString().replace(" ", "").isEmpty()) {
                                                    columns.add(columnInputs.get(i).getText().toString());
                                                }
                                            }

                                            if (!columns.isEmpty()) {
                                                /* Create the table */
                                                new TableList(getApplicationContext()).createTable(inputTitle.getText().toString(), columns);

                                               /* Update List View */
                                                tableNames = new TableList(getApplicationContext()).getTableNames();
                                                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
                                                simpleList.setAdapter(arrayAdapter);

                                                /* Close Alert Dialog */
                                                customListCreationDialog.dismiss();
                                            } else {
                                                // display error
                                                Toast.makeText(getApplicationContext(), "At least one property required.", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            // display error
                                            Toast.makeText(getApplicationContext(), "No spaces or symbols in the title allowed.", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        // display error
                                        Toast.makeText(getApplicationContext(), "Title is empty.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            customListCreationDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v) {
                                    final EditText inputColumn = new EditText(TableListActivity.this);
                                    inputColumn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                                    inputColumn.setHint("Property name");
                                    layout.addView(inputColumn);
                                    builder.setView(layout);

                                    columnInputs.add(inputColumn);

                                    if (columnInputs.size() == 9) {
                                        Toast.makeText(getApplicationContext(), "Maximum amount reached", Toast.LENGTH_LONG).show();
                                        customListCreationDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setClickable(false);
                                    }
                                }
                            });
                        }

                    }
                });
                builderSingleChoices.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                /** Get the AlertDialog from create() **/
                AlertDialog dialog = builderSingleChoices.create();

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
        menu.add("Share List");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        /* Context Menu Click Event Handler */
        if(item.getTitle().equals("Add Item")) {
            /** Instantiate an AlertDialog.Builder with its constructor **/
            AlertDialog.Builder builder = new AlertDialog.Builder(TableListActivity.this);
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            /** Sets up some characteristics of the dialog **/
            builder.setTitle("Add Item");
            builder.setMessage("Type in the values of the item you want to add.");

            final ArrayList<EditText> inputs = new ArrayList<EditText>();
            final ArrayList<String> columnNames = new TableList(getApplicationContext()).getColumnNames(tableNames[info.position]);
            for (int i = 0; i < columnNames.size(); i++) {
                /** Set up the inputs **/
                inputs.add(new EditText(getApplicationContext()));
                inputs.get(i).setHint(columnNames.get(i));

                /** Specify the type of input expected; this sets the input as a number, and will not mask the text **/
                inputs.get(i).setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);

                layout.addView(inputs.get(i));
            }
            builder.setView(layout);

            /** Add the buttons and their functionalities **/
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
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
                    boolean inputsValid = true;
                    for (int i = 0; i < columnNames.size(); i++) { if (inputs.get(i).getText().toString().replace(" ", "").isEmpty()) inputsValid = false; }
                    if (inputsValid) {
                        Item newItem = new Item();
                        for (int i = 0; i < columnNames.size(); i++) {
                            newItem.addEntryToHashMap(columnNames.get(i), inputs.get(i).getText().toString());
                        }
                        new TableList(getApplicationContext()).addItem(tableNames[info.position], newItem);

                        /* Close Alert Dialog */
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "All fields need to be filled!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return true;
        }
        else if(item.getTitle().equals("Rename")) {
            /** Instantiate an AlertDialog.Builder with its constructor **/
            AlertDialog.Builder builder = new AlertDialog.Builder(TableListActivity.this);

            /** Sets up some characteristics of the dialog **/
            builder.setTitle("Rename");
            builder.setMessage("No spaces or names that are already used.");

            /** Set up the input **/
            final EditText input = new EditText(TableListActivity.this);

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
                    }
                }
            });

            return true;
        }

        // ********************************************************************+
        else if(item.getTitle().equals("Share List")) {
            /** Instantiate an AlertDialog.Builder with its constructor **/
            AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

            /** Sets up some characteristics of the dialog **/
            builder.setTitle("Share List");
            builder.setMessage("Press send to share the list.");


            /** Add the buttons and their functionalities **/
            builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {

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
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + "recipient@example.com"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my Shared-List, please BUY!!!");

                    TableList tab = new TableList(getApplicationContext());
                    tab.open(tableNames[info.position]);
                    String result = "";
                    result = tab.toString(tableNames[info.position], tab);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, result);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
                    }

                }

            });

            return true;
        }

        // ********************************************************************+
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

