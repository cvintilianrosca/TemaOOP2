package readentities;

import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class Distributor {
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


    public void addProfit(final long profitConsumer) {
        initialBudget += profitConsumer;
    }


    public void computeInitialProductionCost(){
        int cost =0;
        for (Producer producer : producerList) {
            cost+=(producer.getEnergyPerDistributor()*producer.getPriceKW());
        }
        initialProductionCost=(int)Math.round(Math.floor(cost / 10));
    }



    public void computeProfit() {
        final double multiplier = 0.2;
        setProfit((int) Math.round(Math.floor(multiplier * initialProductionCost)));
    }

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

    public void pay() {
        if (!isBankrupt) {
            if (initialBudget - (initialInfrastructureCost + initialProductionCost * contracts.size())
                    < 0) {
                isBankrupt = true;
            }
            initialBudget -= (initialInfrastructureCost + initialProductionCost * contracts.size());
        }
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public int getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(int monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }




    public ArrayList<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(ArrayList<Producer> producerList) {
        this.producerList = producerList;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    public long getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(long contractPrice) {
        this.contractPrice = contractPrice;
    }

    public ArrayList<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<Contracts> contracts) {
        this.contracts = contracts;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", contractLength=" + contractLength +
                ", initialBudget=" + initialBudget +
                ", initialInfrastructureCost=" + initialInfrastructureCost +
                ", energyNeededKW=" + energyNeededKW +
                ", initialProductionCost=" + initialProductionCost +
                ", contractPrice=" + contractPrice +
                ", profit=" + profit +
                ", monthlyExpenses=" + monthlyExpenses +
                ", contracts=" + contracts +
                ", producerStrategy=" + producerStrategy +
                ", isBankrupt=" + isBankrupt +
                ", producerList=" + producerList +
                '}';
    }
}
