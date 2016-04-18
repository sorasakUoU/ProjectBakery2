package kawinpart.sorasak.buybakery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by NekokanSama on 19/4/2559.
 */
public class MyManage {

    // Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String colum_id = "_id";
    public static final String colum_User = "User";
    public static final String colum_Password = "Password";
    public static final String colum_Name = "Name";
    public static final String colum_IDcard = "_IDcard";

    public static final String buy_table = "buyTABLE";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";
    //public static final String colum_ = "_id";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    } // Constructor

    //public  long addBuy(String str)





}
