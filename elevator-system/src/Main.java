import models.Passenger;
import system.ElevatorSystem;

public class Main {

    public static void main(String[] args) {
        ElevatorSystem system = ElevatorSystem.getInstance();

        System.out.println("\n========== SCENARIO 1: Multiple Passengers, Different Floors ==========");
        system.requestElevator(1, 10);
        system.requestElevator(3, 7);
        system.requestElevator(12, 2);

        system.showAllElevatorStatuses();

        System.out.println("\n========== SCENARIO 2: Boarding Passengers into Elevator 1 ==========");
        Passenger p1 = new Passenger(1, 10, 70.0);
        Passenger p2 = new Passenger(1, 10, 65.0);
        system.boardPassengerToElevator(1, p1);
        system.boardPassengerToElevator(1, p2);

        System.out.println("\n========== SCENARIO 3: Running All Elevators Until Idle ==========");
        system.runAllElevatorsUntilIdle();

        System.out.println("\n========== SCENARIO 4: Floor Panel Display Sync ==========");
        system.syncFloorDisplays();
        system.showFloorPanel(5);
        system.showFloorPanel(10);

        System.out.println("\n========== SCENARIO 5: Capacity Stress Test ==========");
        for (int i = 0; i < 9; i++) {
            Passenger p = new Passenger(2, 14, 75.0);
            system.boardPassengerToElevator(2, p);
        }

        System.out.println("\n========== FINAL SYSTEM STATUS ==========");
        system.showAllElevatorStatuses();
    }
}
