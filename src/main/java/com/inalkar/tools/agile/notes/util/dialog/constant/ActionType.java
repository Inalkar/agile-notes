package com.inalkar.tools.agile.notes.util.dialog.constant;

public enum ActionType {
    OK ("OK"),
    Confirm ("Confirm"),
    Continue ("Continue"),
    Cancel ("Cancel"),
    Close ("Close"),
    Save ("Save");

    private String value;

    ActionType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.value;
    }
}
