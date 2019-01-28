package ru.sberbank.homework6;

import android.support.v7.widget.RecyclerView;

import ru.sberbank.homework6.Items.BaseItem;
import ru.sberbank.homework6.Items.Sms;


class SmsHolderBinder extends ViewHolderBinder {
    private final Sms mSms;


    public SmsHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        mSms = (Sms) item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.SmsViewHolder smsViewHolder = (CustomAdapter.SmsViewHolder) holder;
        smsViewHolder.textView.setText(mSms.getSmsText());

    }

    @Override
    public BaseItem getItem() {
        return mSms;
    }
}