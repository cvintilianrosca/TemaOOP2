package outputdata;

import readentities.Consumer;
import readentities.Distributor;
import readentities.InputData;
import readentities.Producer;

import java.util.ArrayList;

public class OutputData {
    private ArrayList<OutputConsumers> consumers=new ArrayList<>();
    private ArrayList<OutputDistributors> distributors=new ArrayList<>();
    private ArrayList<OutputProducers> energyProducers =new ArrayList<>();


    public void createOutputData(InputData inputData){
        for ( Consumer consumer : inputData.getInitialData().getConsumers()) {
            consumers.add(new OutputConsumers(consumer.getId(), consumer.isBankrupt(), consumer.getInitialBudget()));
        }
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
            distributors.add(new OutputDistributors(distributor.getId(), distributor.getEnergyNeededKW(),
                    (int)distributor.getContractPrice(), distributor.getInitialBudget(), distributor.getProducerStrategy(),
                    distributor.isBankrupt(), distributor.getContracts()));
        }

        for (Producer producer : inputData.getInitialData().getProducers()) {
            energyProducers.add(new OutputProducers(producer.getId(), producer.getMaxDistributors(), producer.getPriceKW(),
                    producer.getEnergyType(),producer.getEnergyPerDistributor(), producer.getMonthlyStats()));
        }
    }

    public ArrayList<OutputConsumers> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<OutputConsumers> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<OutputDistributors> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<OutputDistributors> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<OutputProducers> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(ArrayList<OutputProducers> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
