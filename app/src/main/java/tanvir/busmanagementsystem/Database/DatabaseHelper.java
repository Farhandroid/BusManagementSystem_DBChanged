package tanvir.busmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;
import tanvir.busmanagementsystem.MOdelClass.BusScheduleInfoMC;

/**
 * Created by USER on 04-Jul-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, TableAttribute.DATABASE_NAME, null, TableAttribute.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        TableAttribute tableAttribute = new TableAttribute();
        String query = tableAttribute.userTableCreation();
        sqLiteDatabase.execSQL(query);

        query = tableAttribute.busTableCreation();

        try {
            sqLiteDatabase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(context, "Problem in create User Table", Toast.LENGTH_SHORT).show();
        }

        try {
            sqLiteDatabase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(context, "Problem in create Bus Table", Toast.LENGTH_SHORT).show();
        }

        query = tableAttribute.seatTableCreation();
        try {
            sqLiteDatabase.execSQL(query);

        } catch (Exception e) {
            Toast.makeText(context, "Problem in create Seat Table", Toast.LENGTH_SHORT).show();

        }

        query = tableAttribute.busScheduleTableCreation();
        try {
            sqLiteDatabase.execSQL(query);

        } catch (Exception e) {
            Toast.makeText(context, "Problem in create Bus schedule Table", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertDataInDatabase(String userName, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.COL_USERNAME, userName);
        contentValues.put(TableAttribute.COL_Password, password);
        contentValues.put(TableAttribute.COL_EMAIL, email);

        long result = db.insert(TableAttribute.USER_TABLE, null, contentValues);

        if (result > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TableAttribute.USER_TABLE, null);


        return result;
    }


    public boolean deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();


        int result = db.delete(TableAttribute.USER_TABLE, null, null);
        db.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteNOteFromDatabase(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TableAttribute.USER_TABLE, TableAttribute.COL_USERNAME + " = ?", new String[]{String.valueOf(userName)});
        db.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }


    public boolean checkLogin(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;

        String selectString = "SELECT * FROM " + TableAttribute.USER_TABLE + " WHERE " + TableAttribute.COL_USERNAME + " = ?  AND " + TableAttribute.COL_Password + " = ?";

        cursor = db.rawQuery(selectString, new String[]{userName, password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }

    }

    public boolean addBusInDatabase(BusINfoMC busINfoMC) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.COL_BUS_NAME, busINfoMC.getBusName());
        contentValues.put(TableAttribute.COL_BUS_ID, busINfoMC.getBusID());
        contentValues.put(TableAttribute.COL_BUS_TYPE, busINfoMC.getBusType());
        contentValues.put(TableAttribute.COL_TOTAL_BUS_SEAT, busINfoMC.getTotalBusSeat());
        contentValues.put(TableAttribute.COL_BUS_SEAT_PRICE, busINfoMC.getBusSeatPrice());

        ///Toast.makeText(context,busINfoMC.getBusName()+"\n"+busINfoMC.getBusID()+"\n"+busINfoMC.getBusType()+"\n"+busINfoMC.getDepartureDate()+"\n"+busINfoMC.getDepartureTime()+"\n"+busINfoMC.getStartFrom()+"\n"+busINfoMC.getDestination()+"\n"+busINfoMC.getTotalBusSeat()+"\n"+busINfoMC.getBusSeatPrice(), Toast.LENGTH_LONG).show();

        long result = db.insert(TableAttribute.BUS_TABLE, null, contentValues);

        if (result > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }


    /*public ArrayList<BusINfoMC> getBusListFromBusTable(String startFrom, String destination, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TableAttribute.USER_TABLE, null);


        Cursor cursor;

        String selectString = "SELECT * FROM " + TableAttribute.BUS_TABLE + " WHERE " + TableAttribute.COL_START_FROM + " = ?  AND " + TableAttribute.COL_DESTINATION + " = ?" + " AND " + TableAttribute.COL_DEPARTURE_DATE + " = ?";

        cursor = db.rawQuery(selectString, new String[]{startFrom, destination, date});

        ArrayList<BusINfoMC> busINfoMCArrayList = new ArrayList<>();


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                BusINfoMC busINfoMC = new BusINfoMC();

                busINfoMC.setBusName(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_NAME)));
                busINfoMC.setBusType(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_TYPE)));
                busINfoMC.setDepartureTime(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_DEPARTURE_TIME)));
                busINfoMC.setBusSeatPrice(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_SEAT_PRICE)));

                busINfoMCArrayList.add(busINfoMC);

                cursor.moveToNext();
            }



        }
        return busINfoMCArrayList;
    }*/


    public Cursor getBusNameId() {
        ArrayList<BusINfoMC> busINfoMCArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TableAttribute.COL_BUS_ID + " , " + TableAttribute.COL_BUS_NAME + " FROM " + TableAttribute.BUS_TABLE, null);

        return cursor;


    }

    public boolean addBusScheduleInDatabase(BusScheduleInfoMC busScheduleInfoMC) {
        SQLiteDatabase db = this.getWritableDatabase();



        ///Toast.makeText(context, "Database \n" + busScheduleInfoMC.getBusID() + "\n" + busScheduleInfoMC.getBusName() + "\n" + busScheduleInfoMC.getDepartureDate() + "\n" + busScheduleInfoMC.getDepartureTime() + "\n" + busScheduleInfoMC.getArrivalDate() + "\n" + busScheduleInfoMC.getArrivaleTime() + "\n", Toast.LENGTH_LONG).show();

        String selectString = "SELECT * FROM " + TableAttribute.BUS_TABLE+ " WHERE " + TableAttribute.COL_BUS_ID + " = ?  AND " + TableAttribute.COL_BUS_NAME + " = ?";

        Cursor cursor = db.rawQuery(selectString, new String[]{busScheduleInfoMC.getBusID(), busScheduleInfoMC.getBusName()});

        ///long result = db.insert(TableAttribute.BUS_SCHEDULE_TABLE,null,contentValues);

        if (cursor.getCount()>0)
        {
            String sql1 = "insert into " + TableAttribute.BUS_SCHEDULE_TABLE + " ( " + TableAttribute.COL_BUS_ID + " , " + TableAttribute.COL_BUS_NAME + " , "+ TableAttribute.COL_DEPARTURE_LOCATION + " , "+ TableAttribute.COL_ARRIVAL_LOCATION + " , " + TableAttribute.COL_DEPARTURE_DATE + " , " + TableAttribute.COL_DEPARTUR_TIME + " , " + TableAttribute.COL_ARRIVAL_DATE + " , " + TableAttribute.COL_ARRIVAL_TIME + " ) values( '" + busScheduleInfoMC.getBusID() + "' , '" + busScheduleInfoMC.getBusName()+ "' , '" + busScheduleInfoMC.getDepartureLocation()+ "' , '" + busScheduleInfoMC.getArrivalLocation() + "' , '" + busScheduleInfoMC.getDepartureDate() + "' , '" + busScheduleInfoMC.getDepartureTime() + "' , '" + busScheduleInfoMC.getArrivalDate() + "' , ' " + busScheduleInfoMC.getArrivaleTime() + "')";
            ///try {
                db.execSQL(sql1);

            //} catch (Exception e) {
               // db.close();
            db.close();
                return true;
            //}


        }
        else
        {
            db.close();
            return false;
        }

        ///String sql1 = "insert into " + TableAttribute.BUS_SCHEDULE_TABLE + " (" + TableAttribute.COL_BUS_ID + " , " + TableAttribute.COL_BUS_NAME + " , " + TableAttribute.COL_DEPARTURE_DATE + " , " + TableAttribute.COL_DEPARTUR_TIME + " , " + TableAttribute.COL_ARRIVAL_DATE + " , " + TableAttribute.COL_ARRIVAL_TIME + ") values(" + busScheduleInfoMC.getBusID() + ",'" + busScheduleInfoMC.getBusName() + ",'" + busScheduleInfoMC.getDepartureDate() + ",'" + busScheduleInfoMC.getDepartureTime() + ",'" + busScheduleInfoMC.getArrivalDate() + ",'" + busScheduleInfoMC.getArrivaleTime() + "')";




        //return false;

    }


}
