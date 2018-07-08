package client.websocket;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.WebSocketMessageViewHolder> {
    private final AppCompatActivity activity;
    private final ArrayList<WebSocketMessage> webSocketMessageArrayList;

    public MessagesRecyclerViewAdapter(AppCompatActivity activity, ArrayList<WebSocketMessage> webSocketMessageArrayList) {
        this.activity = activity;
        this.webSocketMessageArrayList = webSocketMessageArrayList;
    }

    @NonNull
    @Override
    public WebSocketMessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item_recycler_view, viewGroup, false);
        return new WebSocketMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebSocketMessageViewHolder webSocketMessageViewHolder, int position) {
        webSocketMessageViewHolder.messageTextView.setText(webSocketMessageArrayList.get(position).getMessageValue());
        webSocketMessageViewHolder.timeTextView.setText(webSocketMessageArrayList.get(position).getMessageTime().toString());
    }

    @Override
    public int getItemCount() {
        if (webSocketMessageArrayList != null)
            return webSocketMessageArrayList.size();
        return 0;
    }

    class WebSocketMessageViewHolder extends RecyclerView.ViewHolder {

        public WebSocketMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
        }

        private TextView messageTextView;
        private TextView timeTextView;
    }
}