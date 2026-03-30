package panels;

import models.ElevatorButton;
import models.DoorButton;
import enums.ButtonType;
import java.util.ArrayList;
import java.util.List;

public class InsidePanel {
    private final int elevatorId;
    private final List<ElevatorButton> floorButtons;
    private final DoorButton openButton;
    private final DoorButton closeButton;

    public InsidePanel(int elevatorId, int totalFloors) {
        this.elevatorId = elevatorId;
        this.floorButtons = new ArrayList<>();
        for (int i = 1; i <= totalFloors; i++) {
            floorButtons.add(new ElevatorButton(i));
        }
        this.openButton = new DoorButton(ButtonType.DOOR_OPEN);
        this.closeButton = new DoorButton(ButtonType.DOOR_CLOSE);
    }

    public void pressFloorButton(int floor) {
        for (ElevatorButton btn : floorButtons) {
            if (btn.getTargetFloor() == floor) {
                btn.press();
                System.out.println("[Inside Panel - Elevator " + elevatorId + "] Floor " + floor + " button pressed.");
                return;
            }
        }
    }

    public void pressDoorOpen() {
        openButton.press();
        System.out.println("[Inside Panel - Elevator " + elevatorId + "] Door Open button pressed.");
    }

    public void pressDoorClose() {
        closeButton.press();
        System.out.println("[Inside Panel - Elevator " + elevatorId + "] Door Close button pressed.");
    }

    public List<ElevatorButton> getFloorButtons() {
        return floorButtons;
    }

    public DoorButton getOpenButton() {
        return openButton;
    }

    public DoorButton getCloseButton() {
        return closeButton;
    }
}
