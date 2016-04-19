package kawinpart.sorasak.buybakery;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by NekokanSama on 19/4/2559.
 */
public class ShowServiceActivity extends AppCompatActivity {
    //
    private TextView showNameTextView;
    private ListView serviceListView;
    private String namestring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service);

        bindWidget();

        showView();

        //Create ListView
        createListView();
    }// Main Method

    private void createListView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.service_table, null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        String[] iconStrings = new String[intCount];
        String[] priceStrings = new String[intCount];
        String[] nameStrings = new String[intCount];
        final String[] idStrings = new String[intCount];

        for (int i = 0; i < intCount; i++) {

            iconStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Image1));
            priceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Price));
            nameStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_Category));
            idStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_id));

            cursor.moveToNext();
        }  // for

        cursor.close();
        MyAdapter myAdapter = new MyAdapter(this, iconStrings, priceStrings, nameStrings);
        serviceListView.setAdapter(myAdapter);

        serviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ShowServiceActivity.this, DetallActivity.class);
                intent.putExtra("id", idStrings[i]);
                startActivity(intent);

            } // onItemClick
        });


    } // Create ListView

    private void showView() {
        namestring = getIntent().getStringExtra("NameUser");
        showNameTextView.setText(namestring);

    }

    private void bindWidget() {
        showNameTextView = (TextView) findViewById(R.id.textView7);
        serviceListView = (ListView) findViewById(R.id.listView);
    }

    public void clickReadOrder(View view) {

    } // clicik

} // Main class