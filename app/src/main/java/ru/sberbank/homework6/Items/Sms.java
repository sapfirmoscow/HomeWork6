package ru.sberbank.homework6.Items;

import ru.sberbank.homework6.ItemTypes;
import ru.sberbank.homework6.UUIDGenerator;

public class Sms implements BaseItem {

    private String smsText;
    private int id;

    public Sms(String smsText) {
        this.smsText = smsText;
        id = UUIDGenerator.generateUniqueId();
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    @Override
    public int getType() {
        return ItemTypes.SMS.getType();
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
