//package com.example.manhngo.ms.models;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.example.manhngo.ms.NhanVat.Soi;
//import com.example.manhngo.ms.Util.DBUtitls;
//
//
///**
// * Created by manhngo on 9/26/17.
// */
//
//public class BaoveDatabaseHelper extends SQLiteOpenHelper {
//    public void addPlayer(Soi post) {
//        // Create and/or open the database for writing
//        SQLiteDatabase db = getWritableDatabase();
//
//        private static BaoveDatabaseHelper sInstance;
//
//        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
//        // consistency of the database.
//        db.beginTransaction();a
//        try {
//            // The user might already exist in the database (i.e. the same user created multiple posts).
//            long userId = addOrUpdateUser(post.user);
//
//            ContentValues values = new ContentValues();
//            values.put(KEY_POST_USER_ID_FK, userId);
//            values.put(KEY_POST_TEXT, post.text);
//
//            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
//            db.insertOrThrow(TABLE_POSTS, null, values);
//            db.setTransactionSuccessful();
//        } catch (Exception e) {
//            Log.d(TAG, "Error while trying to add post to database");
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public static synchronized BaoveDatabaseHelper getInstance(Context context) {
//        // Use the application context, which will ensure that you
//        // don't accidentally leak an Activity's context.
//        // See this article for more information: http://bit.ly/6LRzfx
//        if (sInstance == null) {
//            sInstance = new PostsDatabaseHelper(context.getApplicationContext());
//        }
//        return sInstance;
//    }
//
//    public static final String CREATE_PLAYERD_TABLE_SQL =
//            "CREATE TABLE " + DBUtitls.PLAYER_TABLE_NAME + " ("
//                    + DBUtitls.PLAYER_COLUMN_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.PRIMARY_KEY + ", "
//                    + DBUtitls.PLAYER_COLUMN_NAME + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ")";
//    public static final String CREATE_BAOVE_TABLE_SQL =
//            "CREATE TABLE " + DBUtitls.BAOVE_TABLE_NAME + " ("
//                    + DBUtitls.BAOVE_COLUMN_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.PRIMARY_KEY + ", "
//                    + DBUtitls.BAOVE_COLUMN_PLAYER + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ")";
//    public static final String INSERT_PLAYED_TABLE_SQL =
//            "INSERT INTO " + DBUtitls.PLAYER_TABLE_NAME + " (" + DBUtitls.PLAYER_COLUMN_ID + ", " + DBUtitls.PLAYER_COLUMN_NAME + ")"
//                    + " VALUES "
//                    + "('1', 'Manh'), " + "('2', 'Trang')";
//    private static final String DATABASE_NAME = "MaSoi";
//    private static final int DATABASE_VERSION = 1;
//
//    BaoveDatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_BAOVE_TABLE_SQL);
//        sqLiteDatabase.execSQL(CREATE_PLAYERD_TABLE_SQL);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        onCreate(sqLiteDatabase);
//    }
//
//    public long addOrUpdateUser(Soi user) {
//        // The database connection is cached so it's not expensive to call getWriteableDatabase() multiple times.
//        SQLiteDatabase db = getWritableDatabase();
//        long userId = -1;
//
//        db.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_USER_NAME, user.userName);
//            values.put(KEY_USER_PROFILE_PICTURE_URL, user.profilePictureUrl);
//
//            // First try to update the user in case the user already exists in the database
//            // This assumes userNames are unique
//            int rows = db.update(TABLE_USERS, values, KEY_USER_NAME + "= ?", new String[]{user.userName});
//
//            // Check if update succeeded
//            if (rows == 1) {
//                // Get the primary key of the user we just updated
//                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
//                        KEY_USER_ID, TABLE_USERS, KEY_USER_NAME);
//                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(user.userName)});
//                try {
//                    if (cursor.moveToFirst()) {
//                        userId = cursor.getInt(0);
//                        db.setTransactionSuccessful();
//                    }
//                } finally {
//                    if (cursor != null && !cursor.isClosed()) {
//                        cursor.close();
//                    }
//                }
//            } else {
//                // user with this userName did not already exist, so insert new user
//                userId = db.insertOrThrow(TABLE_USERS, null, values);
//                db.setTransactionSuccessful();
//            }
//        } catch (Exception e) {
//            Log.d(TAG, "Error while trying to add or update user");
//        } finally {
//            db.endTransaction();
//        }
//        return userId;
//    }
//}