package ru.sberbank.homework6.Items;

import ru.sberbank.homework6.ItemTypes;

public class Sms implements BaseItem {

    private String smsText;

    public Sms(String smsText) {
        this.smsText = smsText;
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
}
