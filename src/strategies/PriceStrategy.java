package strategies;

import readentities.Distributor;
import readentities.InputData;
import readentities.Producer;

import java.util.*;

public class PriceStrategy implements EnergyChoiceStrategy {
  private Distributor distributor;
  private InputData inputData;
  private HashMap<Integer, Producer> priceDistributorHashMap = new HashMap<>();

  public PriceStrategy(Distributor distributor, InputData inputData) {
    this.distributor = distributor;
    this.inputData = inputData;
  }
  /** Method that returns Distributor */
  public Distributor getDistributor() {
    return distributor;
  }
  /** Method that sets Distributor */
  public void setDistributor(Distributor distributor) {
    this.distributor = distributor;
  }
  /** Method that returns inputData */
  public InputData getInputData() {
    return inputData;
  }
  /** Method that sets inputData */
  public void setInputData(InputData inputData) {
    this.inputData = inputData;
  }
  /** Method that sorts HashMap by price */
  public HashMap priceSortHashMap(final HashMap<Integer, Producer> map) {
    List<Map.Entry<Integer, Producer>> list;
    list = new LinkedList<>();
    for (Map.Entry<Integer, Producer> entry : map.entrySet()) {
      list.add(entry);
    }

    list.sort(
        new Comparator<Map.Entry<Integer, Producer>>() {
          @Override
          public int compare(Map.Entry<Integer, Producer> o1, Map.Entry<Integer, Producer> o2) {
            return ((Comparable) o1.getValue().getPriceKW()).compareTo(o2.getValue().getPriceKW());
          }
        });

    HashMap<Integer, Producer> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, Producer> entry : list) {
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    return sortedHashMap;
  }
  /** Method that sorts HashMap by quantity */
  public HashMap quantitySortHashMap(final HashMap<Integer, Producer> map) {
    List<Map.Entry<Integer, Producer>> list;
    list = new LinkedList<>();
    for (Map.Entry<Integer, Producer> entry : map.entrySet()) {
      list.add(entry);
    }

    list.sort(
        new Comparator<Map.Entry<Integer, Producer>>() {
          @Override
          public int compare(Map.Entry<Integer, Producer> o1, Map.Entry<Integer, Producer> o2) {
            if (o1.getValue().getPriceKW() == o2.getValue().getPriceKW()) {
              return ((Comparable) o2.getValue().getEnergyPerDistributor())
                  .compareTo(o1.getValue().getEnergyPerDistributor());
            }

            return ((Comparable) o1.getValue().getPriceKW()).compareTo(o2.getValue().getPriceKW());
          }
        });

    HashMap<Integer, Producer> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, Producer> entry : list) {
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    return sortedHashMap;
  }
  /** Method that sorts hashMap by Id */
  public HashMap idSortHashMap(final HashMap<Integer, Producer> map) {
    List<Map.Entry<Integer, Producer>> list;
    list = new LinkedList<>();
    for (Map.Entry<Integer, Producer> entry : map.entrySet()) {
      list.add(entry);
    }

    list.sort(
        new Comparator<Map.Entry<Integer, Producer>>() {
          @Override
          public int compare(Map.Entry<Integer, Producer> o1, Map.Entry<Integer, Producer> o2) {
            if (o1.getValue().getPriceKW() == o2.getValue().getPriceKW()) {
              if (o1.getValue().getEnergyPerDistributor()
                  == o2.getValue().getEnergyPerDistributor()) {
                return ((Comparable) o1.getValue().getId()).compareTo(o2.getValue().getId());
              }
            }

            return 0;
          }
        });

    HashMap<Integer, Producer> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, Producer> entry : list) {
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    return sortedHashMap;
  }

  /** Method that add producer to distributor's List */
  public void setDistributorProducer() {
    int sumEnergy = 0;

    for (Map.Entry<Integer, Producer> entry : priceDistributorHashMap.entrySet()) {
      if (entry.getValue().getNumberDistributors() < entry.getValue().getMaxDistributors()) {
        if (sumEnergy - distributor.getEnergyNeededKW() < 0) {
          distributor.getProducerList().add(entry.getValue());
          sumEnergy += entry.getValue().getEnergyPerDistributor();
          entry.getValue().getDistributorIdList().add(distributor.getId());
          entry.getValue().incrementNumberDistributor();
        } else {
          break;
        }
      }
    }
  }

  /** Method that applies the strategy */
  @Override
  public void applyStrategy() {
    for (Producer producer : inputData.getInitialData().getProducers()) {
      priceDistributorHashMap.put(producer.getId(), producer);
    }

    priceDistributorHashMap = priceSortHashMap(priceDistributorHashMap);
    priceDistributorHashMap = quantitySortHashMap(priceDistributorHashMap);
    priceDistributorHashMap = idSortHashMap(priceDistributorHashMap);
    setDistributorProducer();
  }
}
