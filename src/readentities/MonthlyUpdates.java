package readentities;

import java.util.ArrayList;

public class MonthlyUpdates {
  private ArrayList<ConsumerUpdates> newConsumers;
  private ArrayList<DistributorUpdates> distributorChanges;
  private ArrayList<ProducerUpdates> producerChanges;

  public ArrayList<ConsumerUpdates> getNewConsumers() {
    return newConsumers;
  }

  public void setNewConsumers(ArrayList<ConsumerUpdates> newConsumers) {
    this.newConsumers = newConsumers;
  }

  public ArrayList<DistributorUpdates> getDistributorChanges() {
    return distributorChanges;
  }

  public void setDistributorChanges(ArrayList<DistributorUpdates> distributorChanges) {
    this.distributorChanges = distributorChanges;
  }

  public ArrayList<ProducerUpdates> getProducerChanges() {
    return producerChanges;
  }

  public void setProducerChanges(ArrayList<ProducerUpdates> producerChanges) {
    this.producerChanges = producerChanges;
  }

  @Override
  public String toString() {
    return "MonthlyUpdates{"
        + "newConsumers="
        + newConsumers
        + ", distributorChanges="
        + distributorChanges
        + ", producerChanges="
        + producerChanges
        + '}';
  }
}
