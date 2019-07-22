package moonc.example.com.useexternaldatabase.sqlites;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "Dem";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String TAG = "DatabaseHelper";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 11);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + "moonc.example.com.useexternaldatabase" + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() {
        boolean dbExist = checkDataBase();
        Log.i(TAG, "createDataBase: "+dbExist);
        if (dbExist) {
            copyDataBase();
        } else {
//                copyDataBase();
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase()  {

        try {
            InputStream myInput = null;
            try {
                //String[] locales = myContext.getAssets().getLocales();
                //Log.i(TAG, "copyDataBase: "+locales);
                if(myContext!=null) {
                    AssetManager assetManager = myContext.getAssets();
                    if(assetManager!=null){
                       myInput= assetManager.open(DB_NAME+".db");
                    }else{
                        Log.i(TAG, "copyDataBase: "+"Asset Manager NULL");
                    }
                }else{
                    Log.i(TAG, "copyDataBase: "+"CONTEXT NULL");
                }
               // myInput = .getAssets().open(DB_NAME);
            }catch (Exception e){
                Log.i(TAG, "copyDataBase: "+e.toString());
            }
            Log.i(TAG, "copyDataBase: "+"Completed");
            String outFileName = DB_PATH + DB_NAME;
            Log.i(TAG, "copyDataBase: "+"Completed");
            OutputStream myOutput = new FileOutputStream(outFileName);
            Log.i(TAG, "copyDataBase: "+"Completed");
            byte[] buffer = new byte[10];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (Exception e){
            Log.i(TAG, "copyDataBase: "+e.toString());
        }
        Log.i(TAG, "copyDataBase: "+"Completed");

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
                copyDataBase();

    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query(table, null, null, null, null, null, null);
    }


}
