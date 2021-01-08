package readentities;

import java.util.ArrayList;

public class MonthlyUpdates {
  private ArrayList<ConsumerUpdates> newConsumers;
  private ArrayList<DistributorUpdates> distributorChanges;
  private ArrayList<ProducerUpdates> producerChanges;

  /** Method that returns consumers List */
  public ArrayList<ConsumerUpdates> getNewConsumers() {
    return newConsumers;
  }
  /** Method that sets consumers List */
  public void setNewConsumers(ArrayList<ConsumerUpdates> newConsumers) {
    this.newConsumers = newConsumers;
  }
  /** Method that returns distributors List */
  public ArrayList<DistributorUpdates> getDistributorChanges() {
    return distributorChanges;
  }
  /** Method that sets distributors List */
  public void setDistributorChanges(ArrayList<DistributorUpdates> distributorChanges) {
    this.distributorChanges = distributorChanges;
  }
  /** Method that returns producers List */
  public ArrayList<ProducerUpdates> getProducerChanges() {
    return producerChanges;
  }
  /** Method that sets producers List */
  public void setProducerChanges(ArrayList<ProducerUpdates> producerChanges) {
    this.producerChanges = producerChanges;
  }
  /** Method toString() */
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
