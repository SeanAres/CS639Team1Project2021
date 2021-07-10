package pace.cs639.healthyshopper;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchNutrients extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mNutrientTextView;
    FetchNutrients(TextView nutrientText) {
        this.mNutrientTextView = new WeakReference<>(nutrientText);

    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            String nutrients = null;
            JSONObject jsonObject = new JSONObject(s);
            JSONArray foodArray = jsonObject.getJSONArray("foods");
            JSONObject food = foodArray.getJSONObject(0);
            nutrients = food.getString(  "lowercaseDescription") + "\n";
            JSONArray nutrientsArray = food.getJSONArray("foodNutrients");
            int i = 0;
            while(i< nutrientsArray.length()){
                JSONObject nutrient = nutrientsArray.getJSONObject(i);
                try {
                    nutrients = nutrients + nutrient.getString("nutrientName") + ": " + nutrient.getString("value") + "\n";
                }catch (Exception e){
                    e.printStackTrace();
                }
                // Move to the next item.
                i++;
            }
            if (nutrients != null) {
                mNutrientTextView.get().setText(nutrients);

            }
            else {
                mNutrientTextView.get().setText("No Results, check spelling!");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            mNutrientTextView.get().setText("No Results, check spelling!");
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getNutrientsInfo(strings[0]);
    }
}
