package com.epam.task.sixth;

import com.epam.task.sixth.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final String INPUT_TAXIS = "taxis.json";
    private static final String INPUT_CLIENTS = "clients.json";

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        File taxisFile = new File(INPUT_TAXIS);
        Taxis taxis = mapper.readValue(taxisFile, Taxis.class);
        TaxiPark taxiPark = TaxiPark.getInstance();
        taxiPark.setTaxis(taxis);

        File clientsFile = new File(INPUT_CLIENTS);
        Clients clients = mapper.readValue(clientsFile, Clients.class);
        int numberOfClients = clients.getNumberOfClients();

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfClients);

        List<Client> clientList = clients.getClients();
        clientList.stream().forEach(client -> executorService.submit(client));

        executorService.shutdown();
    }
}
