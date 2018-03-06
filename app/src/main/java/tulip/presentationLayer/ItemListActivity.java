package tulip.presentationLayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import DataLayer.DataAccessLayer.TableList;
import DataLayer.Item;

/**
 * created by nilskjellbeck on 23.01.18
 */

public class ItemListActivity extends Activity {

    /** Attributes **/
    ListView simpleList;
    ArrayAdapter<String> arrayAdapter;
    Button floatButton;
    String tableName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from list_activity.xml **/
        setContentView(R.layout.list_activity);

        Intent intent = getIntent();
        tableName =  intent.getExtras().getString("tableName");

        floatButton = (Button) findViewById(R.id.createListButton);
        simpleList = (ListView)findViewById(R.id.listView);
        registerForContextMenu(simpleList);

        final String firstColumn = new TableList(getApplicationContext()).getColumnNames(tableName).get(0);
        ArrayList<Item> items = new TableList(getApplicationContext()).open(tableName);

        ArrayList<String> listViewItems = new ArrayList<String>();
        for (Item item : items) { listViewItems.add(item.getItemMap().get(firstColumn).toString()); }

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, listViewItems.toArray(new String[0]));
        simpleList.setAdapter(arrayAdapter);

        simpleList.setClickable(true);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /** Start ItemListActivity.class **/
                Intent myIntent = new Intent(ItemListActivity.this,
                        ItemViewActivity.class);
                myIntent.putExtra("tableName", tableName);
                myIntent.putExtra("itempos", position);
                startActivity(myIntent);
            }
        });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Instantiate an AlertDialog.Builder with its constructor **/
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemListActivity.this);
                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                /** Sets up some characteristics of the dialog **/
                builder.setTitle("Add Item");
                builder.setMessage("Type in the values of the item you want to add.");

                final ArrayList<EditText> inputs = new ArrayList<EditText>();
                final ArrayList<String> columnNames = new TableList(getApplicationContext()).getColumnNames(tableName);
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
                final AlertDialog dialog = builder.create();

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
                            new TableList(getApplicationContext()).addItem(tableName, newItem);

                            /* Update List View */
                            ArrayList<Item> items = new TableList(getApplicationContext()).open(tableName);
                            ArrayList<String> listViewItems = new ArrayList<String>();
                            for (Item item : items) { listViewItems.add(item.getItemMap().get(firstColumn).toString()); }
                            arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, listViewItems.toArray(new String[0]));
                            simpleList.setAdapter(arrayAdapter);

                        /* Close Alert Dialog */
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "All fields need to be filled!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Edit");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        /* Context Menu Click Event Handler */
        if(menuItem.getTitle().equals("Edit")) {
            /** Start ItemListActivity.class **/
            Intent myIntent = new Intent(ItemListActivity.this,
                    ItemViewActivity.class);
            myIntent.putExtra("tableName", tableName);
            myIntent.putExtra("itempos", info.position);
            startActivity(myIntent);
        }
        else if (menuItem.getTitle().equals("Delete")) {
            /* Call method to delete table */
            new TableList(getApplicationContext()).deleteItem(tableName, info.position);

            /* Update the ListView */
            String firstColumn = new TableList(getApplicationContext()).getColumnNames(tableName).get(0);
            ArrayList<Item> items = new TableList(getApplicationContext()).open(tableName);

            ArrayList<String> listViewItems = new ArrayList<String>();
            for (Item item : items) { listViewItems.add(item.getItemMap().get(firstColumn).toString()); }

            arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, listViewItems.toArray(new String[0]));
            simpleList.setAdapter(arrayAdapter);

            return true;
        }
        return true;

    }

}

