package com.basic.bankingapp.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.basic.bankingapp.model.Users;
import com.basic.bankingapp.params.DatabaseDetails;
import com.basic.bankingapp.params.UserEntity;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    public UserDatabase(Context context) {
        super(context, DatabaseDetails.DATABASE_NAME, null, DatabaseDetails.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user = "CREATE TABLE " + DatabaseDetails.USERS_TABLE + "("
                + UserEntity.NAME+ " VERCHAR, "
                + UserEntity.PHONE+ " VARCHAR, "
                + UserEntity.BALANCE+ " INTEGER NOT NULL, "
                + UserEntity.EMAIL+ " VARCHAR, "
                + UserEntity.ACCOUNT_NUMBER+ " INTEGER PRIMARY KEY, "
                + UserEntity.IFSC_CODE+ " VARCHAR, "
                + UserEntity.ADDRESS+ " VARCHAR, "
                + UserEntity.DOB+ " VERCHAR );";

        db.execSQL(user);

        //Insert value in the table
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Ashish Gupta','1234567890',10000,'ashish@gmail.com',12345012,'ABCD','Patna','24/01/2000')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Ayush Gupta','2345678901',1100,'ayush@gmail.com',67891456,'EFGH','Indore','20/02/2001')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Abhishek Singh','3456789012',20145,'abhishek@gmail.com',11112789,'IJKL','Bhopal','8/01/2000')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Manoj Kr Gupta','4567890123',12045,'manoj@gmail.com',13141101,'MNOP','Kanpur','25/03/1998')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Anshu Kumari','5678901234',4000,'anshu@gmail.com',51617112,'QRST','Patna','24/01/2002')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Aryan Singh','6789012345',18000,'aryan1@gmail.com',18192131,'UVWX','Bhagalpur','15/02/2001')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Aryan Jain','7890123456',20452,'aryan12@gmail.com',12122415,'YZAB','Delhi','14/11/1997')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Manisha Singh','8901234567',4000,'manishasingh@gmail.com',23242161,'CDEF','Noida','05/01/1997')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Ananya Prashanna','9012345678',5000,'anaya@gmail.com',52627718,'GHIJ','Guna','10/02/2002')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Animesh Gautam','0123456789',5400,'animesh@gmail.com',28293192,'KLMN','Indore','21/12/1995')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Abhishek Gupta','1234567891',2000,'abhishekgupta@gmail.com',13132021,'OPQR','Bhagalpur','25/10/1995')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Vishal Roy','1234567892',4000,'vishal@gmail.com',33343222,'STUV','Goa','08/05/2000')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Deepak Saini','1234567893',2300,'deepak@gmail.com',53637324,'WXYZ','Balia','10/09/2002')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Gagan Tripathi','1234567894',8000,'gagan@gmail.com',38394252,'DCBA','Agra','20/04/1996')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Tanisha Kumari','1234567895',9000,'tanisha@gmail.com',14142627,'LKJH','Patna','10/01/2000')");
        db.execSQL("insert into " + DatabaseDetails.USERS_TABLE + " values('Akash Patel','1234567896',2000,'aksah@gmail.com',42444282,'ZYBP','Jhansi','14/02/2000')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseDetails.USERS_TABLE);
        onCreate(db);
    }

    public List<Users> getAllUsers(){
        List<Users> usersList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +DatabaseDetails.USERS_TABLE,null);

        if (cursor.moveToFirst()){
            do{
                Users users = new Users();
                users.setName(cursor.getString(0));
                users.setPhone(cursor.getString(1));
                users.setBalance(cursor.getInt(2));
                users.setEmail(cursor.getString(3));
                users.setAccountNumber(cursor.getInt(4));
                users.setIfsc_Code(cursor.getString(5));
                users.setAddress(cursor.getString(6));
                users.setDob(cursor.getString(7));
                usersList.add(users);
            }while (cursor.moveToNext());
        }
        return usersList;
    }

    public void updateUserAccountBalance(int accountNumber, int amount, Activity activity){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = ("UPDATE " + DatabaseDetails.USERS_TABLE + " SET " + UserEntity.BALANCE + " = " + amount + " WHERE " +
                UserEntity.ACCOUNT_NUMBER + " = " + accountNumber);
        db.execSQL(updateQuery);
    }

}
