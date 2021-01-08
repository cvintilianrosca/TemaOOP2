package outputdata;

import readentities.Consumer;
import readentities.Distributor;
import readentities.InputData;
import readentities.Producer;

import java.util.ArrayList;

public class OutputData {
  private ArrayList<OutputConsumers> consumers = new ArrayList<>();
  private ArrayList<OutputDistributors> distributors = new ArrayList<>();
  private ArrayList<OutputProducers> energyProducers = new ArrayList<>();

  /** Method that copies data into output format */
  public void createOutputData(InputData inputData) {
    for (Consumer consumer : inputData.getInitialData().getConsumers()) {
      consumers.add(
          new OutputConsumers(
              consumer.getId(), consumer.isBankrupt(), consumer.getInitialBudget()));
    }
    for (Distributor distributor : inputData.getInitialData().getDistributors()) {
      distributors.add(
          new OutputDistributors(
              distributor.getId(),
              distributor.getEnergyNeededKW(),
              (int) distributor.getContractPrice(),
              distributor.getInitialBudget(),
              distributor.getProducerStrategy(),
              distributor.isBankrupt(),
              distributor.getContracts()));
    }

    for (Producer producer : inputData.getInitialData().getProducers()) {
      energyProducers.add(
          new OutputProducers(
              producer.getId(),
              producer.getMaxDistributors(),
              producer.getPriceKW(),
              producer.getEnergyType(),
              producer.getEnergyPerDistributor(),
              producer.getMonthlyStats()));
    }
  }
  /** Method that returns consumers List */
  public ArrayList<OutputConsumers> getConsumers() {
    return consumers;
  }
  /** Method that sets consumers List */
  public void setConsumers(ArrayList<OutputConsumers> consumers) {
    this.consumers = consumers;
  }
  /** Method that returns distributors List */
  public ArrayList<OutputDistributors> getDistributors() {
    return distributors;
  }
  /** Method that sets distributors List */
  public void setDistributors(ArrayList<OutputDistributors> distributors) {
    this.distributors = distributors;
  }
  /** Method that returns producers List */
  public ArrayList<OutputProducers> getEnergyProducers() {
    return energyProducers;
  }
  /** Method that sets producers List */
  public void setEnergyProducers(ArrayList<OutputProducers> energyProducers) {
    this.energyProducers = energyProducers;
  }
}
