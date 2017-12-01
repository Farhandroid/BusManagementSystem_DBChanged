package tanvir.busmanagementsystem.Database;

/**
 * Created by USER on 08-Oct-17.
 */

public class TableAttribute {

    public static final String DATABASE_NAME="DatabaseForBusManageMentSystem";
    public static final int DATABASE_VERSION=1;

    public static final String USER_TABLE="User";
    public static final String COL_USERNAME="UserName";
    public static final String COL_EMAIL="Email";
    public static final String COL_Password="Password";


    public static final String BUS_TABLE="BusInfo";
    public static final String COL_BUS_NAME="BusName";
    public static final String COL_BUS_ID="BusID";
    public static final String COL_BUS_TYPE="BusType";
    public static final String COL_TOTAL_BUS_SEAT="ToatalBusSeat";
    public static final String COL_BUS_SEAT_PRICE="BusSeatPrice";



    public static final String SEAT_TABLE="SeatInfo";
    public static final String COL_PK="PrimaryKey";
    public static final String COL_SEAT_NUMBER="SeatNumber";
    public static final String COL_CUSTOMER_NAME="CustomerName";
    public static final String COL_CUSTOMER_MOBILE_NUMBER="CustomerMobileName";
    public static final String COL_CUSTOMER_EMAIL="CustomerEmail";
    public static final String COL_SEAT_CANCELLABLE_TOKEN="SeatCancellableToken";


    public static final String BUS_SCHEDULE_TABLE="BusSchedule";
    public static final String COL_SCHEDULE_ID="SchedulePk";
    public static final String COL_DEPARTURE_LOCATION="DepartureLocation";
    public static final String COL_ARRIVAL_LOCATION="ArrivalLocation";
    public static final String COL_DEPARTURE_DATE="DepartureDate";
    public static final String COL_DEPARTUR_TIME="DepartureTime";
    public static final String COL_ARRIVAL_DATE="ArrivalDate";
    public static final String COL_ARRIVAL_TIME="ArrivalTime";



    public String userTableCreation()
    {
        String query = "CREATE TABLE IF NOT EXISTS "+ USER_TABLE +"( "+ COL_USERNAME +" TEXT PRIMARY KEY , " +COL_Password + " TEXT, "+COL_EMAIL +" TEXT ) ";

        return query;
    }


    public String busTableCreation()
    {
        String query = "CREATE TABLE IF NOT EXISTS "+ BUS_TABLE +" ( "+ COL_BUS_NAME+" TEXT NOT NULL , " +COL_BUS_ID + " TEXT NOT NULL,"+COL_BUS_TYPE + " TEXT  NOT NULL , "+COL_TOTAL_BUS_SEAT+" TEXT  NOT NULL , "+COL_BUS_SEAT_PRICE+" TEXT  NOT NULL , PRIMARY KEY ( "+COL_BUS_NAME+" , " +COL_BUS_ID +") )";


        return query;
    }

    public String busScheduleTableCreation()
    {
        ///String query = "CREATE TABLE IF NOT EXISTS "+ BUS_SCHEDULE_TABLE +" ( "+ COL_SCHEDULE_ID + " INTEGER  primary key autoincrement , "+COL_BUS_ID+" TEXT NOT NULL , " +COL_BUS_NAME + " TEXT NOT NULL,"+COL_DEPARTURE_DATE + " TEXT  NOT NULL , "+COL_DEPARTUR_TIME+" TEXT  NOT NULL , "+COL_ARRIVAL_DATE+" TEXT  NOT NULL , "+COL_DEPARTUR_TIME+ "TEXT  NOT NULL , FOREIGN KEY ( "+COL_BUS_ID+" , " +COL_BUS_NAME +") references "+BUS_TABLE+ " ( "+COL_BUS_ID+" , "+COL_BUS_NAME+ " ) )";
        ///String query = "CREATE TABLE IF NOT EXISTS "+ BUS_SCHEDULE_TABLE +" ( "+ COL_SCHEDULE_ID + " INTEGER primary key autoincrement  , "+COL_BUS_ID+" TEXT NOT NULL , " +COL_BUS_NAME + " TEXT NOT NULL,"+COL_DEPARTURE_DATE + " TEXT  NOT NULL , "+COL_DEPARTUR_TIME+" TEXT  NOT NULL , "+COL_ARRIVAL_DATE+" TEXT  NOT NULL , "+COL_DEPARTUR_TIME+ "TEXT  NOT NULL )";

        String query = "CREATE TABLE IF NOT EXISTS "+ BUS_SCHEDULE_TABLE +" ( "+ COL_SCHEDULE_ID + " INTEGER  primary key autoincrement , "+COL_BUS_ID+" TEXT NOT NULL , " +COL_BUS_NAME + " TEXT NOT NULL ,"+COL_DEPARTURE_LOCATION+" TEXT NOT NULL , "+COL_ARRIVAL_LOCATION+" TEXT NOT NULL , "+COL_DEPARTURE_DATE + " TEXT  NOT NULL , "+COL_DEPARTUR_TIME+" TEXT  NOT NULL , "+COL_ARRIVAL_DATE+" TEXT  NOT NULL , "+COL_ARRIVAL_TIME+ " TEXT  NOT NULL  )";
        return query;
    }

    public String seatTableCreation()
    {
        ///String query = "CREATE TABLE IF NOT EXISTS "+ SEAT_TABLE +"( "+ COL_PK+" INTEGER PRIMARY KEY AUTO INCREMENT , " +COL_BUS_NAME +" TEXT , " +COL_BUS_ID + " TEXT, FOREIGN KEY ( "+COL_BUS_NAME+" , " +COL_BUS_ID +") REFFERENCES "+BUS_TABLE + "( "+COL_BUS_NAME+" , " +COL_BUS_ID +"), "+COL_SEAT_NUMBER +"TEXT  NOT NULL , "+COL_CUSTOMER_NAME+"TEXT  NOT NULL , "+COL_CUSTOMER_MOBILE_NUMBER+"TEXT  NOT NULL , "+COL_CUSTOMER_EMAIL+"TEXT  NOT NULL , "+COL_SEAT_CANCELLABLE_TOKEN+"TEXT  NOT NULL  ) ";
        String query = "CREATE TABLE IF NOT EXISTS "+ SEAT_TABLE +"( "+ COL_PK +" INTEGER PRIMARY KEY AUTOINCREMENT , " +COL_BUS_NAME +" TEXT NOT NULL , " +COL_BUS_ID + " TEXT NOT NULL , "+COL_SEAT_NUMBER +" TEXT , "+COL_CUSTOMER_NAME+" TEXT   , "+COL_CUSTOMER_MOBILE_NUMBER+" TEXT , "+COL_CUSTOMER_EMAIL+" TEXT  , "+COL_SEAT_CANCELLABLE_TOKEN+" TEXT, FOREIGN KEY ( "+COL_BUS_NAME+" , " +COL_BUS_ID +") REFERENCES "+BUS_TABLE + "( "+COL_BUS_NAME+" , " +COL_BUS_ID +") )";
        return query;
    }




}
