package ru.sberbank.homework6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import ru.sberbank.homework6.Items.Alarm;
import ru.sberbank.homework6.Items.BaseItem;
import ru.sberbank.homework6.Items.Call;
import ru.sberbank.homework6.Items.Sms;

public class DiffCall extends DiffUtil.Callback {

    private List<BaseItem> mOldList;
    private List<BaseItem> mNewList;

    public DiffCall(List<BaseItem> mOldList, List<BaseItem> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return mOldList.get(i).getId() == mNewList.get(i1).getId();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {

        return mOldList.get(i).equals(mNewList.get(i));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        BaseItem oldItem = mOldList.get(oldItemPosition);
        BaseItem newItem = mNewList.get(newItemPosition);

        if (oldItem instanceof Call && newItem instanceof Call)
            return getCallChangePayload((Call) oldItem, (Call) newItem);
        else if (oldItem instanceof Sms && newItem instanceof Sms)
            return getSmsChangePayload((Sms) oldItem, (Sms) newItem);
        else if (oldItem instanceof Alarm && newItem instanceof Alarm)
            return getAlarmChangePayload((Alarm) oldItem, (Alarm) newItem);
        else return null;

    }

    private Object getAlarmChangePayload(Alarm oldItem, Alarm newItem) {
        Bundle diff = new Bundle();

        if (!(oldItem.getTime().equals(newItem.getTime()))) {
            diff.putString("alarm_time", newItem.getTime());
        }

        if (diff.size() > 0) return diff;
        else return null;
    }

    private Object getSmsChangePayload(Sms oldItem, Sms newItem) {
        Bundle diff = new Bundle();

        if (!(oldItem.getSmsText().equals(newItem.getSmsText()))) {
            diff.putString("sms_text", newItem.getSmsText());
        }
        if (diff.size() > 0) return diff;
        else return null;
    }

    private Object getCallChangePayload(Call oldItem, Call newItem) {
        Bundle diff = new Bundle();

        if (!(oldItem.getFirstName().equals(newItem.getFirstName()))) {
            diff.putString("call_firstname", newItem.getFirstName());
        }
        if (diff.size() > 0) return diff;
        else return null;
    }
}
