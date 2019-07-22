package moonc.example.com.useexternaldatabase.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import moonc.example.com.useexternaldatabase.models.ObjectCreated;
import moonc.example.com.useexternaldatabase.R;
import moonc.example.com.useexternaldatabase.oldersqliteclass.SqliteHelper;
import moonc.example.com.useexternaldatabase.adapters.adapter;
import moonc.example.com.useexternaldatabase.sqlites.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ObjectCreated objectCreated;
    ArrayList<ObjectCreated> mArrayList;
    adapter mAdapter;
    Cursor c = null;
    DatabaseHelper myDbHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init_views();
        inti_variables();
        init_functions();

        ///TESTING//////////////





    }

    private void init_functions() {

        myDbHelper.createDataBase();
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(getApplicationContext(), "Successfully Imported", Toast.LENGTH_SHORT).show();
        c = myDbHelper.query("demo", null, null, null, null, null, null);
        c.moveToFirst();
        while (c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            byte[] blob = c.getBlob(2);
            objectCreated = new ObjectCreated(id,name,blob);
            mArrayList.add(objectCreated);
        }

        Log.i(TAG, "init_functions: "+mArrayList.size());
        mAdapter = new adapter(this,mArrayList);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,Profile.class);
                intent.putExtra("id",mArrayList.get(position).getId());
                intent.putExtra("name",mArrayList.get(position).getName());
                intent.putExtra("image",mArrayList.get(position).getImage());
                startActivity(intent);
            }
        });

    }

    private void init_views() {
        listView = (ListView) findViewById(R.id.lst_load);
    }

    private void inti_variables() {
        myDbHelper = new DatabaseHelper(getApplicationContext());
        mArrayList = new ArrayList<>();
    }
}
