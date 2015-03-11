package labs.sdm.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class XmlActivity extends Activity {

    private String file_name = "xml_file";

    private final int XML = 0;
    private final int DB = 1;

    private int operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        operation = getIntent().getIntExtra("operation", 0);

        if (!new File(getFilesDir(), file_name).exists()) {

            Toast.makeText(this, "NEW FILE", Toast.LENGTH_SHORT).show();

            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(openFileOutput(file_name, Context.MODE_PRIVATE));

                XmlSerializer serializer = Xml.newSerializer();
                try {
                    serializer.setOutput(writer);
                    serializer.startDocument("UTF-8", true);
                    serializer.startTag(null, "list");
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "icon", String.valueOf(android.R.drawable.ic_menu_add));
                    serializer.attribute(null, "title", "TITLE ITEM 1");
                    serializer.attribute(null, "description", "Description item 1");
                    serializer.endTag(null, "item");
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "icon", String.valueOf(android.R.drawable.ic_menu_agenda));
                    serializer.attribute(null, "title", "TITLE ITEM 2");
                    serializer.attribute(null, "description", "Description item 2");
                    serializer.endTag(null, "item");
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "icon", String.valueOf(android.R.drawable.ic_menu_always_landscape_portrait));
                    serializer.attribute(null, "title", "TITLE ITEM 3");
                    serializer.attribute(null, "description", "Description item 3");
                    serializer.endTag(null, "item");
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "icon", String.valueOf(android.R.drawable.ic_menu_call));
                    serializer.attribute(null, "title", "TITLE ITEM 4");
                    serializer.attribute(null, "description", "Description item 4");
                    serializer.endTag(null, "item");
                    serializer.startTag(null, "item");
                    serializer.attribute(null, "icon", String.valueOf(android.R.drawable.ic_menu_camera));
                    serializer.attribute(null, "title", "TITLE ITEM 5");
                    serializer.attribute(null, "description", "Description item 5");
                    serializer.endTag(null, "item");
                    serializer.endTag(null, "list");
                    serializer.endDocument();
                    serializer.flush();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
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

        ListView list = (ListView) findViewById(R.id.lvXml);

        HashMap<String, Object> item = null;
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        switch (operation) {
            case XML:
                try {
                    XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();

                    parser.setInput(openFileInput(file_name), "UTF-8");

                    int eventType = parser.next();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if ((eventType == XmlPullParser.START_TAG) && (parser.getName().equalsIgnoreCase("item"))) {
                            item = new HashMap<String, Object>();
                            //**********
                            //COMPLETAR
                            //**********
                            data.add(item);
                        }
                        eventType = parser.next();
                    }

                } catch (XmlPullParserException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;

            case DB:
                MySQLOpenHelper helper = new MySQLOpenHelper(this);
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT icon, title, description FROM list", null);

                while (cursor.moveToNext()) {
                    item = new HashMap<String, Object>();
                    item.put("icon",cursor.getInt(0));
                    item.put("title",cursor.getString(1));
                    item.put("description",cursor.getString(2));

                    //**********
                    //COMPLETAR
                    //**********
                    data.add(item);
                }
                break;
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this, data, R.layout.xml_item,
                new String[]{"icon", "title", "description"},
                new int[]{R.id.ivIconXml, R.id.tvTitleXml, R.id.tvDescriptionXml});
        list.setAdapter(adapter);
    }

}
