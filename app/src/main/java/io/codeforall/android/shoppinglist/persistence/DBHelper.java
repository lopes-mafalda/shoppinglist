package io.codeforall.android.shoppinglist.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "shopping_list.db";
    private static final int DATABASE_VERSION_DEFAULT = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_DEFAULT);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();

        String query = builder.append("CREATE TABLE ")
                .append(DBSchema.ItemsTable.TABLE)
                .append("(")
                .append(BaseColumns._ID) // This ensures the _id column exists
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(DBSchema.ItemsTable.COL_NAME)
                .append(" TEXT NOT NULL,")
                .append(DBSchema.ItemsTable.COL_QUANTITY)
                .append(" INTEGER);")
                .toString();

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBSchema.ItemsTable.TABLE);
        onCreate(db);


    }

    public SQLiteDatabase getDb() {
        if (db != null) {
            return db;
        }

        db = getWritableDatabase();
        return db;
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }
}
