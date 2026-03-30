package models;

import enums.ButtonType;

public class ElevatorButton extends Button {
    private final int targetFloor;

    public ElevatorButton(int targetFloor) {
        super(ButtonType.ELEVATOR_FLOOR);
        this.targetFloor = targetFloor;
    }

    @Override
    public void press() {
        this.isPressed = true;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}
