package tanvir.busmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.BustListAV;
import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;
import tanvir.busmanagementsystem.MOdelClass.BusScheduleInfoMC;
import tanvir.busmanagementsystem.MOdelClass.SeatInfoMC;

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

        String selectString = "SELECT * FROM " + TableAttribute.BUS_TABLE + " WHERE " + TableAttribute.COL_BUS_ID + " = ?  AND " + TableAttribute.COL_BUS_NAME + " = ?";

        Cursor cursor = db.rawQuery(selectString, new String[]{busScheduleInfoMC.getBusID(), busScheduleInfoMC.getBusName()});

        ///long result = db.insert(TableAttribute.BUS_SCHEDULE_TABLE,null,contentValues);

        if (cursor.getCount() > 0) {
            String sql1 = "insert into " + TableAttribute.BUS_SCHEDULE_TABLE + " ( " + TableAttribute.COL_BUS_ID + " , " + TableAttribute.COL_BUS_NAME + " , " + TableAttribute.COL_DEPARTURE_LOCATION + " , " + TableAttribute.COL_ARRIVAL_LOCATION + " , " + TableAttribute.COL_DEPARTURE_DATE + " , " + TableAttribute.COL_DEPARTUR_TIME + " , " + TableAttribute.COL_ARRIVAL_DATE + " , " + TableAttribute.COL_ARRIVAL_TIME + " ) values( '" + busScheduleInfoMC.getBusID() + "' , '" + busScheduleInfoMC.getBusName() + "' , '" + busScheduleInfoMC.getDepartureLocation() + "' , '" + busScheduleInfoMC.getArrivalLocation() + "' , '" + busScheduleInfoMC.getDepartureDate() + "' , '" + busScheduleInfoMC.getDepartureTime() + "' , '" + busScheduleInfoMC.getArrivalDate() + "' , ' " + busScheduleInfoMC.getArrivaleTime() + "')";
            ///try {
            db.execSQL(sql1);

            //} catch (Exception e) {
            // db.close();
            db.close();
            return true;
            //}


        } else {
            db.close();
            return false;
        }

        ///String sql1 = "insert into " + TableAttribute.BUS_SCHEDULE_TABLE + " (" + TableAttribute.COL_BUS_ID + " , " + TableAttribute.COL_BUS_NAME + " , " + TableAttribute.COL_DEPARTURE_DATE + " , " + TableAttribute.COL_DEPARTUR_TIME + " , " + TableAttribute.COL_ARRIVAL_DATE + " , " + TableAttribute.COL_ARRIVAL_TIME + ") values(" + busScheduleInfoMC.getBusID() + ",'" + busScheduleInfoMC.getBusName() + ",'" + busScheduleInfoMC.getDepartureDate() + ",'" + busScheduleInfoMC.getDepartureTime() + ",'" + busScheduleInfoMC.getArrivalDate() + ",'" + busScheduleInfoMC.getArrivaleTime() + "')";


        //return false;

    }

    public ArrayList<BusScheduleInfoMC> getDataFromBusScheduleForRV(String DepartueLocation, String ArrivalLocation, String DepartureDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectString = "SELECT * FROM " + TableAttribute.BUS_SCHEDULE_TABLE + " WHERE " + TableAttribute.COL_DEPARTURE_LOCATION + " = ?  AND " + TableAttribute.COL_ARRIVAL_LOCATION + " = ? AND " + TableAttribute.COL_DEPARTURE_DATE + " = ?";

        Cursor cursor = db.rawQuery(selectString, new String[]{DepartueLocation, ArrivalLocation, DepartureDate});


        ArrayList<BusScheduleInfoMC> busScheduleInfoMCS = new ArrayList<>();


        if (cursor.getCount() > 0) {
            ///Toast.makeText(context, "data found BusScheduleInfoMC DB", Toast.LENGTH_SHORT).show();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {


                BusScheduleInfoMC busScheduleInfoMC = new BusScheduleInfoMC();

                busScheduleInfoMC.setBusScheduleInfoPK(cursor.getInt(cursor.getColumnIndex(TableAttribute.COL_SCHEDULE_ID)));
                busScheduleInfoMC.setBusName(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_NAME)));
                busScheduleInfoMC.setBusID(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_ID)));
                busScheduleInfoMC.setDepartureTime(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_DEPARTUR_TIME)));
                busScheduleInfoMC.setArrivaleTime(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_ARRIVAL_TIME)));
                busScheduleInfoMC.setDepartureLocation(DepartueLocation);
                busScheduleInfoMC.setArrivalLocation(ArrivalLocation);

                busScheduleInfoMCS.add(busScheduleInfoMC);

                cursor.moveToNext();
            }


        }


        //Toast.makeText(context, "DB : "+busScheduleInfoMCS.get(0).getBusID(), Toast.LENGTH_SHORT).show();
        return busScheduleInfoMCS;


    }

    public BusINfoMC getbuaTypeBusSeatSeatPriceFromDB(String busId, String busName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + TableAttribute.COL_BUS_TYPE + " , " + TableAttribute.COL_TOTAL_BUS_SEAT + " , " + TableAttribute.COL_BUS_SEAT_PRICE + " FROM " + TableAttribute.BUS_TABLE + " WHERE " + TableAttribute.COL_BUS_ID + " = ?  AND " + TableAttribute.COL_BUS_NAME + " = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{busId, busName});
        BusINfoMC busINfoMC = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String busType = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_TYPE));
            String totalBusSeat = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_TOTAL_BUS_SEAT));
            String busSeatPrice = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_SEAT_PRICE));

            busINfoMC = new BusINfoMC(busType, totalBusSeat, busSeatPrice);
        }
        return busINfoMC;

    }

    public boolean addSeatInDatabase(ArrayList<SeatInfoMC> seatInfoMCS) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean res = true;
        for (int i = 0; i < seatInfoMCS.size(); i++) {
           /// Toast.makeText(context, "Seat info size in db : " + Integer.toString(seatInfoMCS.size()), Toast.LENGTH_SHORT).show();
            String sql1 = "insert into " + TableAttribute.SEAT_TABLE + " ( " + TableAttribute.COL_SCHEDULE_ID + " , " + TableAttribute.COL_SEAT_NUMBER + " , " + TableAttribute.COL_CUSTOMER_NAME + " , " + TableAttribute.COL_CUSTOMER_MOBILE_NUMBER + " , " + TableAttribute.COL_CUSTOMER_EMAIL + " , " + TableAttribute.COL_SEAT_CANCELLABLE_TOKEN + " ) values( '" + seatInfoMCS.get(i).getSchedule_id() + "' , '" + seatInfoMCS.get(i).getSeatNumber() + "' , '" + seatInfoMCS.get(i).getCustomerName() + "' , '" + seatInfoMCS.get(i).getCustomerMobileNumber() + "' , '" + seatInfoMCS.get(i).getCustomerEmail() + "' , '" + seatInfoMCS.get(i).getSeatCancellableToken() + "')";


            try {
                db.execSQL(sql1);

            } catch (Exception e) {
                res = false;
            }

        }
        return res;
    }

    public ArrayList<SeatInfoMC> getSeatUsingScheduleId(int scheduleId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TableAttribute.SEAT_TABLE + " WHERE " + TableAttribute.COL_SCHEDULE_ID + " = ?  ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(scheduleId)});

        ArrayList<SeatInfoMC> seatInfoMCS = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Integer scheduleid = cursor.getInt(cursor.getColumnIndex(TableAttribute.COL_SCHEDULE_ID));
                String seatNumber = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_SEAT_NUMBER));
                String customerName = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_CUSTOMER_NAME));

                String customerMobileNumber = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_CUSTOMER_MOBILE_NUMBER));
                String customerEmail = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_CUSTOMER_EMAIL));
                String seatCancelableToken = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_SEAT_CANCELLABLE_TOKEN));

                SeatInfoMC seatInfoMC = new SeatInfoMC(scheduleid, seatNumber, customerName, customerMobileNumber, customerEmail, seatCancelableToken);

                seatInfoMCS.add(seatInfoMC);

                cursor.moveToNext();
            }


        }

        return seatInfoMCS;
    }

    public boolean checkIfBusSeatAvailable(int scheduleId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TableAttribute.SEAT_TABLE + " WHERE " + TableAttribute.COL_SCHEDULE_ID + " = ?  ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(scheduleId)});

        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }


    public boolean deleTeBusSchedule(int scheduleId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean res = true;
        ///int result = db.delete(TableAttribute.BUS_SCHEDULE_TABLE, TableAttribute.COL_SCHEDULE_ID, scheduleId);
        ///db.close();


        String sql1 = "Delete from " + TableAttribute.BUS_SCHEDULE_TABLE  + " WHERE " + TableAttribute.COL_SCHEDULE_ID + " = "+scheduleId;


        try {
            db.execSQL(sql1);
            db.close();

        } catch (Exception e) {

            res = false;
        }

        return res;

    }

    public boolean cancelTicket(String token)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean res = true;


        String query = "SELECT * FROM " + TableAttribute.SEAT_TABLE + " WHERE " + TableAttribute.COL_SEAT_CANCELLABLE_TOKEN + " = ?  ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(token)});

        if (cursor.getCount()>0)
        {
            db.execSQL("DELETE FROM " + TableAttribute.SEAT_TABLE+ " WHERE "+TableAttribute.COL_SEAT_CANCELLABLE_TOKEN+"='"+token.trim()+"'");
            db.close();
        }
        else
            res=false;


        return res;


    }

    public ArrayList<String> getArrivalTimeFromcheduleTable(String busId , String busName)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ TableAttribute.COL_ARRIVAL_TIME + " FROM " + TableAttribute.BUS_SCHEDULE_TABLE+ " WHERE " + TableAttribute.COL_BUS_ID + " = ?  AND " + TableAttribute.COL_BUS_NAME + " = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{busId, busName});
        ArrayList<String> arrivalInfo = new ArrayList<>();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String arrival = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_ARRIVAL_TIME));

            arrivalInfo.add(arrival);
        }
        return arrivalInfo;
    }


}
