package jdr.projet.myapplication.DataBase;

import android.provider.BaseColumns;

/**
 * Created by Aubry on 13/02/2018.
 */

public class DbContract {

    private DbContract(){

    }

    public static final class GameEntries implements BaseColumns {
        public static final String TABLE_NAME = "Game";
        public static final String NAME = "Name";
        public static final String EDITION = "Edition";
        public static final String EXTENSION = "Extension";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
