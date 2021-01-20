package outputdata;

import readentities.Contracts;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public final class EntityFactory {

    private EntityFactory() {
    }

    /**
     * Method that creates the Object and returns it
     *
     */
    public static Entity createEntity(final EntityType entityType, final int id,
                                      final int initialBudget,
                                      final boolean isBankrupt,
                                      final ArrayList<Contracts> contractsList,
                                      final int energyNeededKW,
                                      final int contractCost,
                                      final EnergyChoiceStrategyType producerStrategy) {
        return switch (entityType) {
            case Consumer -> new OutputConsumers(id,  isBankrupt, initialBudget);
            case Distributor -> new OutputDistributors(id, energyNeededKW, contractCost,
                    initialBudget, producerStrategy, isBankrupt, contractsList);
        };
    }

    public enum EntityType {
        Consumer, Distributor
    }

}
