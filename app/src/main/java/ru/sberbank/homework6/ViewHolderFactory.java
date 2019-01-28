package ru.sberbank.homework6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface ViewHolderFactory {

    /**
     * Метод создания конкретного ViewHolder
     *
     * @param parent   родительская вью
     * @param inflater {@link LayoutInflater} для получения объекта вьхи из xml
     * @return готовый объект класса {@link RecyclerView.ViewHolder}
     */

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater);
}
