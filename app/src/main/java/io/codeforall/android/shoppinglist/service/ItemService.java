package io.codeforall.android.shoppinglist.service;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.codeforall.android.shoppinglist.persistence.DBHelper;
import io.codeforall.android.shoppinglist.persistence.DBSchema;
import io.codeforall.android.shoppinglist.persistence.model.Item;

public class ItemService {

    private DBHelper dbHelper;

    public ItemService(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    //returns item with id set
    public Item save(Item item) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.ItemsTable.COL_NAME, item.getName());
        values.put(DBSchema.ItemsTable.COL_QUANTITY, item.getQuantity());

        long id = dbHelper.getDb().insert(DBSchema.ItemsTable.TABLE, null, values);
        item.setId(id);

        return item;
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();

        String[] projection = {DBSchema.ItemsTable._ID, DBSchema.ItemsTable.COL_NAME, DBSchema.ItemsTable.COL_QUANTITY};

        Cursor cursor = dbHelper.getDb().query(
                DBSchema.ItemsTable.TABLE,
                projection,
                null, null, null, null, null
        );

        int idIndex = cursor.getColumnIndexOrThrow(DBSchema.ItemsTable._ID);
        int nameIndex = cursor.getColumnIndexOrThrow(DBSchema.ItemsTable.COL_NAME);
        int quantIndex = cursor.getColumnIndexOrThrow(DBSchema.ItemsTable.COL_QUANTITY);

        while (cursor.moveToNext()){
            items.add(new Item(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getInt(quantIndex)));
        }

        cursor.close();
        return items;
    }

    public void clear() {
        dbHelper.getDb().delete(DBSchema.ItemsTable.TABLE, null, null);
    }


    public void seed(){
        save(new Item("batatas", 10));
        save(new Item("cenouras", 5));
        save(new Item("lanterna", 1));
    }

    public void delete(Item item) {
        String whereClause = DBSchema.ItemsTable._ID + " = ?";
        String[] whereArgs = { String.valueOf(item.getId()) };

        dbHelper.getDb().delete(DBSchema.ItemsTable.TABLE, whereClause, whereArgs);
    }

}
