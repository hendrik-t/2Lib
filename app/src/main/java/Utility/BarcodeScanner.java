package Utility;

import DataLayer.Item;

/**
 * Created by nilskjellbeck on 13.12.17.
 */

public class BarcodeScanner {

/* attributes */
    private String barcode;
    private Item item;

/* Constructor */
    public BarcodeScanner() {

    };

/* Getter */
    public String getBarcode() {
        return barcode;
    }

    public Item getItem() {
        return this.item;
    }

/* Setter */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setItem(Item item) {
        this.item = item;
    }

/* Methods */

    /* Opens mobile phones camera-preview */
    public void openCamera() {

    };

    /* Scans the barcode in the middle of the camera-preview */
    public void scanWithCamera() {

    };


    /* Checks an API-Lookup and gets back the product information */
    public void lookUpResults() {

    };

    /* Allows user to enter a code manually */
    public String manualInput() {
        String eanInput = "";
        return eanInput;
    };

    /* closes mobile phones camera-preview */
    public void closeCamera() {

    };


}
