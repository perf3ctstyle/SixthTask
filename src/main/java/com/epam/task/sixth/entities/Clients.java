package com.epam.task.sixth.entities;

import java.util.List;

public class Clients {

    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getNumberOfClients() {
        return clients.size();
    }
}
