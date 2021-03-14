package com.epam.task.sixth.entities;

public class Client implements Runnable {

    private double currentX;
    private double currentY;
    private double destinationX;
    private double destinationY;
    private int id;

    public Client() {
    }

    public Client(double currentX, double currentY, double destinationX, double destinationY, int id) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.id = id;
    }

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        TaxiPark taxiPark = TaxiPark.getInstance();
        taxiPark.process(this);
    }
}
