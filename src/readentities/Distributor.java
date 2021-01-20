package readentities;

import strategies.EnergyChoiceStrategyFactory;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class Distributor implements CustomObserver {
  private int id;
  private int contractLength;
  private int initialBudget;
  private int initialInfrastructureCost;
  private int energyNeededKW;
  private int initialProductionCost;
  private long contractPrice;
  private long profit;
  private int monthlyExpenses;
  private ArrayList<Contracts> contracts = new ArrayList<>();
  private EnergyChoiceStrategyType producerStrategy;
  private boolean isBankrupt;
  private ArrayList<Producer> producerList = new ArrayList<>();

  public InputData getInputData() {
    return inputData;
  }

  public void setInputData(InputData inputData) {
    this.inputData = inputData;
  }

  private InputData inputData;

  /** Method that adds profit every month */
  public void addProfit(final long profitConsumer) {
    initialBudget += profitConsumer;
  }
  /** Method that computes initial production cost */
  public void computeInitialProductionCost() {
    int cost = 0;
    for (Producer producer : producerList) {
      cost += (producer.getEnergyPerDistributor() * producer.getPriceKW());
    }
    initialProductionCost = (int) Math.round(Math.floor(cost / 10));
  }
  /** Method that computes profit */
  public void computeProfit() {
    final double multiplier = 0.2;
    setProfit((int) Math.round(Math.floor(multiplier * initialProductionCost)));
  }
  /** Method that computes contract price */
  public void computeContractPrice() {
    if (contracts.size() == 0) {
      this.contractPrice = initialInfrastructureCost + initialProductionCost + profit;
    } else {
      this.contractPrice =
          Math.round(
              Math.floor(((float) initialInfrastructureCost / (float) contracts.size()))
                  + initialProductionCost
                  + profit);
    }
  }
  /** Method that subtract payment from budget */
  public void pay() {
    if (!isBankrupt) {
      if (initialBudget - (initialInfrastructureCost + initialProductionCost * contracts.size())
          < 0) {
        isBankrupt = true;
      }
      initialBudget -= (initialInfrastructureCost + initialProductionCost * contracts.size());
    }
  }
  /** Method that returns profit */
  public long getProfit() {
    return profit;
  }
  /** Method that sets profit */
  public void setProfit(long profit) {
    this.profit = profit;
  }
  /** Method that returns monthly expenses */
  public int getMonthlyExpenses() {
    return monthlyExpenses;
  }
  /** Method that sets monthly expenses */
  public void setMonthlyExpenses(int monthlyExpenses) {
    this.monthlyExpenses = monthlyExpenses;
  }
  /** Method that returns producers List */
  public ArrayList<Producer> getProducerList() {
    return producerList;
  }
  /** Method that sets producers List */
  public void setProducerList(ArrayList<Producer> producerList) {
    this.producerList = producerList;
  }
  /** Method that returns production cost */
  public int getInitialProductionCost() {
    return initialProductionCost;
  }
  /** Method that sets production cost */
  public void setInitialProductionCost(int initialProductionCost) {
    this.initialProductionCost = initialProductionCost;
  }
  /** Method that returns contract price */
  public long getContractPrice() {
    return contractPrice;
  }
  /** Method that sets contract price */
  public void setContractPrice(long contractPrice) {
    this.contractPrice = contractPrice;
  }
  /** Method that returns contracts List */
  public ArrayList<Contracts> getContracts() {
    return contracts;
  }
  /** Method that sets contracts List */
  public void setContracts(ArrayList<Contracts> contracts) {
    this.contracts = contracts;
  }
  /** Method that returns isBankrupt status */
  public boolean isBankrupt() {
    return isBankrupt;
  }
  /** Method that sets isBankrupt status */
  public void setBankrupt(boolean bankrupt) {
    this.isBankrupt = bankrupt;
  }
  /** Method that returns Id */
  public int getId() {
    return id;
  }
  /** Method that sets Id */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns contract length */
  public int getContractLength() {
    return contractLength;
  }
  /** Method that sets contract length */
  public void setContractLength(int contractLength) {
    this.contractLength = contractLength;
  }
  /** Method that returns budget */
  public int getInitialBudget() {
    return initialBudget;
  }
  /** Method that sets budget */
  public void setInitialBudget(int initialBudget) {
    this.initialBudget = initialBudget;
  }
  /** Method that returns infrastructure cost */
  public int getInitialInfrastructureCost() {
    return initialInfrastructureCost;
  }
  /** Method that sets infrastructure cost */
  public void setInitialInfrastructureCost(int initialInfrastructureCost) {
    this.initialInfrastructureCost = initialInfrastructureCost;
  }
  /** Method that returns energy needed */
  public int getEnergyNeededKW() {
    return energyNeededKW;
  }
  /** Method that sets energy needed */
  public void setEnergyNeededKW(int energyNeededKW) {
    this.energyNeededKW = energyNeededKW;
  }
  /** Method that returns energy strategy */
  public EnergyChoiceStrategyType getProducerStrategy() {
    return producerStrategy;
  }
  /** Method that sets energy strategy */
  public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
    this.producerStrategy = producerStrategy;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "Distributor{"
        + "id="
        + id
        + ", contractLength="
        + contractLength
        + ", initialBudget="
        + initialBudget
        + ", initialInfrastructureCost="
        + initialInfrastructureCost
        + ", energyNeededKW="
        + energyNeededKW
        + ", initialProductionCost="
        + initialProductionCost
        + ", contractPrice="
        + contractPrice
        + ", profit="
        + profit
        + ", monthlyExpenses="
        + monthlyExpenses
        + ", contracts="
        + contracts
        + ", producerStrategy="
        + producerStrategy
        + ", isBankrupt="
        + isBankrupt
        + ", producerList="
        + producerList
        + '}';
  }

  @Override
  public void update() {
    EnergyChoiceStrategyFactory.createStrategy(
            this.getProducerStrategy(), this, inputData).applyStrategy();
  }

}
