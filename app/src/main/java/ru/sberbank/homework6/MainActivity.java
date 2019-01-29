package ru.sberbank.homework6;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.homework6.Items.Alarm;
import ru.sberbank.homework6.Items.BaseItem;
import ru.sberbank.homework6.Items.Call;
import ru.sberbank.homework6.Items.Sms;

public class MainActivity extends AppCompatActivity {
    static List<BaseItem> arrayList1;

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        arrayList1 = new ArrayList<>();
        arrayList1.add(new Call("FirstTesting"));
        arrayList1.add(new Sms("Oooooooooooooooops"));

        adapter = new CustomAdapter(arrayList1);
        adapter.setData(arrayList1);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                arrayList1.add(new Call("Alan"));
                arrayList1.add(new Call("Alex"));

                arrayList1.add(new Sms("It's works"));
                arrayList1.add(new Sms("Mama mia!"));

                arrayList1.add(new Alarm("12:40"));
                arrayList1.add(new Alarm("16:52"));

                adapter.setData(arrayList1);
            }
        }, 5000);   //5 seconds


    }


    //    public static String getRandomPhone() {
//        int num1, num2, num3;
//        int set2, set3;
//        Random generator = new Random();
//        num1 = generator.nextInt(1)+9;
//        num2 = generator.nextInt(2);
//        num3 = generator.nextInt(6)+2;
//        set2 = generator.nextInt(643) + 100;
//        set3 = generator.nextInt(8999) + 1000;
//        return  " +7 (" + num1 + "" + num2 + "" + num3 + ")" + "-" + set2 + "-" + set3 ;
//    }
//
//
//
//
//    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
//
//    final java.util.Random rand = new java.util.Random();
//
//    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
//    final Set<String> identifiers = new HashSet<String>();
//
//    public String randomIdentifier() {
//        StringBuilder builder = new StringBuilder();
//        while(builder.toString().length() == 0) {
//            int length = rand.nextInt(5)+5;
//            for(int i = 0; i < length; i++) {
//                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
//            }
//            if(identifiers.contains(builder.toString())) {
//                builder = new StringBuilder();
//            }
//        }
//        return builder.toString();
//    }

}
