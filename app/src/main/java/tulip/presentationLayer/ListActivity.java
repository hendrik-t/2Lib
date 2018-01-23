package tulip.presentationLayer;

import android.app.*;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import DataLayer.DataAccessLayer.TableList;


public class ListActivity extends Activity {

    /** Attributes **/

    ListView simpleList;
    TableList tableList = new TableList(this);
    ArrayList<String> tableNames = tableList.getTableNames();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from list_activity.xml **/
        setContentView(R.layout.list_activity);

        simpleList = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_element_activity, R.id.textView, tableNames);
        simpleList.setAdapter(arrayAdapter);



        // TableList.getTablenames
    }

}

