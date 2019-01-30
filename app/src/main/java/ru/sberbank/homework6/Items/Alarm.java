package ru.sberbank.homework6.Items;

import ru.sberbank.homework6.ItemTypes;
import ru.sberbank.homework6.UUIDGenerator;

public class Alarm implements BaseItem {

    private String time;
    private int id;

    public Alarm(String time) {
        this.time = time;
        id = UUIDGenerator.generateUniqueId();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getType() {
        return ItemTypes.ALARM.getType();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();

    }
}
