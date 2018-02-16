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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import DataLayer.DataAccessLayer.TableList;
import DataLayer.Item;

/**
 * created by nilskjellbeck on 23.01.18
 */

public class ListElementActivity extends Activity {

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

        floatButton = (Button) findViewById(R.id.newListButton);
        simpleList = (ListView)findViewById(R.id.listView);
        registerForContextMenu(simpleList);

        String firstColumn = new TableList(getApplicationContext()).getColumnNames(tableName).get(0);
        ArrayList<Item> items = new TableList(getApplicationContext()).open(tableName);

        ArrayList<String> listViewItems = new ArrayList<String>();
        for (Item item : items) { listViewItems.add(item.getItemMap().get(firstColumn).toString()); }

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, listViewItems.toArray(new String[0]));
        simpleList.setAdapter(arrayAdapter);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        /* Context Menu Click Event Handler */
        if (menuItem.getTitle().equals("Delete")) {
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

