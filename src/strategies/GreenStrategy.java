package strategies;

import entities.EnergyType;
import readentities.Distributor;
import readentities.InputData;
import readentities.Producer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GreenStrategy implements EnergyChoiceStrategy {
  private Distributor distributor;
  private InputData inputData;
  private HashMap<Integer, Producer> greenHashMap = new HashMap<>();
  private HashMap<Integer, Producer> othersHashMap = new HashMap<>();

  public GreenStrategy(Distributor distributor, InputData inputData) {
    this.distributor = distributor;
    this.inputData = inputData;
  }
  /** Method that returns Distributor */
  public Distributor getDistributor() {
    return distributor;
  }
  /** Method sets returns Distributor */
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

            return 0;
          }
        });

    HashMap<Integer, Producer> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, Producer> entry : list) {
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    return sortedHashMap;
  }
  /** Method that sorts HashMap by Id */
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
    for (Map.Entry<Integer, Producer> entry : greenHashMap.entrySet()) {
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
    for (Map.Entry<Integer, Producer> entry : othersHashMap.entrySet()) {

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
      if (producer.getEnergyType().compareTo(EnergyType.HYDRO) == 0
          || producer.getEnergyType().compareTo(EnergyType.SOLAR) == 0
          || producer.getEnergyType().compareTo(EnergyType.WIND) == 0) {
        greenHashMap.put(producer.getId(), producer);
      } else {
        othersHashMap.put(producer.getId(), producer);
      }
    }
    greenHashMap = priceSortHashMap(greenHashMap);
    greenHashMap = quantitySortHashMap(greenHashMap);
    greenHashMap = idSortHashMap(greenHashMap);

    othersHashMap = priceSortHashMap(othersHashMap);
    othersHashMap = quantitySortHashMap(othersHashMap);
    othersHashMap = idSortHashMap(othersHashMap);
    setDistributorProducer();
  }
}
