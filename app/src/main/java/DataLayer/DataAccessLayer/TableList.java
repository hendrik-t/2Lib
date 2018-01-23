package DataLayer.DataAccessLayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import DataLayer.Item;

/**
 * Created by nilskjellbeck on 13.12.17.
 * Last edited by Hendrik Tete on 23.01.18
 */

public class TableList {

/* Attributes */
    private Context context;
    SQLiteDatabase db;

    private Item[] itemList;

    /* Constructor */
    public TableList(Context context) {
        this.context = context;
        this.db = new SQLiteHelper(context).getWritableDatabase();
    }

/* Getter */
    public Item[] getItemList() {
        return itemList;
    }

/* Setter */
    public void setItemList(Item[] itemList) {
        this.itemList = itemList;
    }

/* Methods */

    /* Creates a new table in the DB */
    public void createTable(String tableName, String[] columnNames) {
        // Prepares Statement
        String statement = "create table " + tableName + "(";
        for(int i = 0; i < columnNames.length; i++) {
            statement += "'" + columnNames[i] + "' string";
            if(i != columnNames.length-1) { statement += ", "; }
        }
        statement += ");";

        db.execSQL(statement);
        //db.execSQL("drop table " + tableName + ";"); // FOR EARLY TESTING, REMOVE IN FINAL
    }


    /* Opens and gets the data out of a DB */
    public TableList openTable(String tableName) {

    TableList a = new TableList(context);

    return a;
    }


    /* adds an Item to the DB */
    public void addItem(String tableName, Item item) {
        String statement = "insert into " + tableName + " (";
        for(int i = 0; i < item.getItemMap().size(); i++) {
            statement += "'" + item.getItemMap() + "'";
            if(i != item.getItemMap().size()-1) { statement += ", "; }
        }
    }


    /* deletes an Item from the DB */
    public void deleteItem(Item item) {

    }


    /* edits an Item in the DB */
    public void editItem(Item itemOld, Item itemNew) {

    }


    /* Returns a string array with the names of the existing tables */
    public ArrayList<String> getTableNames() {
        ArrayList<String> tableNames = new ArrayList<String>();

        Cursor cur = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if(cur.moveToFirst()) {
            while(!cur.isAfterLast()) {
                tableNames.add(cur.getString(cur.getColumnIndex("name")));
                cur.moveToNext();
            }
        }
        return tableNames;
    }


    /* Returns a String array with the columns of a specified table */
    public ArrayList<String> getColumnNames(String tableName) {
        Cursor dbCursor = db.query(tableName, null, null, null, null, null, null);
        return new ArrayList<>(Arrays.asList(dbCursor.getColumnNames()));
    }

}
