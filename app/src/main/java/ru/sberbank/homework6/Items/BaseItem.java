package ru.sberbank.homework6.Items;

public interface BaseItem extends Cloneable {
    int getType();

    int getId();

    Object clone() throws CloneNotSupportedException;
}
