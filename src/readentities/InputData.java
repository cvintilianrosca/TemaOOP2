package readentities;

import java.util.ArrayList;

public class InputData {
  private int numberOfTurns;
  private InitialData initialData;
  private ArrayList<MonthlyUpdates> monthlyUpdates;

  /** Method that returns number of turns */
  public int getNumberOfTurns() {
    return numberOfTurns;
  }
  /** Method that sets number of turns */
  public void setNumberOfTurns(int numberOfTurns) {
    this.numberOfTurns = numberOfTurns;
  }
  /** Method that returns initialData */
  public InitialData getInitialData() {
    return initialData;
  }
  /** Method that sets initialData */
  public void setInitialData(InitialData initialData) {
    this.initialData = initialData;
  }
  /** Method that returns monthlyUpdates */
  public ArrayList<MonthlyUpdates> getMonthlyUpdates() {
    return monthlyUpdates;
  }
  /** Method that sets monthlyUpdates */
  public void setMonthlyUpdates(ArrayList<MonthlyUpdates> monthlyUpdates) {
    this.monthlyUpdates = monthlyUpdates;
  }
}
