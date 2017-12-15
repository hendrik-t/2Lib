package DataLayer.DataAccessLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import DataLayer.Item;

/**
 * Created by nilskjellbeck on 13.12.17.
 * Last edited by Hendrik Tete on 14.12.17
 */

public class TableList {

/* Attributes */
    private String tableName;
    private Item[] itemList;

    /* Constructor */
    public TableList() {

    };

/* Getter */
    public String getTableName() {
        return tableName;
    }

    public Item[] getItemList() {
        return itemList;
    }

/* Setter */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setItemList(Item[] itemList) {
        this.itemList = itemList;
    }

/* Methods */

    /* Creates a new table in the DB */
    public void createTable(Context context, String[] columnNames) {
        // Creates a database connection
        SQLiteDatabase db = new SQLiteHelper(context).getDatabaseWritable();

        // Prepares Statement
        String statement = "create table " + tableName + "(";
        for(int i = 0; i < columnNames.length; i++) {
            statement += "'" + columnNames[i] + "' string";
            if(i != columnNames.length-1) { statement += ", "; }
        }
        statement += ")";

        db.execSQL(statement);
    }

    /* Opens and gets the data out of a DB */
    public TableList openTable(String tableName) {

    TableList a = new TableList();

    return a;
    }

    /* adds an Item to the DB */
    public void addItem(Item item) {

    }

    /* deletes an Item from the DB */
    public void deleteItem(Item item) {

    }

    /* edits an Item in the DB */
    public void editItem(Item itemOld, Item itemNew) {

    }

}
