package com.epam.task.sixth.entities;

public class Taxi {

    private double currentX;
    private double currentY;
    private int id;

    public Taxi() {
    }

    public Taxi(double currentX, double currentY, int id) {
        this.currentX = currentX;
        this.currentY = currentY;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void process(Client client) {
        double clientDestinationX = client.getDestinationX();
        double clientDestinationY = client.getDestinationY();

        client.setCurrentX(clientDestinationX);
        client.setCurrentY(clientDestinationY);

        this.setCurrentX(clientDestinationX);
        this.setCurrentY(clientDestinationY);

        int clientId = client.getId();
        System.out.println("Client " + clientId + " has been delivered to their destination by taxi number " + this.id + ".");
    }
}
