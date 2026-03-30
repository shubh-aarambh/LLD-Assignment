package models;

public class Passenger {
    private static int idCounter = 1;
    private final int passengerId;
    private final int sourceFloor;
    private final int destinationFloor;
    private final double weight;

    public Passenger(int sourceFloor, int destinationFloor, double weight) {
        this.passengerId = idCounter++;
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.weight = weight;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public double getWeight() {
        return weight;
    }
}
