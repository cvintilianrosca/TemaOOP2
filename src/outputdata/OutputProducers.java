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
  /** Method that returns Id */
  public int getId() {
    return id;
  }
  /** Method that sets Id */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns max distributor number */
  public int getMaxDistributors() {
    return maxDistributors;
  }
  /** Method that sets max distributor number */
  public void setMaxDistributors(int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }
  /** Method that returns price */
  public double getPriceKW() {
    return priceKW;
  }
  /** Method that sets price */
  public void setPriceKW(double priceKW) {
    this.priceKW = priceKW;
  }
  /** Method that returns energy type */
  public EnergyType getEnergyType() {
    return energyType;
  }
  /** Method that sets energy type */
  public void setEnergyType(EnergyType energyType) {
    this.energyType = energyType;
  }
  /** Method that returns energy per distributor */
  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }
  /** Method that sets energy per distributor */
  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }
  /** Method that returns MonthlyStats List */
  public ArrayList<MonthlyStats> getMonthlyStats() {
    return monthlyStats;
  }
  /** Method that sets MonthlyStats List */
  public void setMonthlyStats(ArrayList<MonthlyStats> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }
}
