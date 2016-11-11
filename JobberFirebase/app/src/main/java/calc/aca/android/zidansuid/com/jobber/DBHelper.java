package calc.aca.android.zidansuid.com.jobber;

/**
 * Created by zidansuid on 11/2/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "cust.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Customer.TABLE  + "("
                + Customer.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Customer.KEY_custNme + " TEXT, "
                + Customer.KEY_custBus + " TEXT, "
                + Customer.KEY_custAdrs1 + " TEXT, "
                + Customer.KEY_custAdrs2 + " TEXT, "
                + Customer.KEY_custphn + " TEXT, "
                + Customer.KEY_custmail + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Customer.TABLE);

        // Create tables again
        onCreate(db);

    }

}