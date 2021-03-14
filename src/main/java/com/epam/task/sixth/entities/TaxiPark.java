package com.epam.task.sixth.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiPark {

    private Taxis taxis;
    private static final int NUMBER_OF_AVAILABLE_OPERATORS = 2;
    private static final Logger logger = LogManager.getLogger(TaxiPark.class);
    private static final Lock lock = new ReentrantLock();
    private static final Semaphore semaphore = new Semaphore(NUMBER_OF_AVAILABLE_OPERATORS);
    private static TaxiPark instance;

    private TaxiPark() {
    }

    public static TaxiPark getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new TaxiPark();
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void setTaxis(Taxis taxis) {
        this.taxis = taxis;
    }

    private double calculateDistanceBetweenTaxiAndClient(Taxi taxi, Client client) {
        double taxiX = taxi.getCurrentX();
        double taxiY = taxi.getCurrentY();
        double clientX = client.getCurrentX();
        double clientY = client.getCurrentY();
        return Math.sqrt((taxiX-clientX)*(taxiX-clientX) + (taxiY-clientY)*(taxiY-clientY));
    }

    public void process(Client client) {
        try {
            semaphore.acquire();
            lock.lock();

            List<Taxi> taxiList = taxis.getTaxis();

            double minDistance = calculateDistanceBetweenTaxiAndClient(taxiList.get(0), client);
            int numberOfClosestTaxi = 0;

            for (Taxi taxi : taxiList) {
                double distance = calculateDistanceBetweenTaxiAndClient(taxi, client);
                if (distance < minDistance) {
                    minDistance = distance;
                    numberOfClosestTaxi = taxiList.indexOf(taxi);
                }
            }

            Taxi closestTaxi = taxiList.get(numberOfClosestTaxi);
            closestTaxi.process(client);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            semaphore.release();
            lock.unlock();
        }
    }
}
