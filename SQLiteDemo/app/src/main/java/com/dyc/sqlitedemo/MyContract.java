package com.dyc.sqlitedemo;

import android.provider.BaseColumns;

/**
 * Created by apple on 2018/2/18.
 */

public final class MyContract {

    private MyContract(){}

    public static class MyEntry implements BaseColumns{
        public static final String TABLE_NAME="FavoriteTVShow";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_EMAIL="email";
        public static final String COLUMN_NAME_FAVORITE="favorite";
    }

}
