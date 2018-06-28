package client.websocket;

import android.view.View;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.crossbar.autobahn.websocket.WebSocketConnection;
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler;
import io.crossbar.autobahn.websocket.exceptions.WebSocketException;
import io.crossbar.autobahn.websocket.types.ConnectionResponse;

class AppSingleton {

    private static AppSingleton appSingletonInstance;
    private final WebSocketConnection webSocketConnection = new WebSocketConnection();
    private static final String WEB_SOCKET_URI = "ws://demos.kaazing.com/echo";
    private final String tag = this.getClass().getSimpleName();

    private AppSingleton() {
    }

    public static AppSingleton getInstance() {
        if (appSingletonInstance == null)
            appSingletonInstance = new AppSingleton();

        return appSingletonInstance;
    }

    public void connectToWebSocketServer(final View view) {
        try {
            if (!webSocketConnection.isConnected()) {
                webSocketConnection.connect(WEB_SOCKET_URI, new WebSocketConnectionHandler() {
                    @Override
                    public void onConnect(ConnectionResponse response) {
                        super.onConnect(response);
                        AppUtils.showLog(tag, "onConnect response:" + response);
                        AppUtils.showSnackBar(view, "onConnect");
                    }

                    @Override
                    public void onClose(int code, String reason) {
                        super.onClose(code, reason);
                        AppUtils.showLog(tag, "onClose code:" + code + " reason:" + reason);
                    }

                    @Override
                    public void onMessage(String payload) {
                        super.onMessage(payload);
                        AppUtils.showLog(tag, "onMessage payload:" + payload);
                        AppUtils.showSnackBar(view, "onMessage payload:" + payload);
                    }
                });
            } else {
                AppUtils.showSnackBar(view, "already connected");
                AppUtils.showLog(tag, "already connected");
            }
        } catch (WebSocketException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    public void disconnectFromWebSocketServer() {
        if (webSocketConnection.isConnected()) {
            webSocketConnection.sendClose();
            AppUtils.showLog(tag, "sendClose");
        }
        AppUtils.showLog(tag, "already NOT connected");
    }

    public void sendMessageToWebSocketServer(View view, String messageToSend) {
        AppUtils.showLog(tag, "sendMessageToServer messageToSend:" + messageToSend);
        if (!webSocketConnection.isConnected()) {
            connectToWebSocketServer(view);
        }
        webSocketConnection.sendMessage(messageToSend);

    }
}