package tulip.presentationLayer;

import android.app.*;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Init **/
        TableList tableList = new TableList(getApplicationContext());
        String tableNames[] = tableList.getTableNames();

        /** Get the view from list_activity.xml **/
        setContentView(R.layout.list_activity);

        simpleList = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element_activity, R.id.textView, tableNames);
        simpleList.setAdapter(arrayAdapter);



        // TableList.getTablenames
    }

}

