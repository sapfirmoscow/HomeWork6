package ru.sberbank.homework6.Items;

import ru.sberbank.homework6.ItemTypes;
import ru.sberbank.homework6.UUIDGenerator;

public class Call implements BaseItem {


    private String firstName;
    private int id;

    public Call(String firstName) {
        this.firstName = firstName;
        id = UUIDGenerator.generateUniqueId();
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

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();

    }
}
