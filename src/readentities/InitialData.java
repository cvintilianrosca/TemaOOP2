package readentities;

import java.util.ArrayList;

public class InitialData {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<Producer> producers;

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "consumers=" + consumers +
                ", distributors=" + distributors +
                ", producers=" + producers +
                '}';
    }
}
