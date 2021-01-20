package strategies;

import readentities.Distributor;
import readentities.InputData;

public final class EnergyChoiceStrategyFactory {
  /** Method that returns created strategy */
  public static EnergyChoiceStrategy createStrategy(
      EnergyChoiceStrategyType energyChoiceStrategyType,
      Distributor distributor,
      InputData inputData) {

    return switch (energyChoiceStrategyType) {
      case GREEN -> new GreenStrategy(distributor, inputData);
      case PRICE -> new PriceStrategy(distributor, inputData);
      case QUANTITY -> new QuantityStrategy(distributor, inputData);
    };
  }
  private EnergyChoiceStrategyFactory() {
  }
}
