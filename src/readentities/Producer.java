package readentities;

import entities.EnergyType;

import java.util.ArrayList;

public class Producer {
  private int id;
  private EnergyType energyType;
  private int maxDistributors;
  private double priceKW;
  private int energyPerDistributor;
  private ArrayList<MonthlyStats> monthlyStats = new ArrayList<>();
  private int numberDistributors = 0;
  private ArrayList<Integer> distributorIdList = new ArrayList<>();

  /** Method that increments number of distributors */
  public void incrementNumberDistributor() {
    numberDistributors++;
  }
  /** Method that decrements number of distributors */
  public void decrementNumberDistributor() {
    numberDistributors--;
  }
  /** Method that returns number of distributors */
  public int getNumberDistributors() {
    return numberDistributors;
  }
  /** Method that sets number of distributors */
  public void setNumberDistributors(int numberDistributors) {
    this.numberDistributors = numberDistributors;
  }
  /** Method that returns distributorsId List */
  public ArrayList<Integer> getDistributorIdList() {
    return distributorIdList;
  }
  /** Method that sets distributorsId List */
  public void setDistributorIdList(ArrayList<Integer> distributorIdList) {
    this.distributorIdList = distributorIdList;
  }
  /** Method that returns MonthlyStats List */
  public ArrayList<MonthlyStats> getMonthlyStats() {
    return monthlyStats;
  }
  /** Method that sets MonthlyStats List */
  public void setMonthlyStats(ArrayList<MonthlyStats> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }
  /** Method that returns Id */
  public int getId() {
    return id;
  }
  /** Method that sets Id */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns EnergyType */
  public EnergyType getEnergyType() {
    return energyType;
  }
  /** Method that sets EnergyType */
  public void setEnergyType(EnergyType energyType) {
    this.energyType = energyType;
  }
  /** Method that returns number of max distributors */
  public int getMaxDistributors() {
    return maxDistributors;
  }
  /** Method that sets number of max distributors */
  public void setMaxDistributors(int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }
  /** Method that returns price per KW */
  public double getPriceKW() {
    return priceKW;
  }
  /** Method that sets price per KW */
  public void setPriceKW(double priceKW) {
    this.priceKW = priceKW;
  }
  /** Method that returns energy per Distributor */
  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }
  /** Method that sets energy per Distributor */
  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "Producer{"
        + "id="
        + id
        + ", energyType="
        + energyType
        + ", maxDistributors="
        + maxDistributors
        + ", priceKW="
        + priceKW
        + ", energyPerDistributor="
        + energyPerDistributor
        + ", monthlyStats="
        + monthlyStats
        + '}';
  }
}
