package ru.sberbank.homework6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.homework6.Items.BaseItem;

public class CustomAdapter extends RecyclerView.Adapter {

    private final List<ViewHolderBinder> mBinders;
    private SparseArray<ViewHolderFactory> mFactoryMap;
    private List<BaseItem> mData;

    public CustomAdapter(List<BaseItem> data) {
        mData = data;
        mBinders = new ArrayList<>();
        initFactory();
        setData(mData);
    }

    private void initFactory() {
        mFactoryMap = new SparseArray<>();
        mFactoryMap.put(ItemTypes.CALL.type, new CallViewHolderFactory());
        mFactoryMap.put(ItemTypes.SMS.type, new SmsViewHolderFactory());
        mFactoryMap.put(ItemTypes.ALARM.type, new AlarmViewHolderFactory());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolderFactory factory = mFactoryMap.get(i);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return factory.createViewHolder(viewGroup, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderBinder binder = mBinders.get(i);
        if (binder != null) {
            binder.bindViewHolder(viewHolder);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        Log.d("TESTING", "bind, position = " + position);
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle diff = (Bundle) payloads.get(0);
            for (String key : diff.keySet()) {
                switch (key) {
                    case "call_firstname":
                        ((CallViewHolder) holder).textView.setText(diff.getString("call_firstname"));
                        break;
                    case "sms_text":
                        ((SmsViewHolder) holder).textView.setText(diff.getString("sms_text"));
                        break;
                    case "alarm_time":
                        ((AlarmViewHolder) holder).textView.setText(diff.getString("alarm_time"));
                        break;
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    private void setData(List<BaseItem> items) {
        mBinders.clear();
        for (BaseItem item : items) {
            mBinders.add(generateBinder(item));
        }
        notifyDataSetChanged();
    }


    public void updateData(List<BaseItem> newItems) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCall(mData, newItems));
        diffResult.dispatchUpdatesTo(this);

        mData.clear();
        mData.addAll(newItems);

        mBinders.clear();
        for (BaseItem item : mData) {
            mBinders.add(generateBinder(item));
        }

    }


    private ViewHolderBinder generateBinder(BaseItem item) {
        if (item.getType() == ItemTypes.CALL.type) {
            return new CallHolderBinder(item, ItemTypes.CALL.type);
        } else if (item.getType() == ItemTypes.SMS.type)
            return new SmsHolderBinder(item, ItemTypes.SMS.type);
        else if (item.getType() == ItemTypes.ALARM.type)
            return new AlarmHolderBinder(item, ItemTypes.ALARM.type);

        return null;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class CallViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView3);
        }
    }

    public static class SmsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public SmsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView7);
        }
    }

    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView5);
        }
    }
}
