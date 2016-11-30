package com.medvid.andrii.diplomawork.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogUtils {

    public static Dialog createConfirmDialog(Context context,
                                             String title,
                                             String message,
                                             String confirmBtnText,
                                             DialogInterface.OnClickListener confirmBtnClickAction,
                                             boolean isCancelable) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(isCancelable);

        if(confirmBtnClickAction == null)   {
            builder.setPositiveButton(confirmBtnText, new  DialogInterface.OnClickListener()    {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }   else {
            builder.setPositiveButton(confirmBtnText, confirmBtnClickAction);
        }

        Dialog dialogVersion = builder.create();
        return dialogVersion;
    }

    public static Dialog createDialog(Context context,
                                      String title,
                                      String message,
                                      String confirmBtnText,
                                      String cancelBtnText,
                                      DialogInterface.OnClickListener confirmBtnClickAction,
                                      DialogInterface.OnClickListener cancelBtnClickAction,
                                      boolean isCancelable) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(isCancelable);

        DialogInterface.OnClickListener defaultClickAction = new  DialogInterface.OnClickListener()    {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        builder.setPositiveButton(confirmBtnText,
                confirmBtnClickAction == null ? defaultClickAction : confirmBtnClickAction);
        builder.setNegativeButton(cancelBtnText,
                confirmBtnClickAction == null ? defaultClickAction : cancelBtnClickAction);

        Dialog dialogVersion = builder.create();
        return dialogVersion;
    }
}
