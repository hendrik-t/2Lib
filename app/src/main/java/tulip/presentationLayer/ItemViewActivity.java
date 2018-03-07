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

    Button editButton;
    Button cancelButton;
    Button saveButton;
    Item item;
    ArrayList<String> columnNames;
    ArrayList<TextView> textViews;
    ArrayList<EditText> editTexts;

    /* Help variables */
    private static final float INVISIBLE = 0f;
    private static final float VISIBLE = 1f;
    private static final float FADED = 0.85f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view_activity);

        /* Initialize and configure buttons */
        editButton = (Button) findViewById(R.id.editButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        editButton.setEnabled(true);
        editButton.setAlpha(VISIBLE);

        cancelButton.setEnabled(false);
        cancelButton.setAlpha(INVISIBLE);

        saveButton.setEnabled(false);
        saveButton.setAlpha(INVISIBLE);

        /* Get extras */
        Intent intent = getIntent();
        final String tableName = intent.getExtras().getString("tableName");
        final int itempos = intent.getExtras().getInt("itempos");

        // create the cointainer layout
        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        // Create new LinearLayout
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Get necessary informations and initialize ArrayLists
        textViews = new ArrayList<TextView>();
        editTexts = new ArrayList<EditText>();
        columnNames = new TableList(this).getColumnNames(tableName);
        item = new TableList(this).open(tableName).get(itempos);

        for(int i = 0; i < columnNames.size(); i++) {
            // Add textviews
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            textView.setText(columnNames.get(i));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            textView.setTextColor(0xff000000); // black
            //textView.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
            textView.setPadding(20, 40, 20, 10); // in pixels (left, top, right, bottom)
            textViews.add(textView);
            linearLayout.addView(textView);

            // Add EditTexts
            EditText editText = new EditText(this);
            editText.setText(item.getItemMap().get(columnNames.get(i)).toString());
            editText.setSingleLine(true);
            editText.setFocusable(false);
            editText.setAlpha(FADED);
            editText.setPadding(20, 10, 20, 40); // in pixels (left, top, right, bottom)
            editTexts.add(editText);
            linearLayout.addView(editText);
        }

        // Set context view
        container.addView(linearLayout);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable the edit button and enable cancel and save buttons
                toggleButtons();

                // make all edittexts editable
                for(int i = 0; i < editTexts.size(); i++) {
                    // let the user be able to change the values
                    editTexts.get(i).setFocusableInTouchMode(true);
                    editTexts.get(i).setAlpha(VISIBLE);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtons();

                // reset the edittext fields to actual values
                for(int i = 0; i < editTexts.size(); i++) {
                    editTexts.get(i).setText(item.getItemMap().get(columnNames.get(i)).toString());
                    editTexts.get(i).setFocusable(false);
                    editTexts.get(i).setAlpha(FADED);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item itemNew = new Item();

                // TODO: fill itemNew with the edittext values


                /* update item in DB */
                new TableList(ItemViewActivity.this).editItem(tableName, itempos, itemNew);
            }
        });
    }

    /* toggles between editButton and cancel & saveButton being visible */
    private void toggleButtons() {
        cancelButton.setEnabled(!cancelButton.isEnabled());
        cancelButton.setAlpha(editButton.getAlpha());

        editButton.setEnabled(!editButton.isEnabled());
        editButton.setAlpha(saveButton.getAlpha());

        saveButton.setEnabled(!saveButton.isEnabled());
        saveButton.setAlpha(cancelButton.getAlpha());
    }

}
