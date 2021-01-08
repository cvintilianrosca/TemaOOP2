package strategies;

import readentities.Distributor;
import readentities.InputData;

public class EnergyChoiceStrategyFactory {
  /** Method that returns created strategy */
  public static EnergyChoiceStrategy createStrategy(
      EnergyChoiceStrategyType energyChoiceStrategyType,
      Distributor distributor,
      InputData inputData) {

    switch (energyChoiceStrategyType) {
      case GREEN:
        return new GreenStrategy(distributor, inputData);
      case PRICE:
        return new PriceStrategy(distributor, inputData);
      case QUANTITY:
        return new QuantityStrategy(distributor, inputData);
    }
    return null;
  }
}
