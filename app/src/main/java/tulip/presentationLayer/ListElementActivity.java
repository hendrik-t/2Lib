package tulip.presentationLayer;

import android.app.Activity;
import android.os.Bundle;

/**
 * created by nilskjellbeck on 23.01.18
 */

public class ListElementActivity extends Activity {

    /** Attributes **/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Get the view from activity_main.xml **/
        setContentView(R.layout.list_element_activity);
    }

}

