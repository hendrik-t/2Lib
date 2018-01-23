package tulip.presentationLayer;

import android.app.*;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListActivity extends Activity {

    /** Attributes **/

    ListView simpleList;
    String countryList[] = {"India","China","GG"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from activity_main.xml **/
        setContentView(R.layout.list_activity);

        simpleList = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_activity, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);

        // TableList.getTablenames
    }

}

