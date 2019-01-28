package ru.sberbank.homework6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class SmsViewHolderFactory implements ViewHolderFactory {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View itemView = inflater.inflate(R.layout.incoming_sms, parent, false);
        RecyclerView.ViewHolder holder = new CustomAdapter.SmsViewHolder(itemView);
        return holder;
    }
}
