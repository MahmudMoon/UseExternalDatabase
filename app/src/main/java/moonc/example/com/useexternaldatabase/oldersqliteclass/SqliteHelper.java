package moonc.example.com.useexternaldatabase.oldersqliteclass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteHelper extends SQLiteOpenHelper {
    public static String dbName = "databases/Dem.db";
    public static int Version = 10;
    public static Context mContext;
    public static String tableName = "demo";
    public static String DB_Path ;
    SQLiteDatabase sqLiteDatabase;


    public SqliteHelper(Context context) {
        super(context, dbName, null, Version);
        mContext= context;
        if(Build.VERSION.SDK_INT>=17){
            DB_Path = mContext.getApplicationInfo().dataDir + "/databases/";
        }else
            DB_Path = "/data/data/"+mContext.getPackageName()+"/databases/";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        if(sqLiteDatabase!=null){
            sqLiteDatabase.close();
        }
        super.close();
    }

    public boolean CheckDataBase(){
        SQLiteDatabase temp = null;
        try{
            String path = DB_Path + dbName;
            temp = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);

        }catch (Exception e){

        }

        if(temp!=null){
            temp.close();
        }
        return temp!=null ? true : false;
    }




    public void CopyDataBase(){
        try {
            InputStream inputStream = mContext.getAssets().open(dbName);
            String path_out_put_Stream = DB_Path+dbName;
            OutputStream outputStream = new FileOutputStream(path_out_put_Stream);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void CreateDb(){
        boolean isExist = CheckDataBase();
        if(isExist){

        }else {
            this.getReadableDatabase();
            CopyDataBase();
        }
    }

    public Cursor Show(){
        sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from "+tableName;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
       return cursor;
    }


    public void OpenDataBase(){
        String path = DB_Path+dbName;
        sqLiteDatabase = SQLiteDatabase.openDatabase(path,null, SQLiteDatabase.OPEN_READONLY);
    }

}
