package client.websocket;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

class AppUtils {

    private AppUtils() {
    }

    public static void showSnackBar(View view, String stringToShow) {
        Snackbar.make(view, stringToShow, Snackbar.LENGTH_LONG)
                .setAction("OK", null).show();
    }

    public static void showLog(String tag, String messageToPrint) {
        if (tag != null && messageToPrint != null)
            Log.v(tag, messageToPrint);
    }
}