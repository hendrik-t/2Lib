package tulip.presentationLayer;

/**
 * Created by JB on 12.02.2018.
 */

import android.os.Bundle;
import android.widget.TextView;
import org.jsoup.nodes.Document;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupActivity extends AppCompatActivity {


    public TextView textViewEan, textViewTitle;
    public Document doc;
    public String url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jsoup_activity);


    /*Bundle extras = getIntent().getExtras();
    result= extras.getString("STR_RESULT");*/
        Intent intent = getIntent();
        String ean = intent.getStringExtra("key");

        url = "https://www.barcodable.com/ean/"+ean;//result from ScanActivity
        textViewEan = findViewById(R.id. ean);
        textViewEan.setText("URL + Ean:"+url);
        textViewTitle = findViewById(R.id. title);
        textViewTitle.setText("Title search...");
        new DataGrabber().execute();
    }

    private class DataGrabber extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            // NO CHANGES TO UI TO BE DONE HERE
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //This is where we update the UI with the acquired data
            if (doc != null){
                textViewTitle.setText("Title: "+doc.title().toString());
            }else{
                textViewTitle.setText("Title search failed");
            }
        }
    }
}


