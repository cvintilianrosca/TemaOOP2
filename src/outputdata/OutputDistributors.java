package outputdata;

import readentities.Contracts;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class OutputDistributors {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private ArrayList<Contracts> contracts;

    public OutputDistributors(int id, int energyNeededKW, int contractCost, int budget, EnergyChoiceStrategyType producerStrategy
    , boolean isBankrupt, ArrayList<Contracts> contracts){
        this.id=id;
        this.budget=budget;
        this.contractCost=contractCost;
        this.contracts=contracts;
        this.energyNeededKW=energyNeededKW;
        this.isBankrupt=isBankrupt;
        this.producerStrategy=producerStrategy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public boolean getisBankrupt() {
        return isBankrupt;
    }

    public void setisBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<Contracts> contracts) {
        this.contracts = contracts;
    }
}
