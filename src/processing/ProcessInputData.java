package processing;

import readentities.*;
import strategies.EnergyChoiceStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessInputData {
    InputData inputData;
    public ProcessInputData(InputData inputData){
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





    public void relateConsumerWithDistributor(Consumer consumer){
        Distributor distributorBestPrice = findBestDistributorPrice();
        consumer.setDistributorID(distributorBestPrice.getId());
        consumer.setContractPrice(distributorBestPrice.getContractPrice());
        consumer.setMonthsRemained(distributorBestPrice.getContractLength());
        distributorBestPrice.getContracts().add(new Contracts(consumer.getId(), distributorBestPrice.getContractPrice(), distributorBestPrice.getContractLength()));
    }

    public void decrementContractMonth(final Distributor distributors, final int consumerID) {
        for (Contracts contract : distributors.getContracts()) {
            if (contract.getConsumerId() == consumerID) {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            }
        }
    }

    public void firstMonth(){
        //DIstributorii isi aleg producatorii
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
            EnergyChoiceStrategyFactory.createStrategy(distributor.getProducerStrategy(), distributor, inputData).applyStrategy();
             distributor.computeInitialProductionCost();
        }

   // Distributorii calculeaza preturile
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
            distributor.computeProfit();
            distributor.computeContractPrice();
        }

        // Consumatorii isi aleg distribuitorii
        for (Consumer consumer : inputData.getInitialData().getConsumers()) {
            if (consumer.getDistributorID()==-1){
                relateConsumerWithDistributor(consumer);
            }
        }

        //Consumatorii platesc

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


    public Producer findProducerById(int producerId){
        for (Producer producer :inputData.getInitialData().getProducers()) {
            if (producer.getId()==producerId){
                return producer;
            }
        }
        return null;
    }

 public void makeUpdates(MonthlyUpdates monthlyUpdate){
        // Adaug noii consumatori
     for (ConsumerUpdates consumerUpdate : monthlyUpdate.getNewConsumers()) {
         Consumer consumer = new Consumer();
         consumer.setId(consumerUpdate.getId());
         consumer.setInitialBudget(consumerUpdate.getInitialBudget());
         consumer.setMonthlyIncome(consumerUpdate.getMonthlyIncome());
         inputData.getInitialData().getConsumers().add(consumer);
     }

     // Am modificat costul infrastructurii
     for (DistributorUpdates distributorUpdate : monthlyUpdate.getDistributorChanges()) {
         findDistributor(distributorUpdate.getId()).setInitialInfrastructureCost(distributorUpdate.getInfrastructureCost());
     }

 }

 public boolean makeProducerUpdates(MonthlyUpdates monthlyUpdate){
        boolean flag=false;
     if (monthlyUpdate.getProducerChanges().size() != 0){
         flag=true;
         for (ProducerUpdates producerUpdates: monthlyUpdate.getProducerChanges()) {
             findProducerById(producerUpdates.getId()).setEnergyPerDistributor(producerUpdates.getEnergyPerDistributor());
         }
         // toti distribuitorii care sunt in lista producatorilor care au primit update trebuie notificati si sa refaca
         // strategia din nou ???
     }
     return flag;
 }

    public void startMonthSimulation(){
      int monthNumber=1;

        for (MonthlyUpdates monthlyUpdate : inputData.getMonthlyUpdates()) {

            // la inceputul lunii se fac update-urile la toti ( consumatori, producatori, distribuitori)
           makeUpdates(monthlyUpdate);

//           if(updateFlag){
//
//               for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                   distributor.getProducerList().clear();
//               }
//               for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                   EnergyChoiceStrategyFactory.createStrategy(distributor.getProducerStrategy(), distributor, inputData).applyStrategy();
//                   distributor.computeInitialProductionCost();
//               }
//           }

            // TODO: Aplic strategia daca este necesara


            // in fiecare luna producatorii trebuie sa aiba un distribuitorii in lista lor de monthly stats
//            for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                for (Producer producer : distributor.getProducerList()) {
//                    MonthlyStats monthlyStats = new MonthlyStats();
//                    monthlyStats.setMonth(monthNumber);
//                    monthlyStats.getDistributorsIds().add(distributor.getId());
//                    producer.getMonthlyStats().add(monthlyStats);
//                }
//            }


//            for (Producer producer : inputData.getInitialData().getProducers()) {
//                boolean monthlyFlag=false;
//                for (MonthlyStats monthlystats: producer.getMonthlyStats()) {
//                    if (monthlystats.getMonth() == monthNumber) {
//                        monthlyFlag = true;
//                        break;
//                    }
//                }
//                if (!monthlyFlag){
////                    System.out.println(producer.getId());
//                    MonthlyStats monthlyStats = new MonthlyStats();
//                    monthlyStats.setMonth(monthNumber);
//                    producer.getMonthlyStats().add(monthlyStats);
//                }
//            }
            // dupa idee partea cu monthlyStats e facuta


            // distribuitorii au calculat noile preturi in fiecare luna
            for (Distributor distributor: inputData.getInitialData().getDistributors()) {
                distributor.computeInitialProductionCost();
                distributor.computeProfit();
                distributor.computeContractPrice();
            }


            //Consumatorii trebuie sa isi aleaga distribuitorul daca nu au unul deja ales sau daca
            // este ultima luna de contract sau daca nu au dat bankrupt
            for (Consumer consumer : inputData.getInitialData().getConsumers()) {

                if (consumer.isBankrupt()){
                    Distributor distributor = findDistributor(consumer.getDistributorID());
                    Contracts saveContracts=null;
                    for (Contracts contracts : distributor.getContracts()) {
                        if (contracts.getConsumerId() == consumer.getId()){
                            saveContracts=contracts;
                            break;
                        }
                    }
                    distributor.getContracts().remove(saveContracts);
                }

                // daca consumatorii sunt noi adaugati prin update
                if (consumer.getDistributorID()==-1){
                    relateConsumerWithDistributor(consumer);
                }

                // cazul in care raman 0 luni si nu mai trebuie sters contractul
                // dar trebuie sa updatam lunile ramase in contract
                if (consumer.getMonthsRemained()==0 && !consumer.isBankrupt()){
                    Distributor bestPriceDistributor = findBestDistributorPrice();
                    if (bestPriceDistributor == findDistributor(consumer.getDistributorID())){
                        consumer.setDistributorID(bestPriceDistributor.getId());
                        consumer.setContractPrice(bestPriceDistributor.getContractPrice());
                        consumer.setMonthsRemained(bestPriceDistributor.getContractLength());
                        Contracts saveContract=null;
                        for (Contracts contract : bestPriceDistributor.getContracts()) {
                            if (contract.getConsumerId() == consumer.getId()){
                                saveContract=contract;
                                break;
                            }
                        }

                        bestPriceDistributor.getContracts().remove(saveContract);
                        bestPriceDistributor.getContracts().add(new Contracts(consumer.getId(), bestPriceDistributor.getContractPrice()
                        , bestPriceDistributor.getContractLength()));
                    } else{
                        // gasesc distribuitorul vechi al consumatorului
                        Distributor distributor = findDistributor(consumer.getDistributorID());
                        // gasesc contractul consumerului la distribuitor si il sterg
                        Contracts saveContract=null;
                        for (Contracts contract: distributor.getContracts()) {
                            if (contract.getConsumerId() == consumer.getId()){
                                saveContract=contract;
                                break;
                            }
                        }
                        distributor.getContracts().remove(saveContract);
                        consumer.setDistributorID(bestPriceDistributor.getId());
                        consumer.setContractPrice(bestPriceDistributor.getContractPrice());
                        consumer.setMonthsRemained(bestPriceDistributor.getContractLength());
                        bestPriceDistributor.getContracts().add(new Contracts(consumer.getId(),
                                bestPriceDistributor.getContractPrice(), bestPriceDistributor.getContractLength()));

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
//            for (Consumer consumer : inputData.getInitialData().getConsumers()) {
//                System.out.println(consumer.getInitialBudget());
//                System.out.println(consumer.getContractPrice());
//            }
//
//            System.out.println();
//            for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                System.out.println(distributor.getContractPrice());
//                System.out.println(distributor.getInitialInfrastructureCost());
//                System.out.println(distributor.getInitialProductionCost());
//                System.out.println(distributor.getProducerList().get(0).getEnergyPerDistributor());
////                System.out.println(distributor.getProfit());
//            }
////            System.out.println();


//            for (Producer producer : inputData.getInitialData().getProducers()) {
//                System.out.println(producer.getDistributorIdList());
//            }
//            System.out.println();

            List<Integer> distributorsToUpdateList = new ArrayList<>();

            for (ProducerUpdates producerUpdates : monthlyUpdate.getProducerChanges()) {
                Producer producer = findProducerById(producerUpdates.getId());
                distributorsToUpdateList.addAll(producer.getDistributorIdList());
            }
//            for (Producer producer : inputData.getInitialData().getProducers()) {
//                distributorsToUpdateList.addAll(producer.getDistributorIdList());
//            }

            List<Integer> newList = distributorsToUpdateList.stream()
                    .distinct()
                    .collect(Collectors.toList());

            ArrayList<Integer> distributorSorted = new ArrayList<>(newList);

            distributorSorted.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1, o2);
                }
            });


            if(makeProducerUpdates(monthlyUpdate)){

//                for (Integer id : distributorSorted) {
//                    Distributor distributor = findDistributor(id);
//                    distributor.getProducerList().clear();
//                }
//
//                for (Producer producer : inputData.getInitialData().getProducers()) {
//                    producer.setNumberDistributors(0);
//                    producer.getDistributorIdList().clear();
//
//                }
//
//                for (Integer id : distributorSorted) {
//                    Distributor distributor = findDistributor(id);
//                    EnergyChoiceStrategyFactory.createStrategy(distributor.getProducerStrategy(), distributor, inputData).applyStrategy();
//                    distributor.computeInitialProductionCost();
//                }

                System.out.println(distributorSorted);
                for (Integer id : distributorSorted) {
                    Distributor distributor = findDistributor(id);
                    distributor.getProducerList().clear();

                    for (Producer producer : inputData.getInitialData().getProducers()) {
                        if (producer.getDistributorIdList().contains(id)){
                            producer.getDistributorIdList().remove(id);
                            producer.decrementNumberDistributor();
                        }
                    }
                    EnergyChoiceStrategyFactory.createStrategy(distributor.getProducerStrategy(), distributor, inputData).applyStrategy();
                    distributor.computeInitialProductionCost();

                }

            }


//            for (Producer producer : inputData.getInitialData().getProducers()) {
//                if (producer.getId()==4){
//                    System.out.println(producer.getDistributorIdList());
//                }
//            }
//
//            System.out.println();
//            for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                System.out.println(distributor.getProducerList().size());
//            }
//            System.out.println();

            for (Distributor distributor : inputData.getInitialData().getDistributors()) {
                for (Producer producer : distributor.getProducerList()) {
                    boolean monthFlag = false;
                    for (MonthlyStats stats : producer.getMonthlyStats()) {
                        if (stats.getMonth()==monthNumber){
                            stats.getDistributorsIds().add(distributor.getId());
                            monthFlag=true;
                        }
                    }
                    if (monthFlag==false) {
                        MonthlyStats monthlyStats = new MonthlyStats();
                        monthlyStats.setMonth(monthNumber);
                        monthlyStats.getDistributorsIds().add(distributor.getId());
                        producer.getMonthlyStats().add(monthlyStats);
                    }

                }

            }


            for (Producer producer : inputData.getInitialData().getProducers()) {
                boolean monthlyFlag=false;
                for (MonthlyStats monthlystats: producer.getMonthlyStats()) {
                    if (monthlystats.getMonth() == monthNumber) {
                        monthlyFlag = true;
                        break;
                    }
                }
                if (!monthlyFlag){
//                    System.out.println(producer.getId());
                    MonthlyStats monthlyStats = new MonthlyStats();
                    monthlyStats.setMonth(monthNumber);
                    producer.getMonthlyStats().add(monthlyStats);
                }
            }

//            for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                if (distributor.getId()==4){
//                    System.out.println(distributor.getContracts());
//                }
//            }
//            System.out.println();
            monthNumber++;


            for (Producer producer : inputData.getInitialData().getProducers()) {
                System.out.println(producer.getDistributorIdList());
            }
            System.out.println();
//            for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//                if (distributor.getId()==0){
//                    System.out.println(distributor.getContractPrice());
//                }
//            }
//            System.out.println();
        }

    }


    public void start(){
        firstMonth();
        startMonthSimulation();

        for (Consumer consumer : inputData.getInitialData().getConsumers()) {
//            System.out.println(consumer);
        }
        for (Distributor distributor : inputData.getInitialData().getDistributors()) {
//            System.out.println(distributor);
        }

        for (Producer producer : inputData.getInitialData().getProducers()) {
//            System.out.println(producer);
        }
    }

    public InputData getInputData() {
        return inputData;
    }
}
