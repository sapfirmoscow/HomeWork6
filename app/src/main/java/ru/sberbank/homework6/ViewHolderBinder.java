package ru.sberbank.homework6;

import android.support.v7.widget.RecyclerView;

import ru.sberbank.homework6.Items.BaseItem;

public abstract class ViewHolderBinder {
    protected final int viewType;

    public ViewHolderBinder(int viewType) {
        this.viewType = viewType;
    }

    public abstract void bindViewHolder(RecyclerView.ViewHolder holder);

    public abstract BaseItem getItem();
}
