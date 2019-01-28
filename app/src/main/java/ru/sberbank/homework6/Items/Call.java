package ru.sberbank.homework6.Items;

import ru.sberbank.homework6.ItemTypes;

public class Call implements BaseItem {


    private String firstName;

    public Call(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int getType() {
        return ItemTypes.CALL.getType();
    }
}
