package icenterdata;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class DataReader extends SQLiteOpenHelper {
    // Database Info
//    private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_TILES= "tiles";

    // tiles table columns
    private static final String ZOOM_LEVEL = "zoom_level";
    private static final String TILE_COLUMN = "tile_column";
    private static final String TILE_ROW= "tile_row";
    private static final String TILE_DATA= "tile_data";

    public DataReader(Context context, String db_path) {
        super(context, db_path, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Do nothing.
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion != newVersion) {
//            // Simplest implementation is to drop all old tables and recreate them
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//            onCreate(db);
//        }
    }

    public String getTileData(int col, int row) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery("select * from tiles where tile_column=" + col + " and tile_row="+ row,new String[]{"1"});
        String res = "";
        if (cur.moveToFirst()) {
            res = cur.getString(cur.getColumnIndex("tile_data"));
        }

        db.close();
        return res;
    }

//    public synchronized String getTileData
}