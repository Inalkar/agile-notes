package com.inalkar.tools.agile.notes.util.dialog.event;

@FunctionalInterface
public interface IDialogTextEvent {
    void execute(final String text);
}
