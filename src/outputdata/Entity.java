package outputdata;

public abstract class Entity {
  private int id;
  private int budget;
  private boolean isBankrupt;

  public Entity(final int id, final int budget, final boolean isBankrupt) {
    this.id = id;
    this.budget = budget;
    this.isBankrupt = isBankrupt;
  }

  /** Method that returns Bankrupt status */
  public boolean getisBankrupt() {
    return isBankrupt;
  }

  /** Method that returns budget */
  public int getBudget() {
    return budget;
  }

  /** Method that sets budget */
  public void setBudget(final int budget) {
    this.budget = budget;
  }

  /** Method that returns Id */
  public int getId() {
    return id;
  }

  /** Method that sets Id */
  public void setId(final int id) {
    this.id = id;
  }

  /** Method that sets Bankrupt */
  public void setBankrupt(final boolean bankrupt) {
    isBankrupt = bankrupt;
  }
}
