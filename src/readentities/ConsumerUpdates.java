package readentities;

public class ConsumerUpdates {
  private int id;
  private int initialBudget;
  private int monthlyIncome;

  /** Method that returns Id */
  public int getId() {
    return id;
  }
  /** Method that sets Id */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns Budget */
  public int getInitialBudget() {
    return initialBudget;
  }
  /** Method that sets Budget */
  public void setInitialBudget(int initialBudget) {
    this.initialBudget = initialBudget;
  }
  /** Method that returns monthly Income */
  public int getMonthlyIncome() {
    return monthlyIncome;
  }
  /** Method that sets monthly Income */
  public void setMonthlyIncome(int monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "ConsumerUpdates{"
        + "id="
        + id
        + ", initialBudget="
        + initialBudget
        + ", monthlyIncome="
        + monthlyIncome
        + '}';
  }
}
