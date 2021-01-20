package strategies;

import readentities.Distributor;
import readentities.InputData;
import readentities.Producer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QuantityStrategy implements EnergyChoiceStrategy {
  private Distributor distributor;
  private InputData inputData;
  private HashMap<Integer, Integer> quantityDistributorHashMap = new HashMap<>();

  public QuantityStrategy(Distributor distributor, InputData inputData) {
    this.distributor = distributor;
    this.inputData = inputData;
  }

  /** Method that returns quantity distributor hashMap */
  public HashMap<Integer, Integer> getQuantityDistributorHashMap() {
    return quantityDistributorHashMap;
  }
  /** Method that sets quantity distributor hashMap */
  public void setQuantityDistributorHashMap(HashMap<Integer, Integer> quantityDistributorHashMap) {
    this.quantityDistributorHashMap = quantityDistributorHashMap;
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
  /** Method that sorts HashMap */
  public HashMap sortHashMap(final HashMap map) {
    List<Map.Entry> list;
    list = new LinkedList<Map.Entry>(map.entrySet());
    list.sort((o1, o2) -> ((Comparable) o2.getValue()).compareTo(o1.getValue()));

    HashMap<Object, Object> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry entry : list) {
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    return sortedHashMap;
  }
  /** Method that sorts HashMap by quantity */
  public HashMap sortHashMapModified(final HashMap map) {
    List<Map.Entry> list;
    list = new LinkedList<Map.Entry>(map.entrySet());
    list.sort(
        (o1, o2) -> {
          if (o1.getValue() == o2.getValue()) {
            return ((Comparable) o1.getKey()).compareTo(o2.getKey());
          }
          return 0;
        });

    HashMap<Object, Object> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry entry : list) {
      sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    return sortedHashMap;
  }
  /** Method that finds a producer by ID */
  public Producer findProducerByID(int iD) {
    for (Producer producer : inputData.getInitialData().getProducers()) {
      if (producer.getId() == iD) {
        return producer;
      }
    }
    return null;
  }
  /** Method that add producer to distributor's List */
  public void setDistributorProducer() {
    int sumEnergy = 0;
    for (Map.Entry<Integer, Integer> entry : quantityDistributorHashMap.entrySet()) {
      if (findProducerByID(entry.getKey()).getNumberDistributors()
          < findProducerByID(entry.getKey()).getMaxDistributors()) {
        if (sumEnergy - distributor.getEnergyNeededKW() < 0) {
          distributor.getProducerList().add(findProducerByID(entry.getKey()));
          sumEnergy += entry.getValue();
          findProducerByID(entry.getKey()).getDistributorIdList().add(distributor.getId());
          findProducerByID(entry.getKey()).incrementNumberDistributor();
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
      quantityDistributorHashMap.put(producer.getId(), producer.getEnergyPerDistributor());
    }
    quantityDistributorHashMap = sortHashMap(quantityDistributorHashMap);
    quantityDistributorHashMap = sortHashMapModified(quantityDistributorHashMap);
    setDistributorProducer();
  }
}
