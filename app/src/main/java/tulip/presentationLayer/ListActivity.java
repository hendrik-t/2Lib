package tulip.presentationLayer;

import android.app.*;
import android.os.Bundle;


public class ListActivity extends Activity {

    /** Attributes **/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from activity_main.xml **/
        setContentView(R.layout.list_activity);
    }

}

