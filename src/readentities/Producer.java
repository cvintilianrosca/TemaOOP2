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

  public void incrementNumberDistributor() {
    numberDistributors++;
  }

  public void decrementNumberDistributor() {
    numberDistributors--;
  }

  public int getNumberDistributors() {
    return numberDistributors;
  }

  public void setNumberDistributors(int numberDistributors) {
    this.numberDistributors = numberDistributors;
  }

  public ArrayList<Integer> getDistributorIdList() {
    return distributorIdList;
  }

  public void setDistributorIdList(ArrayList<Integer> distributorIdList) {
    this.distributorIdList = distributorIdList;
  }

  public ArrayList<MonthlyStats> getMonthlyStats() {
    return monthlyStats;
  }

  public void setMonthlyStats(ArrayList<MonthlyStats> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public EnergyType getEnergyType() {
    return energyType;
  }

  public void setEnergyType(EnergyType energyType) {
    this.energyType = energyType;
  }

  public int getMaxDistributors() {
    return maxDistributors;
  }

  public void setMaxDistributors(int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }

  public double getPriceKW() {
    return priceKW;
  }

  public void setPriceKW(double priceKW) {
    this.priceKW = priceKW;
  }

  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }

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
