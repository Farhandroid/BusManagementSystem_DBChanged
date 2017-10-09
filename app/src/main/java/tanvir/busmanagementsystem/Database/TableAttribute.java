package tanvir.busmanagementsystem.Database;

/**
 * Created by USER on 08-Oct-17.
 */

public class TableAttribute {

    public static final String DATABASE_NAME="DatabaseForBusManageMentSystem";
    public static final int DATABASE_VERSION=1;

    public static final String TABLE_NAME="User";
    public static final String COL_USERNAME="UserName";
    public static final String COL_EMAIL="Email";
    public static final String COL_Password="Password";


    public String userTableCreation()
    {
        String query = "CREATE TABLE "+ TABLE_NAME +"( "+ COL_USERNAME +" TEXT PRIMARY KEY , " +COL_Password + " TEXT, "+COL_EMAIL +" TEXT ) ";

        return query;
    }



}
