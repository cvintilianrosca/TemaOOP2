package strategies;

import readentities.Distributor;
import readentities.InputData;
import readentities.Producer;

import java.util.*;

public class QuantityStrategy implements EnergyChoiceStrategy {
    private Distributor distributor;
    private InputData inputData;

    public HashMap<Integer, Integer> getQuantityDistributorHashMap() {
        return quantityDistributorHashMap;
    }

    public void setQuantityDistributorHashMap(HashMap<Integer, Integer> quantityDistributorHashMap) {
        this.quantityDistributorHashMap = quantityDistributorHashMap;
    }

    private HashMap<Integer, Integer> quantityDistributorHashMap = new HashMap<>();

    public QuantityStrategy(Distributor distributor, InputData inputData){
        this.distributor=distributor;
        this.inputData=inputData;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public InputData getInputData() {
        return inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }


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

    public HashMap sortHashMapModified(final HashMap map) {
        List<Map.Entry> list;
        list = new LinkedList<Map.Entry>(map.entrySet());
        list.sort((o1, o2) -> {
            if (o1.getValue()==o2.getValue()){
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


    public Producer findProducerByID(int Id){
        for (Producer producer : inputData.getInitialData().getProducers()) {
            if (producer.getId() == Id){
                return producer;
            }
        }
        return null;
    }

    public void setDistributorProducer(){
        int sumEnergy=0;
        for (Map.Entry<Integer, Integer> entry : quantityDistributorHashMap.entrySet()) {
            if (findProducerByID(entry.getKey()).getNumberDistributors() < findProducerByID(entry.getKey()).getMaxDistributors()){
           if (sumEnergy-distributor.getEnergyNeededKW()<0){
               distributor.getProducerList().add(findProducerByID(entry.getKey()));
               sumEnergy+=entry.getValue();
               findProducerByID(entry.getKey()).getDistributorIdList().add(distributor.getId());
               findProducerByID(entry.getKey()).incrementNumberDistributor();
           }else
           {
               break;
           }
        }
        }
    }

    @Override
    public void applyStrategy() {

        for (Producer producer : inputData.getInitialData().getProducers()) {
            quantityDistributorHashMap.put(producer.getId(), producer.getEnergyPerDistributor());
        }
        quantityDistributorHashMap=sortHashMap(quantityDistributorHashMap);
        quantityDistributorHashMap=sortHashMapModified(quantityDistributorHashMap);
        setDistributorProducer();
    }
}
