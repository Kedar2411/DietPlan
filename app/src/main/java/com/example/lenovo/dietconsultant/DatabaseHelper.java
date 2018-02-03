package com.example.lenovo.dietconsultant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by lenovo on 25-01-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="register.db";
    private static final String TABLE_NAME="register";
    private static final String COL_Id="id";
    private static final String COL_Name="name";
    private static final String COL_Age="age";
    private static final String COL_Weight="weight";
    private static final String COL_Height="height";
    private static final String COL_Email="email";
    private static final String COL_Phone="phone";
    private static final String COL_Pass="pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE="create table register (id integer primary key not null , " +
    "name text not null , age text not null , weight text not null , height not null , email text not null , phone text not null , pass text not null)";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertdata(RegisterData rd)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from register";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put(COL_Id,count);
        values.put(COL_Name , rd.getName());
        values.put(COL_Age,rd.getAge());
        values.put(COL_Weight,rd.getWeight());
        values.put(COL_Height,rd.getHeight());
        values.put(COL_Email,rd.getEmail());
        values.put(COL_Phone,rd.getPhone());
        values.put(COL_Pass,rd.getPass());

        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public String searchPass(String email)
    {
        db=this.getReadableDatabase();
        //return email;

        String query = "select email , password from " + TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String a,b;
        b="not found";
        if (cursor.moveToFirst())
        {
            do {
                a=cursor.getString(0);
                if (a.equals(email))
                {
                    b=cursor.getString(1);
                    break;
                }

            }while (cursor.moveToNext());
        }
            return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
