package com.carlosggz.contactsbook.view.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.view.listeners.OkAction;

import java.util.List;

public class MessagingUtils {

    public static void displayAlert(String title, String message, OkAction action, Context context) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog
                .setTitle(title)
                .setIcon(R.drawable.ic_error)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.OK), (dialogInterface, i) -> action.DoAction())
                .show();
    }

    public static void displayMessagesList(String title, String[] messages, OkAction action, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(title)
                .setIcon(R.drawable.ic_error)
                .setItems(messages, (dialog, which) -> {})
                .setPositiveButton(context.getString(R.string.OK), (dialogInterface, i) -> {})
                .show();
    }
}
