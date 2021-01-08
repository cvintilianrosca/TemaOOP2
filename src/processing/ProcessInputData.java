package processing;

import readentities.*;
import strategies.EnergyChoiceStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessInputData {
  private InputData inputData;

  public ProcessInputData(InputData inputData) {
    this.inputData = inputData;
  }

  /** Method that finds a distributor by its Id */
  public Distributor findDistributor(final int id) {
    for (Distributor distributor : inputData.getInitialData().getDistributors()) {
      if (distributor.getId() == id) {
        return distributor;
      }
    }
    return null;
  }

  /** Method that returns the distributor with the best price */
  public Distributor findBestDistributorPrice() {
    long min = 0;
    Distributor distributorsMinimumPrice = null;
    boolean flag = false;
    for (Distributor distributor : inputData.getInitialData().getDistributors()) {
      if (!distributor.isBankrupt()) {
        if (!flag) {
          min = distributor.getContractPrice();
          distributorsMinimumPrice = distributor;
          flag = true;
        } else {
          if (min > distributor.getContractPrice()) {
            min = distributor.getContractPrice();
            distributorsMinimumPrice = distributor;
          }
        }
      }
    }
    return distributorsMinimumPrice;
  }

  /** Method that creates the connection between consumer and distributor */
  public void relateConsumerWithDistributor(Consumer consumer) {
    Distributor distributorBestPrice = findBestDistributorPrice();
    consumer.setDistributorID(distributorBestPrice.getId());
    consumer.setContractPrice(distributorBestPrice.getContractPrice());
    consumer.setMonthsRemained(distributorBestPrice.getContractLength());
    distributorBestPrice
        .getContracts()
        .add(
            new Contracts(
                consumer.getId(),
                distributorBestPrice.getContractPrice(),
                distributorBestPrice.getContractLength()));
  }

  /** Method that decrement number contract month */
  public void decrementContractMonth(final Distributor distributors, final int consumerID) {
    for (Contracts contract : distributors.getContracts()) {
      if (contract.getConsumerId() == consumerID) {
        contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
      }
    }
  }

  /** Method that simulate first month of the electrical energy system */
  public void firstMonth() {
    for (Distributor distributor : inputData.getInitialData().getDistributors()) {
      EnergyChoiceStrategyFactory.createStrategy(
              distributor.getProducerStrategy(), distributor, inputData)
          .applyStrategy();
      distributor.computeInitialProductionCost();
    }

    for (Distributor distributor : inputData.getInitialData().getDistributors()) {
      distributor.computeProfit();
      distributor.computeContractPrice();
    }

    for (Consumer consumer : inputData.getInitialData().getConsumers()) {
      if (consumer.getDistributorID() == -1) {
        relateConsumerWithDistributor(consumer);
      }
    }

    for (Consumer consumer : inputData.getInitialData().getConsumers()) {
      Distributor distributor = findDistributor(consumer.getDistributorID());
      consumer.addMonthlyIncome();
      consumer.decrementMonth();
      distributor.addProfit(consumer.pay());
      decrementContractMonth(distributor, consumer.getId());
    }

    for (Distributor distributor : inputData.getInitialData().getDistributors()) {
      distributor.pay();
    }
  }

  /** Method that finds a producer by its ID */
  public Producer findProducerById(int producerId) {
    for (Producer producer : inputData.getInitialData().getProducers()) {
      if (producer.getId() == producerId) {
        return producer;
      }
    }
    return null;
  }

  /** Method that updates Lists every month */
  public void makeUpdates(MonthlyUpdates monthlyUpdate) {
    for (ConsumerUpdates consumerUpdate : monthlyUpdate.getNewConsumers()) {
      Consumer consumer = new Consumer();
      consumer.setId(consumerUpdate.getId());
      consumer.setInitialBudget(consumerUpdate.getInitialBudget());
      consumer.setMonthlyIncome(consumerUpdate.getMonthlyIncome());
      inputData.getInitialData().getConsumers().add(consumer);
    }
    for (DistributorUpdates distributorUpdate : monthlyUpdate.getDistributorChanges()) {
      findDistributor(distributorUpdate.getId())
          .setInitialInfrastructureCost(distributorUpdate.getInfrastructureCost());
    }
  }

  /** Method that updates the producerList */
  public boolean makeProducerUpdates(MonthlyUpdates monthlyUpdate) {
    boolean flag = false;
    if (monthlyUpdate.getProducerChanges().size() != 0) {
      flag = true;
      for (ProducerUpdates producerUpdates : monthlyUpdate.getProducerChanges()) {
        findProducerById(producerUpdates.getId())
            .setEnergyPerDistributor(producerUpdates.getEnergyPerDistributor());
      }
    }
    return flag;
  }

  /** Method that simulate every month */
  public void startMonthSimulation() {
    int monthNumber = 1;

    for (MonthlyUpdates monthlyUpdate : inputData.getMonthlyUpdates()) {
      makeUpdates(monthlyUpdate);
      for (Distributor distributor : inputData.getInitialData().getDistributors()) {
        distributor.computeInitialProductionCost();
        distributor.computeProfit();
        distributor.computeContractPrice();
      }

      for (Consumer consumer : inputData.getInitialData().getConsumers()) {

        if (consumer.isBankrupt()) {
          Distributor distributor = findDistributor(consumer.getDistributorID());
          Contracts saveContracts = null;
          for (Contracts contracts : distributor.getContracts()) {
            if (contracts.getConsumerId() == consumer.getId()) {
              saveContracts = contracts;
              break;
            }
          }
          distributor.getContracts().remove(saveContracts);
        }
        if (consumer.getDistributorID() == -1) {
          relateConsumerWithDistributor(consumer);
        }

        if (consumer.getMonthsRemained() == 0 && !consumer.isBankrupt()) {
          Distributor bestPriceDistributor = findBestDistributorPrice();
          if (bestPriceDistributor == findDistributor(consumer.getDistributorID())) {
            consumer.setDistributorID(bestPriceDistributor.getId());
            consumer.setContractPrice(bestPriceDistributor.getContractPrice());
            consumer.setMonthsRemained(bestPriceDistributor.getContractLength());
            Contracts saveContract = null;
            for (Contracts contract : bestPriceDistributor.getContracts()) {
              if (contract.getConsumerId() == consumer.getId()) {
                saveContract = contract;
                break;
              }
            }

            bestPriceDistributor.getContracts().remove(saveContract);
            bestPriceDistributor
                .getContracts()
                .add(
                    new Contracts(
                        consumer.getId(),
                        bestPriceDistributor.getContractPrice(),
                        bestPriceDistributor.getContractLength()));
          } else {
            Distributor distributor = findDistributor(consumer.getDistributorID());
            Contracts saveContract = null;
            for (Contracts contract : distributor.getContracts()) {
              if (contract.getConsumerId() == consumer.getId()) {
                saveContract = contract;
                break;
              }
            }
            distributor.getContracts().remove(saveContract);
            consumer.setDistributorID(bestPriceDistributor.getId());
            consumer.setContractPrice(bestPriceDistributor.getContractPrice());
            consumer.setMonthsRemained(bestPriceDistributor.getContractLength());
            bestPriceDistributor
                .getContracts()
                .add(
                    new Contracts(
                        consumer.getId(),
                        bestPriceDistributor.getContractPrice(),
                        bestPriceDistributor.getContractLength()));
          }
        }

        Distributor distributor = findDistributor(consumer.getDistributorID());
        consumer.addMonthlyIncome();
        consumer.decrementMonth();
        distributor.addProfit(consumer.pay());
        decrementContractMonth(distributor, consumer.getId());
      }

      for (Distributor distributor : inputData.getInitialData().getDistributors()) {
        distributor.pay();
      }

      if (makeProducerUpdates(monthlyUpdate)) {
        List<Integer> distributorsToUpdateList = new ArrayList<>();

        for (ProducerUpdates producerUpdates : monthlyUpdate.getProducerChanges()) {
          Producer producer = findProducerById(producerUpdates.getId());
          distributorsToUpdateList.addAll(producer.getDistributorIdList());
        }

        List<Integer> newList =
            distributorsToUpdateList.stream().distinct().collect(Collectors.toList());

        ArrayList<Integer> distributorSorted = new ArrayList<>(newList);

        distributorSorted.sort(
            new Comparator<Integer>() {
              @Override
              public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
              }
            });

        for (Integer id : distributorSorted) {
          Distributor distributor = findDistributor(id);
          distributor.getProducerList().clear();

          for (Producer producer : inputData.getInitialData().getProducers()) {
            if (producer.getDistributorIdList().contains(id)) {
              producer.getDistributorIdList().remove(id);
              producer.decrementNumberDistributor();
            }
          }
          EnergyChoiceStrategyFactory.createStrategy(
                  distributor.getProducerStrategy(), distributor, inputData)
              .applyStrategy();
          distributor.computeInitialProductionCost();
        }
      }

      for (Distributor distributor : inputData.getInitialData().getDistributors()) {
        for (Producer producer : distributor.getProducerList()) {
          boolean monthFlag = false;
          for (MonthlyStats stats : producer.getMonthlyStats()) {
            if (stats.getMonth() == monthNumber) {
              stats.getDistributorsIds().add(distributor.getId());
              monthFlag = true;
            }
          }
          if (monthFlag == false) {
            MonthlyStats monthlyStats = new MonthlyStats();
            monthlyStats.setMonth(monthNumber);
            monthlyStats.getDistributorsIds().add(distributor.getId());
            producer.getMonthlyStats().add(monthlyStats);
          }
        }
      }

      for (Producer producer : inputData.getInitialData().getProducers()) {
        boolean monthlyFlag = false;
        for (MonthlyStats monthlystats : producer.getMonthlyStats()) {
          if (monthlystats.getMonth() == monthNumber) {
            monthlyFlag = true;
            break;
          }
        }
        if (!monthlyFlag) {
          MonthlyStats monthlyStats = new MonthlyStats();
          monthlyStats.setMonth(monthNumber);
          producer.getMonthlyStats().add(monthlyStats);
        }
      }

      monthNumber++;
    }
  }

  /** Method that starts the simulation */
  public void start() {
    firstMonth();
    startMonthSimulation();
  }

  /** Method that returns modified inputData */
  public InputData getInputData() {
    return inputData;
  }

  /** Method that sets InputData */
  public void setInputData(InputData inputData) {
    this.inputData = inputData;
  }
}
