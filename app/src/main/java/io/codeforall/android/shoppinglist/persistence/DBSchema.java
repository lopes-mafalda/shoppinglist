package io.codeforall.android.shoppinglist.persistence;

import android.provider.BaseColumns;

public final class DBSchema {

    private DBSchema() {
    }

    public static class ItemsTable implements BaseColumns {
        public static final String TABLE = "items";
        public static final String COL_NAME = "name";
        public static final String COL_QUANTITY = "quantity";
    }
}
