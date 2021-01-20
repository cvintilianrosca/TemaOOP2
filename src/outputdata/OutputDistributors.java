package outputdata;

import readentities.Contracts;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class OutputDistributors extends Entity {
  private int id;
  private int energyNeededKW;
  private int contractCost;
  private int budget;
  private EnergyChoiceStrategyType producerStrategy;
  private boolean isBankrupt;
  private ArrayList<Contracts> contracts;

  public OutputDistributors(
      int id,
      int energyNeededKW,
      int contractCost,
      int budget,
      EnergyChoiceStrategyType producerStrategy,
      boolean isBankrupt,
      ArrayList<Contracts> contracts) {
    super(id, budget, isBankrupt);
    this.contractCost = contractCost;
    this.contracts = contracts;
    this.energyNeededKW = energyNeededKW;
    this.producerStrategy = producerStrategy;
  }
  /** Method that returns distributor Id */
  public int getId() {
    return super.getId();
  }
  /** Method that sets Id */
  public void setId(int id) {
    super.setId(id);
  }
  /** Method that returns energy needed */
  public int getEnergyNeededKW() {
    return energyNeededKW;
  }
  /** Method that sets energy needed */
  public void setEnergyNeededKW(int energyNeededKW) {
    this.energyNeededKW = energyNeededKW;
  }
  /** Method that returns contract price */
  public int getContractCost() {
    return contractCost;
  }
  /** Method that sets contract price */
  public void setContractCost(int contractCost) {
    this.contractCost = contractCost;
  }
  /** Method that returns budget */
  public int getBudget() {
    return super.getBudget();
  }
  /** Method that sets budget */
  public void setBudget(int budget) {
    super.setBudget(budget);
  }
  /** Method that returns strategy */
  public EnergyChoiceStrategyType getProducerStrategy() {
    return producerStrategy;
  }
  /** Method that sets strategy */
  public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
    this.producerStrategy = producerStrategy;
  }
  /** Method that returns isBankrupt status */
  public boolean getisBankrupt() {
    return super.getisBankrupt();
  }
  /** Method that sets isBankrupt status */
  public void setisBankrupt(boolean bankrupt) {
    super.setBankrupt(bankrupt);
  }
  /** Method that returns contracts List */
  public ArrayList<Contracts> getContracts() {
    return contracts;
  }
  /** Method that sets contracts List */
  public void setContracts(ArrayList<Contracts> contracts) {
    this.contracts = contracts;
  }
}
