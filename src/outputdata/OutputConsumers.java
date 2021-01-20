package outputdata;

public class OutputConsumers extends Entity {
  private int id;
  private boolean isBankrupt;
  private int budget;

  public OutputConsumers(int id, boolean isBankrupt, int budget) {
    super(id, budget, isBankrupt);
  }
  /** Method that returns consumer Id */
  public int getId() {
    return super.getId();
  }
  /** Method that sets consumer ID */
  public void setId(int id) {
    super.setId(id);
  }
  /** Method that returns isBankrupt status */
  public boolean getisBankrupt() {
    return super.getisBankrupt();
  }
  /** Method that sets isBankrupt status */
  public void setisBankrupt(boolean bankrupt) {
    super.setBankrupt(bankrupt);
  }
  /** Method that returns budget */
  public int getBudget() {
    return super.getBudget();
  }
  /** Method that sets budget */
  public void setBudget(int budget) {
    super.setBudget(budget);
  }
}
