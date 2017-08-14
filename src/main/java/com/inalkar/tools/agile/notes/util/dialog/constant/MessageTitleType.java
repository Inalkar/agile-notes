package com.inalkar.tools.agile.notes.util.dialog.constant;

public enum MessageTitleType {
    Info ("Info"),
    Error ("Error"),
    Warning ("Warning"),
    Confirm ("Please confirm the action");

    private String value;

    MessageTitleType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return this.value;
    }
}
