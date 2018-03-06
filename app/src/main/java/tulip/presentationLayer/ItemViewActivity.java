package tulip.presentationLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

import DataLayer.DataAccessLayer.TableList;
import DataLayer.Item;

public class ItemViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view_activity);

        Intent intent = getIntent();
        String tableName = intent.getExtras().getString("tableName");
        int itempos = intent.getExtras().getInt("itempos");

        // create the cointainer layout
        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        // Create new LinearLayout
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Get necessary informations and initialize ArrayLists
        final ArrayList<TextView> textViews = new ArrayList<TextView>();
        final ArrayList<EditText> editTexts = new ArrayList<EditText>();
        ArrayList<String> columnNames = new TableList(this).getColumnNames(tableName);
        Item item = new TableList(this).open(tableName).get(itempos);

        for(int i = 0; i < columnNames.size(); i++) {
            // Add textviews
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            textView.setText(columnNames.get(i));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            textView.setTextColor(0xff000000); // black
            //textView.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
            textView.setPadding(20, 20, 20, 20); // in pixels (left, top, right, bottom)
            textViews.add(textView);
            linearLayout.addView(textView);

            // Add EditTexts
            EditText editText = new EditText(this);
            editText.setText(item.getItemMap().get(columnNames.get(i)).toString());
            editText.setSingleLine(true);
            editText.setFocusable(false);
            editText.setAlpha((float)0.85);
            editTexts.add(editText);
            linearLayout.addView(editText);
        }

        // Set context view
        container.addView(linearLayout);

        final Button editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable the edit button
                editButton.setClickable(false);
                editButton.setAlpha(0);

                // make all edittexts editable
                for(int i = 0; i < editTexts.size(); i++)
                {
                    // let the user be able to change the values
                    editTexts.get(i).setFocusableInTouchMode(true);
                    editTexts.get(i).setAlpha(1);
                }
            }
        });
    }

}
