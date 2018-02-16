package DataLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * Created by nilskjellbeck on 13.12.17.
 */

public class Item {

/* Attributes */
    private LinkedHashMap itemMap = new LinkedHashMap();


/* Constructor */
    public Item() {

    };

    /* Overloaded Constructor */
    public Item(String[] columns, String[] entries) {
        for(int i=0; i < columns.length; i++) {
            itemMap.put(columns[i], entries[i]);
        }
    };

    public Item(ArrayList<String> columns, ArrayList<String> entries) {
        for(int i=0; i < columns.size(); i++) {
            itemMap.put(columns.get(i), entries.get(i));
        }
    };

/* Getter */
    public LinkedHashMap getItemMap() {
        return itemMap;
    }

/* Setter */
    public void setItemMap(LinkedHashMap itemMap) {
        this.itemMap = itemMap;
    }

/* Methods */

    /* Adds Entry to the Hashmap itemMap */
    public void addEntryToHashMap(String columnName, String entry) {
        itemMap.put(columnName, entry);
    };

}
