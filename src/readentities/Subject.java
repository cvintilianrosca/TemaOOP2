package readentities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Subject implements CustomObservable {

    List<Integer> distributorsToUpdateList = new ArrayList<>();
    MonthlyUpdates monthlyUpdate;
    InputData inputData;
    ArrayList<Integer> distributorSorted;

    public Subject(MonthlyUpdates monthlyUpdates, InputData inputData){
        this.monthlyUpdate=monthlyUpdates;
        this.inputData=inputData;
    }


    public Distributor findDistributor(final int id) {
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
            if (distributor.getId() == id) {
                return distributor;
            }
        }
        return null;
    }

    public Producer findProducerById(int producerId) {
        for (Producer producer : inputData.getInitialData().getProducers()) {
            if (producer.getId() == producerId) {
                return producer;
            }
        }
        return null;
    }

    @Override
    public void attach() {

        for (ProducerUpdates producerUpdates : monthlyUpdate.getProducerChanges()) {
            Producer producer = findProducerById(producerUpdates.getId());
            distributorsToUpdateList.addAll(producer.getDistributorIdList());
        }
        List<Integer> newList =
                distributorsToUpdateList.stream().distinct().collect(Collectors.toList());

        distributorSorted = new ArrayList<>(newList);
        distributorSorted.sort(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o1, o2);
                    }
                });

    }

    @Override
    public void notifyAllObservers() {
        for (Integer id : distributorSorted) {
            Distributor distributor = findDistributor(id);
            distributor.getProducerList().clear();

            for (Producer producer : inputData.getInitialData().getProducers()) {
                if (producer.getDistributorIdList().contains(id)) {
                    producer.getDistributorIdList().remove(id);
                    producer.decrementNumberDistributor();
                }
            }
            distributor.setInputData(inputData);
            distributor.update();
            distributor.computeInitialProductionCost();
        }
    }
}
