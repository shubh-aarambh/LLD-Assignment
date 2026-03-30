package models;

import enums.ButtonType;
import enums.Direction;

public class FloorButton extends Button {
    private final int floorNumber;
    private final Direction direction;

    public FloorButton(int floorNumber, Direction direction) {
        super(direction == Direction.UP ? ButtonType.FLOOR_CALL_UP : ButtonType.FLOOR_CALL_DOWN);
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    @Override
    public void press() {
        this.isPressed = true;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}
