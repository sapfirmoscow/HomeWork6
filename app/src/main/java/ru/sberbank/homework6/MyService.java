package ru.sberbank.homework6;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.sberbank.homework6.Items.Alarm;
import ru.sberbank.homework6.Items.BaseItem;
import ru.sberbank.homework6.Items.Call;
import ru.sberbank.homework6.Items.Sms;

public class MyService extends Service {


    public static final int MESSAGE_REGISTER_CLIENT = 0;
    public static final int MESSAGE_UNREGISTER_CLIENT = 1;
    public static final int MESSAGE_UPDATE = 2;

    private static final String TAG = "CustomService";
    private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private List<Messenger> mClients = new ArrayList<>();
    private List<BaseItem> mItems = new ArrayList<>();
    private Thread mThread = new Thread() {
        @Override
        public void run() {
            Random random = new Random();

            while (!isInterrupted()) {
                switch (random.nextInt(4)) {
                    case 0:
                        mItems.add(new Alarm(getRandomTime()));
                        break;
                    case 1:
                        mItems.add(new Call(getRandomPhone()));
                        mItems.add(new Call(getRandomPhone()));
                        mItems.add(new Call(getRandomPhone()));
                        break;
                    case 2:
                        mItems.add(new Sms(getRandomText(random.nextInt(80))));
                        break;
                    case 3:
                        for (int i = 0; i < 3; i++) {
                            if (mItems.size() > 0)
                                mItems.remove(random.nextInt(mItems.size()));
                        }
                        break;
                }

                sendToClients(Message.obtain(null, MESSAGE_UPDATE, 0, 0, mItems));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case MESSAGE_REGISTER_CLIENT:
                    mClients.add(message.replyTo);
                    break;
                case MESSAGE_UNREGISTER_CLIENT:
                    mClients.remove(message.replyTo);
                    break;
                default:
                    super.handleMessage(message);
            }
        }
    });

    public static Intent newIntent(Context context) {
        return new Intent(context, MyService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mThread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThread.interrupt();
    }

    private void sendToClients(Message message) {
        for (Messenger messenger : mClients) {
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private String getRandomPhone() {
        int num1, num2, num3;
        int set2, set3;
        Random generator = new Random();
        num1 = generator.nextInt(1) + 9;
        num2 = generator.nextInt(2);
        num3 = generator.nextInt(6) + 2;
        set2 = generator.nextInt(643) + 100;
        set3 = generator.nextInt(8999) + 1000;
        return " +7 (" + num1 + "" + num2 + "" + num3 + ")" + "-" + set2 + "-" + set3;
    }

    private String getRandomTime() {
        Random generator = new Random();
        return generator.nextInt(13) + ":" + generator.nextInt(61);
    }

    private String getRandomText(int len) {

        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();

    }


}
