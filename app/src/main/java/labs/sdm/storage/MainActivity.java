package labs.sdm.storage;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView list = (ListView) findViewById(R.id.lvDashboard);

		HashMap<String, Object> item;
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_preferences);
		item.put("Title", getResources().getString(R.string.dashboard_preferences));
		data.add(item);

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_search);
		item.put("Title", getResources().getString(R.string.dashboard_files));
		data.add(item);

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_save);
		item.put("Title", getResources().getString(R.string.dashboard_sdcard));
		data.add(item);

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_view);
		item.put("Title", getResources().getString(R.string.dashboard_xml));
		data.add(item);

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_today);
		item.put("Title", getResources().getString(R.string.dashboard_raw));
		data.add(item);

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_more);
		item.put("Title", getResources().getString(R.string.dashboard_assets));
		data.add(item);

		item = new HashMap<String, Object>();
		item.put("Icon", android.R.drawable.ic_menu_agenda);
		item.put("Title", getResources().getString(R.string.dashboard_database));
		data.add(item);

		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.dashboard_item, new String[]{"Icon", "Title"}, new int[]{R.id.ivDashboard, R.id.tvDashboard});
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			Intent intent;
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch(arg2) {
				case 0:
					intent = new Intent(getApplicationContext(), PreferencesActivity.class);
					startActivity(intent);
					break;
				case 1: 
					intent = new Intent(getApplicationContext(), FilesActivity.class);
					intent.putExtra("operation", 0);
					startActivity(intent);
					break;
				case 2: 
					intent = new Intent(getApplicationContext(), FilesActivity.class);
					intent.putExtra("operation", 1);
					startActivity(intent);
					break;
				case 3: 
					intent = new Intent(getApplicationContext(), XmlActivity.class);
					intent.putExtra("operation", 0);
					startActivity(intent);
					break;
				case 4: 
					intent = new Intent(getApplicationContext(), FilesActivity.class);
					intent.putExtra("operation", 2);
					startActivity(intent);
					break;
				case 5: 
					intent = new Intent(getApplicationContext(), FilesActivity.class);
					intent.putExtra("operation", 3);
					startActivity(intent);
					break;
				case 6: 
					intent = new Intent(getApplicationContext(), XmlActivity.class);
					intent.putExtra("operation", 1);
					startActivity(intent);
					break;
				}
			}
		});
		
	}

}
