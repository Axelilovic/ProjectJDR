package jdr.projet.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import jdr.projet.myapplication.DataBase.DbContract.*;
import jdr.projet.myapplication.Classes.Game;

import java.util.ArrayList;

/**
 * Created by Aubry on 13/02/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Game.db";
    private static final Integer DATABASE_VERSION = 1;

    //Constructor
    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ");
        sb.append(GameEntries.TABLE_NAME);   //voir imports
        sb.append(" (");
        sb.append(GameEntries._ID);
        sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(GameEntries.NAME);
        sb.append(" TEXT NOT NULL,");
        sb.append(GameEntries.EDITION);
        sb.append(" TEXT,");
        sb.append(GameEntries.EXTENSION);
        sb.append(" TEXT");
        sb.append(");");

        db.execSQL(sb.toString());

        Game entree1 = new Game("Vampire","20 Anniversary","Vanilla");

        addGame(db,entree1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ GameEntries.TABLE_NAME);
        onCreate(db);
    }


    public ArrayList<Game> getAllGames(SQLiteDatabase db){
        Cursor cursor;

        String query = "SELECT * FROM "+GameEntries.TABLE_NAME+" ORDER BY "+GameEntries._ID+" ASC";
        cursor = db.rawQuery(query,null);

        ArrayList<Game> result = new ArrayList<Game>();
        while(cursor.moveToNext()){
            Game current = new Game(
                    cursor.getInt(cursor.getColumnIndex(GameEntries._ID)),
                    cursor.getString(cursor.getColumnIndex(GameEntries.NAME)),
                    cursor.getString(cursor.getColumnIndex(GameEntries.EDITION)),
                    cursor.getString(cursor.getColumnIndex(GameEntries.EXTENSION))
            );
            result.add(current);
        }

        return result;
    }

    public Cursor getCursorGame(SQLiteDatabase db){
        String query = "SELECT * FROM "+GameEntries.TABLE_NAME+" ORDER BY "+GameEntries._ID+" ASC";
        return db.rawQuery(query,null);
    }

    /*
    public void addGame(SQLiteDatabase db, Game entree1){

            ContentValues cv = new ContentValues();
            cv.put(DbContract.GameEntries.NAME, entree1.getName());
            cv.put(DbContract.GameEntries.EDITION, entree1.getEdition());
            cv.put(DbContract.GameEntries.EXTENSION, entree1.getExtension());

            db.insert(DbContract.GameEntries.TABLE_NAME,null,cv);
    }

    */

    public void addGame(SQLiteDatabase db, Game game){
        ContentValues cv = new ContentValues();
        cv.put(DbContract.GameEntries.NAME, game.getName());
        cv.put(DbContract.GameEntries.EDITION, game.getEdition());
        cv.put(DbContract.GameEntries.EXTENSION, game.getExtension());

        db.insert(DbContract.GameEntries.TABLE_NAME,null,cv);
    }

    public Game getGameById(int id, SQLiteDatabase db){
        String query = "SELECT * FROM " + GameEntries.TABLE_NAME + " WHERE "+GameEntries._ID + " = "+ id;
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToNext()) return null;
        return new Game(
                cursor.getInt(cursor.getColumnIndex(GameEntries._ID)),
                cursor.getString(cursor.getColumnIndex(GameEntries.NAME)),
                cursor.getString(cursor.getColumnIndex(GameEntries.EDITION)),
                cursor.getString(cursor.getColumnIndex(GameEntries.EXTENSION))
        );
    }
}
