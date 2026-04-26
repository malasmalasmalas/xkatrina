package com.fufufu.katrina.backup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DebugActivity extends Activity {
    private String[] exceptionTypes = {"StringIndexOutOfBoundsException", "IndexOutOfBoundsException", "ArithmeticException", "NumberFormatException", "ActivityNotFoundException"};
    private String[] exceptionMessages = {"Invalid string operation\n", "Invalid list operation\n", "Invalid arithmetical operation\n", "Invalid toNumber block operation\n", "Invalid intent operation"};

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String str = "";
        if (intent != null) {
            String stringExtra = intent.getStringExtra("error");
            String[] strArrSplit = stringExtra.split("\n");
            int i = 0;
            while (true) {
                try {
                    String[] strArr = this.exceptionTypes;
                    if (i >= strArr.length) {
                        break;
                    }
                    if (strArrSplit[0].contains(strArr[i])) {
                        String str2 = this.exceptionMessages[i];
                        int iIndexOf = strArrSplit[0].indexOf(this.exceptionTypes[i]) + this.exceptionTypes[i].length();
                        StringBuilder sb = new StringBuilder(String.valueOf(str2));
                        String str3 = strArrSplit[0];
                        sb.append(str3.substring(iIndexOf, str3.length()));
                        str = String.valueOf(sb.toString()) + "\n\nDetailed error message:\n" + stringExtra;
                        break;
                    }
                    i++;
                } catch (Exception e) {
                    str = String.valueOf(str) + "\n\nError while getting error: " + Log.getStackTraceString(e);
                }
            }
            if (str.isEmpty()) {
                str = stringExtra;
            }
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setTitle("An error occurred").setMessage(str).setPositiveButton("End Application", new DialogInterface.OnClickListener() {             @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                DebugActivity.this.finish();
            }
        }).create();
        alertDialogCreate.show();
        ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setTextIsSelectable(true);
    }
}