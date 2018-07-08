package client.websocket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }
}