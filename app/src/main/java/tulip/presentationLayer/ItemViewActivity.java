package tulip.presentationLayer;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * Created by Hendrik on 05-Mar-18.
 */

public class ItemViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create new LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Add textviews
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textView1.setText("programmatically created TextView1");
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20); // in pixels (left, top, right, bottom)
        linearLayout.addView(textView1);

        TextView textView2 = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
        textView2.setLayoutParams(layoutParams);
        textView2.setText("programmatically created TextView2");
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView2.setBackgroundColor(0xffffdbdb); // hex color 0xAARRGGBB
        linearLayout.addView(textView2);

        // Set context view
        setContentView(linearLayout);
    }
}
