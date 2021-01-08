package readentities;

import java.util.ArrayList;

public class InitialData {
  private ArrayList<Consumer> consumers;
  private ArrayList<Distributor> distributors;
  private ArrayList<Producer> producers;

  /** Method that returns consumers List */
  public ArrayList<Consumer> getConsumers() {
    return consumers;
  }
  /** Method that sets consumers List */
  public void setConsumers(ArrayList<Consumer> consumers) {
    this.consumers = consumers;
  }
  /** Method that returns distributor List */
  public ArrayList<Distributor> getDistributors() {
    return distributors;
  }
  /** Method that returns distributor List */
  public void setDistributors(ArrayList<Distributor> distributors) {
    this.distributors = distributors;
  }
  /** Method that returns producer List */
  public ArrayList<Producer> getProducers() {
    return producers;
  }
  /** Method that sets Producer List */
  public void setProducers(ArrayList<Producer> producers) {
    this.producers = producers;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "InitialData{"
        + "consumers="
        + consumers
        + ", distributors="
        + distributors
        + ", producers="
        + producers
        + '}';
  }
}
