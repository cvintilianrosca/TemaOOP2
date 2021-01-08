package outputdata;

public class OutputConsumers {
  private int id;
  private boolean isBankrupt;
  private int budget;

  public OutputConsumers(int id, boolean isBankrupt, int budget) {
    this.id = id;
    this.budget = budget;
    this.isBankrupt = isBankrupt;
  }
  /** Method that returns consumer Id */
  public int getId() {
    return id;
  }
  /** Method that sets consumer ID */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns isBankrupt status */
  public boolean getisBankrupt() {
    return isBankrupt;
  }
  /** Method that sets isBankrupt status */
  public void setisBankrupt(boolean bankrupt) {
    isBankrupt = bankrupt;
  }
  /** Method that returns budget */
  public int getBudget() {
    return budget;
  }
  /** Method that sets budget */
  public void setBudget(int budget) {
    this.budget = budget;
  }
}
