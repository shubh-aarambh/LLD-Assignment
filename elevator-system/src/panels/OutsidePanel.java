package panels;

import models.FloorButton;
import enums.Direction;

public class OutsidePanel {
    private final int floorNumber;
    private final FloorButton upButton;
    private final FloorButton downButton;

    public OutsidePanel(int floorNumber, int totalFloors) {
        this.floorNumber = floorNumber;
        this.upButton = (floorNumber < totalFloors) ? new FloorButton(floorNumber, Direction.UP) : null;
        this.downButton = (floorNumber > 1) ? new FloorButton(floorNumber, Direction.DOWN) : null;
    }

    public void pressUp() {
        if (upButton != null) {
            upButton.press();
            System.out.println("[Outside Panel - Floor " + floorNumber + "] UP button pressed.");
        }
    }

    public void pressDown() {
        if (downButton != null) {
            downButton.press();
            System.out.println("[Outside Panel - Floor " + floorNumber + "] DOWN button pressed.");
        }
    }

    public FloorButton getUpButton() {
        return upButton;
    }

    public FloorButton getDownButton() {
        return downButton;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
