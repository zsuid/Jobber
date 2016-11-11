package calc.aca.android.zidansuid.com.jobberversion2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zidansuid on 11/2/16.
 */

public class CustomerRepo {
    private DBHelper dbHelper;

    public CustomerRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Customer customer) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.KEY_custNme, customer.name);
        values.put(Customer.KEY_custBus,customer.business);
        values.put(Customer.KEY_custAdrs1, customer.address1);
        values.put(Customer.KEY_custAdrs2,customer.address2);
        values.put(Customer.KEY_custphn, customer.phone);
        values.put(Customer.KEY_custmail,customer.email);


        // Inserting Row
        long customer_Id = db.insert(Customer.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) customer_Id;
    }

    public void delete(int customer_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Customer.TABLE, Customer.KEY_ID + "= ?", new String[] { String.valueOf(customer_Id) });
        db.close(); // Closing database connection
    }

    public void update(Customer customer) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Customer.KEY_custNme, customer.name);
        values.put(Customer.KEY_custBus,customer.business);
        values.put(Customer.KEY_custAdrs1, customer.address1);
        values.put(Customer.KEY_custAdrs2,customer.address2);
        values.put(Customer.KEY_custphn, customer.phone);
        values.put(Customer.KEY_custmail,customer.email);

        db.update(Customer.TABLE, values, Customer.KEY_ID + "= ?", new String[] { String.valueOf(customer.customer_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getCustomerList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Customer.KEY_ID + "," +
                Customer.KEY_custNme + "," +
                Customer.KEY_custBus + "," +
                Customer.KEY_custAdrs1 + "," +
                Customer.KEY_custAdrs2 + "," +
                Customer.KEY_custphn + "," +
                Customer.KEY_custmail +
                " FROM " + Customer.TABLE;


        ArrayList<HashMap<String, String>> customerList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> customer = new HashMap<String, String>();
                customer.put("id", cursor.getString(cursor.getColumnIndex(Customer.KEY_ID)));
                customer.put("name", cursor.getString(cursor.getColumnIndex(Customer.KEY_custNme)));
                customerList.add(customer);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return customerList;

    }

    public Customer getCustomerById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Customer.KEY_ID + "," +
                Customer.KEY_custNme + "," +
                Customer.KEY_custBus + "," +
                Customer.KEY_custAdrs1 + "," +
                Customer.KEY_custAdrs2 + "," +
                Customer.KEY_custphn + "," +
                Customer.KEY_custmail +
                " FROM " + Customer.TABLE
                + " WHERE " +
                Customer.KEY_ID + "=?";

        int iCount =0;
        Customer customer = new Customer();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                customer.customer_ID =cursor.getInt(cursor.getColumnIndex(Customer.KEY_ID));
                customer.name =cursor.getString(cursor.getColumnIndex(Customer.KEY_custNme));
                customer.business  =cursor.getString(cursor.getColumnIndex(Customer.KEY_custBus));
                customer.address1 =cursor.getString(cursor.getColumnIndex(Customer.KEY_custAdrs1));
                customer.address2  =cursor.getString(cursor.getColumnIndex(Customer.KEY_custAdrs2));
                customer.phone  =cursor.getString(cursor.getColumnIndex(Customer.KEY_custphn));
                customer.email =cursor.getString(cursor.getColumnIndex(Customer.KEY_custmail));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return customer;
    }

}