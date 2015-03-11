package labs.sdm.storage;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesActivity extends Activity {

	private final String PREFERENCES_FILE = "admclass4_preferences";
	private final String PREFERENCES_EDIT_TEXT = "EditText";
	private final String PREFERENCES_CHECK_BOX = "CheckBox";
	private final String PREFERENCES_SPINNER = "Spinner";
	private final String PREFERENCES_RATING_BAR = "RatingBar";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putString(PREFERENCES_EDIT_TEXT, ((EditText) findViewById(R.id.etPreferences)).getText().toString());
        prefsEditor.putBoolean(PREFERENCES_CHECK_BOX, ((CheckBox) findViewById(R.id.cbPreferences)).isChecked());
        prefsEditor.putInt(PREFERENCES_SPINNER, ((Spinner) findViewById(R.id.spPreferences)).getSelectedItemPosition());
        prefsEditor.putFloat(PREFERENCES_RATING_BAR, ((RatingBar) findViewById(R.id.rbPreferences)).getRating());
        prefsEditor.commit();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        if (preferences==null) return;
        ((EditText) findViewById(R.id.etPreferences)).setText(preferences.getString(PREFERENCES_EDIT_TEXT,"Empty"));
        ((CheckBox) findViewById(R.id.cbPreferences)).setChecked(preferences.getBoolean(PREFERENCES_CHECK_BOX,false));
        ((Spinner) findViewById(R.id.spPreferences)).setSelection(preferences.getInt(PREFERENCES_SPINNER,0));
        ((RatingBar) findViewById(R.id.rbPreferences)).setRating(preferences.getFloat(PREFERENCES_RATING_BAR,0));
	}

}
