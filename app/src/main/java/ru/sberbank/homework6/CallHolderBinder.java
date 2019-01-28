package ru.sberbank.homework6;

import android.support.v7.widget.RecyclerView;

import ru.sberbank.homework6.Items.BaseItem;
import ru.sberbank.homework6.Items.Call;

public class CallHolderBinder extends ViewHolderBinder {

    private final Call mCall;

    public CallHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        mCall = (Call) item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.CallViewHolder callViewHolder = (CustomAdapter.CallViewHolder) holder;
        callViewHolder.textView.setText(mCall.getFirstName());
    }

    @Override
    public BaseItem getItem() {
        return mCall;
    }
}
