package ru.sberbank.homework6.Items;

import ru.sberbank.homework6.ItemTypes;

public class Alarm implements BaseItem {

    private String time;

    public Alarm(String time) {
        this.time = time;
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
}
