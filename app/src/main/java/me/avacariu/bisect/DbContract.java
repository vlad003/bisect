package me.avacariu.bisect;

import android.provider.BaseColumns;

/**
 * Created by vlad on 03/04/15.
 */
public class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DbContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "bisect";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_START = "start";
        public static final String COLUMN_NAME_END = "end";
        public static final String COLUMN_NAME_TYPE = "type";

        public static final String COLUMN_TYPE_BISECT = "bisect";
        public static final String COLUMN_TYPE_BIN_SEARCH = "bin";

    }

}
