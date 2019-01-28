package ru.sberbank.homework6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    }

    private void initFactory() {
        mFactoryMap = new SparseArray<>();
        mFactoryMap.put(ItemTypes.CALL.type, new CallViewHolderFactory());
        mFactoryMap.put(ItemTypes.SMS.type, new SmsViewHolderFactory());
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
    public int getItemViewType(int position) {
//       if(mData.get(position).getType()==ItemTypes.CALL.type) return ItemTypes.CALL.type;
//       else if(mData.get(position).getType()== ItemTypes.SMS) return ItemTypes.SMS.type;

        return mData.get(position).getType();
    }

    public void setData(List<BaseItem> items) {
        mBinders.clear();
        for (BaseItem item : items) {
            mBinders.add(generateBinder(item));
        }
        notifyDataSetChanged();
    }

    private ViewHolderBinder generateBinder(BaseItem item) {
        if (item.getType() == ItemTypes.CALL.type) {
            return new CallHolderBinder(item, ItemTypes.CALL.type);
        } else if (item.getType() == ItemTypes.SMS.type)
            return new SmsHolderBinder(item, ItemTypes.SMS.type);

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
            textView = itemView.findViewById(R.id.textView4);
        }
    }

    public static class SmsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public SmsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView7);
        }
    }
}
