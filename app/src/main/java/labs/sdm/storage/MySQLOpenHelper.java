package labs.sdm.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLOpenHelper extends SQLiteOpenHelper {

	public MySQLOpenHelper(Context context) {
		super(context, "list_database.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.beginTransaction();
		arg0.execSQL("CREATE TABLE list (id INTEGER PRIMARY KEY AUTOINCREMENT, icon INTEGER NOT NULL, title TEXT NOT NULL, description TEXT NOT NULL);");
		arg0.execSQL("INSERT INTO list (icon, title, description) VALUES (" + android.R.drawable.ic_menu_zoom + ", 'DB Title 1', 'DB Description 1');");
		arg0.execSQL("INSERT INTO list (icon, title, description) VALUES (" + android.R.drawable.ic_menu_week + ", 'DB Title 2', 'DB Description 2');");
		arg0.execSQL("INSERT INTO list (icon, title, description) VALUES (" + android.R.drawable.ic_menu_view + ", 'DB Title 3', 'DB Description 3');");
		arg0.execSQL("INSERT INTO list (icon, title, description) VALUES (" + android.R.drawable.ic_menu_upload_you_tube + ", 'DB Title 4', 'DB Description 4');");
		arg0.execSQL("INSERT INTO list (icon, title, description) VALUES (" + android.R.drawable.ic_menu_today + ", 'DB Title 5', 'DB Description 5');");
		arg0.execSQL("INSERT INTO list (icon, title, description) VALUES (" + android.R.drawable.ic_menu_sort_alphabetically + ", 'DB Title 6', 'DB Description 6');");
		arg0.setTransactionSuccessful();
		arg0.endTransaction();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS list");
		onCreate(arg0);
	}

}
