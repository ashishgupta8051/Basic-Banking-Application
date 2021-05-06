package com.basic.bankingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.basic.bankingapp.model.Transaction;
import com.basic.bankingapp.model.Users;
import com.basic.bankingapp.params.DatabaseDetails;
import com.basic.bankingapp.params.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionDatabase extends SQLiteOpenHelper {

    public TransactionDatabase(Context context) {
        super(context, DatabaseDetails.TRANSACTION_TABLE, null, DatabaseDetails.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String transaction = "CREATE TABLE " + DatabaseDetails.TRANSACTION_TABLE + "(" +
                TransactionEntity.FROM_NAME+ " VERCHAR, " +
                TransactionEntity.TO_NAME+ " VERCHAR, " +
                TransactionEntity.AMOUNT+ " VERCHAR, " +
                TransactionEntity.STATUS+ " VERCHAR, " +
                TransactionEntity.DATE+ " VERCHAR );";

        db.execSQL(transaction);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseDetails.TRANSACTION_TABLE);
        onCreate(db);
    }

    public boolean insertTransferData (String fromName, String toName, String amount, String status, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TransactionEntity.FROM_NAME, fromName);
        contentValues.put(TransactionEntity.TO_NAME, toName);
        contentValues.put(TransactionEntity.AMOUNT, amount);
        contentValues.put(TransactionEntity.STATUS, status);
        contentValues.put(TransactionEntity.DATE, time);
        Long result = db.insert(DatabaseDetails.TRANSACTION_TABLE, null, contentValues);
//        Log.d("Insert","From Name "+fromName+" To Name "+toName+" Amount "+amount+" Status "+status+ " Date "+time);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Transaction> getAllTransactions(){
        List<Transaction> transactionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " +DatabaseDetails.TRANSACTION_TABLE,null);

        if (cursor.moveToFirst()){
            do{
                Transaction transaction = new Transaction();
                transaction.setFromName(cursor.getString(0));
                transaction.setToName(cursor.getString(1));
                transaction.setAmount(cursor.getString(2));
                transaction.setStatus(cursor.getString(3));
                transaction.setTransactionDate(cursor.getString(4));
                transactionList.add(transaction);
            }while (cursor.moveToNext());
        }
        return transactionList;
    }
}
