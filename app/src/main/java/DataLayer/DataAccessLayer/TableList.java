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
    SQLiteDatabase db;

/* Constructor */
    public TableList(Context context) {
        this.db = new SQLiteHelper(context).getWritableDatabase();
    }

/* Methods */

    /* Creates a new table in the DB */
    public void createTable(String tableName, ArrayList<String> columnNames) {
        // Prepares Statement
        String statement = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
        for(int i = 0; i < columnNames.size(); i++) {
            statement += "'" + columnNames.get(i) + "' string";
            if(i != columnNames.size()-1) { statement += ", "; }
        }
        statement += ");";

        db.execSQL(statement);
    }


    /* Rename a table */
    public void renameTable(String oldName, String newName) {
        String statement = "ALTER TABLE " + oldName + " RENAME TO " + newName + ";";
        db.execSQL(statement);
    }


    /* Deletes a table in the DB */
    public void deleteTable(String tableName) {
        String statement = "DROP TABLE IF EXISTS " + tableName + ";";
        db.execSQL(statement);
    }


    /* Gets the data out of an existing table */
    public ArrayList<Item> open(String tableName) {
        ArrayList<String> columns = getColumnNames(tableName);
        ArrayList<Item> items = new ArrayList<Item>();

        Cursor cur = db.rawQuery("SELECT * FROM " + tableName, null);
        if (cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
                ArrayList<String> values = new ArrayList<String>();
                for (int i = 0; i < columns.size(); i++) {
                    values.add(cur.getString(cur.getColumnIndex(columns.get(i))));
                }
                Item item = new Item(columns, values);
                items.add(item);

                cur.moveToNext();
            }
        }

        return items;
    }


    /* Adds an Item to the DB-Table */
    public void addItem(String tableName, Item item) {
        ArrayList<String> columns = getColumnNames(tableName);

        /* fill in column names */
        String statement = "insert into " + tableName + " (";
        for(int i = 0; i < columns.size(); i++) {
            statement += "'" + columns.get(i) + "'";
            if(i != columns.size()-1) { statement += ", "; }
        }

        /* fill in corresponding values */
        statement += ") values (";
        for(int i = 0; i < columns.size(); i++) {
            statement += "'" + item.getItemMap().get(columns.get(i)).toString() + "'";
            if(i != columns.size()-1) { statement += ", "; }
        }
        statement += ");";

        db.execSQL(statement);
    }


    /* edits an Item in the DB */
    public void editItem(String tableName, int itemPos, Item itemNew) {
        ArrayList<String> columnNames = getColumnNames(tableName);

        /* prepare statement */
        String statement = "UPDATE " + tableName + " SET ";
        for(int i = 0; i < columnNames.size(); i++) {
            statement += "'" + columnNames.get(i) + "' = '" + itemNew.getItemMap().get(columnNames.get(i)).toString() + "'";
            if(i != columnNames.size()-1) { statement += ", "; }
        }
        statement += " WHERE rowid=";

        Cursor cur = db.rawQuery("SELECT rowid, * FROM " + tableName + ";", null);
        if (cur.moveToFirst()) {
            for (; itemPos > 0; itemPos--) { cur.moveToNext(); }
            int id = cur.getInt(0);
            statement += id + ";";
            db.execSQL(statement);
        }
    }


    /* deletes an Item from the DB */
    public void deleteItem(String tableName, int pos) {
        Cursor cur = db.rawQuery("SELECT rowid, * FROM " + tableName + ";", null);
        if (cur.moveToFirst()) {
            for (; pos > 0; pos--) { cur.moveToNext(); }
            int id = cur.getInt(0);
            db.execSQL("DELETE FROM " + tableName + " WHERE rowid=" + id + ";");
        }
    }


    /* Returns a string array with the names of the existing tables */
    public String[] getTableNames() {
        ArrayList<String> tableNames = new ArrayList<String>();

        Cursor cur = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata' order by name", null);
        if(cur.moveToFirst()) {
            while(!cur.isAfterLast()) {
                tableNames.add(cur.getString(cur.getColumnIndex("name")));
                cur.moveToNext();
            }
        }
        return tableNames.toArray(new String[0]);
    }


    /* Returns a String array with the columns of a specified table */
    public ArrayList<String> getColumnNames(String tableName) {
        Cursor dbCursor = db.query(tableName, null, null, null, null, null, null);
        return new ArrayList<>(Arrays.asList(dbCursor.getColumnNames()));
    }

    public String toString(String tableName, TableList tab) {
        String result = "";
        ArrayList<Item> list= new ArrayList<>();
        list = tab.open(tableName);

        for(int i = 0; i < list.size(); i++) {
            tab.getColumnNames(tableName).get(0);
            result += list.get(i).getItemMap().get(tab.getColumnNames(tableName).get(0))+"\n\n";
        }

        return tableName +"\n\n"+result;
    }
}
