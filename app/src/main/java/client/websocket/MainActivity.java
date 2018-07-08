package client.websocket;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ArrayList<WebSocketMessage> webSocketMessageArrayList = new ArrayList<>();

        MessagesRecyclerViewAdapter messagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter(MainActivity.this, webSocketMessageArrayList);
        recyclerView.setAdapter(messagesRecyclerViewAdapter);

        EditText editTextMessageToSend = findViewById(R.id.editTextMessageToSend);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (editTextMessageToSend.getText().toString().isEmpty()) {
                AppUtils.showSnackBar(fab, getString(R.string.PleaseEnterAMessageToSend));
            } else {
                if (AppUtils.isConnectedToInternet(this)) {
                    webSocketMessageArrayList.add(new WebSocketMessage(editTextMessageToSend.getText().toString(), new Date()));
                    messagesRecyclerViewAdapter.notifyItemInserted(webSocketMessageArrayList.size());
                    //connectToWsServer
                    AppSingleton.getInstance().sendMessageToWebSocketServer(fab, editTextMessageToSend.getText().toString());
                } else
                    AppUtils.showSnackBar(fab, getString(R.string.PleaseConnectThisDeviceToInternet));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //connectToWebServer
        AppSingleton.getInstance().connectToWebSocketServer(fab);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppSingleton.getInstance().disconnectFromWebSocketServer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}