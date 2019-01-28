package ru.sberbank.homework6;

public enum ItemTypes {
    CALL(0), SMS(1), ALARM(2);

    int type;

    ItemTypes(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
