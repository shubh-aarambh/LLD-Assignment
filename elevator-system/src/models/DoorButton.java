package models;

import enums.ButtonType;

public class DoorButton extends Button {

    public DoorButton(ButtonType type) {
        super(type);
        if (type != ButtonType.DOOR_OPEN && type != ButtonType.DOOR_CLOSE) {
            throw new IllegalArgumentException("DoorButton must be DOOR_OPEN or DOOR_CLOSE");
        }
    }

    @Override
    public void press() {
        this.isPressed = true;
    }
}
