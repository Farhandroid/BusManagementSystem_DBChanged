package tanvir.busmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 04-Jul-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, TableAttribute.DATABASE_NAME, null, TableAttribute.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        TableAttribute tableAttribute= new TableAttribute();
        String query = tableAttribute.userTableCreation();
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertDataInDatabase(String userName , String password, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.COL_USERNAME,userName);
        contentValues.put(TableAttribute.COL_Password,password);
        contentValues.put(TableAttribute.COL_EMAIL,email);

        long result = db.insert(TableAttribute.TABLE_NAME,null,contentValues);

        if(result >0)
        {
            db.close();
            return  true;
        }
        else
        {
            db.close();
            return  false;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TableAttribute.TABLE_NAME,null);


        return  result;
    }


    public boolean deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();



        int result = db.delete(TableAttribute.TABLE_NAME,null,null);
        db.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteNOteFromDatabase(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();



        int result = db.delete(TableAttribute.TABLE_NAME,TableAttribute.COL_USERNAME + " = ?", new String[] {String.valueOf(userName)});
        db.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }


    public  boolean checkLogin(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;

        String selectString = "SELECT * FROM " + TableAttribute.TABLE_NAME + " WHERE " + TableAttribute.COL_USERNAME + " = ?  AND "+ TableAttribute.COL_Password + " = ?";

        cursor = db.rawQuery(selectString, new String[] {userName,password});

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            cursor.close();
            db.close();
            return  true;
        }
        else
        {
            cursor.close();
            db.close();
            return  false;
        }



    }


}
