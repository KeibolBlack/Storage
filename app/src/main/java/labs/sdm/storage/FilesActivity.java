package labs.sdm.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;

public class FilesActivity extends Activity {

	private final int INTERNAL_MEMORY = 0;
	private final int SD_CARD = 1;
	private final int RAW_RESOURCE = 2;
	private final int ASSETS = 3; 

	private int operation;
	private String file_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_files);

		operation = getIntent().getIntExtra("operation", 0); 
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		OutputStreamWriter writer = null;
		try {

			switch(operation) {
			case INTERNAL_MEMORY:
				file_name = "internal_memory_file";
				writer = new OutputStreamWriter(openFileOutput(file_name, Context.MODE_PRIVATE));
				break;
			case SD_CARD:
				String state = Environment.getExternalStorageState();
				if (!state.equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
					return;
				}
				file_name = "sd_card_file";
				//**********
				//COMPLETAR
                //**********
				break;
			default:
				return;
			}
			writer.write(((EditText) findViewById(R.id.etFiles)).getText().toString());
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		InputStreamReader isr = null;
		BufferedReader reader = null;

		try {

			switch(operation) {
			case INTERNAL_MEMORY:
                file_name = "internal_memory_file";
				isr = new InputStreamReader(openFileInput(file_name));
				break;
			case SD_CARD:
				String state = Environment.getExternalStorageState();
				if (!state.equalsIgnoreCase(Environment.MEDIA_MOUNTED) && !state.equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY)) {
					return;
				}
                //**********
                //COMPLETAR
                //**********
				break;
			case RAW_RESOURCE:
				isr = new InputStreamReader(getResources().openRawResource(R.raw.text_file));
				break;
			case ASSETS:
				isr = new InputStreamReader(getAssets().open("assets_text_file"));
				break;
			}
			reader = new BufferedReader(isr);
			String s = null;
			StringBuffer buffer = new StringBuffer();
			while ((s = reader.readLine()) != null) {
				buffer.append(s + '\n');
			}
			((EditText) findViewById(R.id.etFiles)).setText(buffer.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
