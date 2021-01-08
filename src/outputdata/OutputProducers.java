package outputdata;

import entities.EnergyType;
import readentities.MonthlyStats;

import java.util.ArrayList;

public class OutputProducers {

  private int id;
  private int maxDistributors;
  private double priceKW;
  private EnergyType energyType;
  private int energyPerDistributor;
  private ArrayList<MonthlyStats> monthlyStats;

  public OutputProducers(
      int id,
      int maxDistributors,
      double priceKW,
      EnergyType energyType,
      int energyPerDistributor,
      ArrayList<MonthlyStats> monthlyStats) {
    this.energyPerDistributor = energyPerDistributor;
    this.energyType = energyType;
    this.id = id;
    this.maxDistributors = maxDistributors;
    this.priceKW = priceKW;
    this.monthlyStats = monthlyStats;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public EnergyType getEnergyType() {
    return energyType;
  }

  public void setEnergyType(EnergyType energyType) {
    this.energyType = energyType;
  }

  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }

  public ArrayList<MonthlyStats> getMonthlyStats() {
    return monthlyStats;
  }

  public void setMonthlyStats(ArrayList<MonthlyStats> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }
}
