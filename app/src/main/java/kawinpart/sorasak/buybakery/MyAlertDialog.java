package kawinpart.sorasak.buybakery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by NekokanSama on 30/3/2559.
 */
public class MyAlertDialog {

    public void myDialog(Context context,
                         String strTitle,
                         String strMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.danger);  // เปลื่ยน Icon ตกใจตรงนี้
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    } // mydialog

} // Main Class
