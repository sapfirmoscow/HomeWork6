package ru.sberbank.homework6;

import android.support.v7.widget.RecyclerView;

import ru.sberbank.homework6.Items.Alarm;
import ru.sberbank.homework6.Items.BaseItem;

public class AlarmHolderBinder extends ViewHolderBinder {

    Alarm mAlarm;

    public AlarmHolderBinder(BaseItem alarm, int viewType) {
        super(viewType);
        mAlarm = (Alarm) alarm;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.AlarmViewHolder alarmViewHolder = (CustomAdapter.AlarmViewHolder) holder;
        alarmViewHolder.textView.setText(mAlarm.getTime());

    }

    @Override
    public BaseItem getItem() {
        return mAlarm;
    }
}
