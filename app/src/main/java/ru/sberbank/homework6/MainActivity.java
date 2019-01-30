package ru.sberbank.homework6;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.homework6.Items.BaseItem;

public class MainActivity extends AppCompatActivity {
    static List<BaseItem> arrayList1 = new ArrayList<>();

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Messenger mServiceMessenger;
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case MyService.MESSAGE_UPDATE:
                    update((List<BaseItem>) message.obj);
                    break;
                default:
                    super.handleMessage(message);

            }
        }
    });
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServiceMessenger = new Messenger(service);

            registerClient();
        }

        private void registerClient() {
            Message message = Message.obtain(null, MyService.MESSAGE_REGISTER_CLIENT);
            message.replyTo = mMessenger;
            try {
                mServiceMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceMessenger = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void update(List<BaseItem> list) {
        adapter.updateData(list);
    }


    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(new ArrayList<BaseItem>());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(MyService.newIntent(MainActivity.this), mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mServiceConnection);
    }
}
