package com.example.manhngo.ms.Util;

/**
 * Created by manhngo on 9/12/17.
 */

public final class DBUtitls {

    public static final String DATABASE_NAME = "MaSoi";
    public static final int DATABASE_VERSION = 1;

    public static final String PLAYERS_TABLE = "players";

    public static final String PLAYERS_COLUMN_ID = "_id";
    public static final String PLAYERS_COLUMN_NAME = "name";
    public static final String PLAYERS_COLUMN_FUNCTION = "function";

    public static final String PLAYER_DETAILS_TABLE = "player_details";

    public static final String PLAYER_DETAILS_COLUMN_ID = "_id";
    public static final String PLAYER_DETAILS_COLUMN_PLAYER = "player";
    public static final String PLAYER_DETAILS_COLUMN_FUNCTION = "function";
    public static final String PLAYER_DETAILS_COLUMN_SURVIVE = "survive";

    public static final String BAOVE_TABLE_NAME = "BAOVE";
    public static final String BAOVE_COLUMN_ID = "Id";
    public static final String BAOVE_COLUMN_PLAYER = "Player";

    public static final String CUPID_TABLE_NAME = "CUPID";
    public static final String CUPID_COLUMN_ID = "Id";
    public static final String CUPID_COLUMN_PLAYER = "Player";

    public static final String SOI_TABLE_NAME = "WOLF";
    public static final String SOI_COLUMN_ID = "Id";
    public static final String SOI_COLUMN_PLAYER = "Player";

    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String NOT_NULL = "NOT NULL";
    public static final String NULL = "NULL";

    public static final String TEXT_DATA_TYPE = "TEXT";
    public static final String REAL_DATA_TYPE = "REAL";
    public static final String BLOB_DATA_TYPE = "BLOB";


}
