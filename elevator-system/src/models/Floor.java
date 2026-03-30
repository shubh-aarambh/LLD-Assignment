package models;

import enums.Direction;

public class Floor {
    private final int floorNumber;
    private final FloorButton upButton;
    private final FloorButton downButton;

    public Floor(int floorNumber, int totalFloors) {
        this.floorNumber = floorNumber;
        this.upButton = (floorNumber < totalFloors) ? new FloorButton(floorNumber, Direction.UP) : null;
        this.downButton = (floorNumber > 1) ? new FloorButton(floorNumber, Direction.DOWN) : null;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public FloorButton getUpButton() {
        return upButton;
    }

    public FloorButton getDownButton() {
        return downButton;
    }

    public void pressUp() {
        if (upButton != null) {
            upButton.press();
        }
    }

    public void pressDown() {
        if (downButton != null) {
            downButton.press();
        }
    }
}
