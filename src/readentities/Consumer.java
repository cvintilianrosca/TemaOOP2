package readentities;

public class Consumer {
  private final double multiplier = 1.2;
  private int id;
  private int initialBudget;
  private int monthlyIncome;
  private boolean isBankrupt = false;
  private int distributorID = -1;
  private long contractPrice;
  private long oldMonthNonPaid;
  private int monthsRemained;
  private boolean nonPaid = false;

  /** Method that adds monthly income to budget */
  public void addMonthlyIncome() {
    if (!isBankrupt) {
      this.initialBudget += this.monthlyIncome;
    }
  }
  /** Method that decreases from budget monthly payment */
  public long pay() {
    if (!isBankrupt) {
      if (nonPaid) {
        if ((initialBudget - (Math.round(Math.floor(multiplier * oldMonthNonPaid)) + contractPrice))
            < 0) {
          isBankrupt = true;
          return 0;
        }
      }

      if (initialBudget - contractPrice < 0) {
        nonPaid = true;
        oldMonthNonPaid = contractPrice;
        return 0;
      }

      if (initialBudget - contractPrice >= 0 && !nonPaid) {
        initialBudget -= contractPrice;
      }
      return contractPrice;

    } else {
      return 0;
    }
  }
  /** Method that decrements number of months */
  public void decrementMonth() {
    monthsRemained--;
  }

  /** Method that returns non Paid status */
  public boolean isNonPaid() {
    return nonPaid;
  }

  /** Method that sets non Paid status */
  public void setNonPaid(boolean nonPaid) {
    this.nonPaid = nonPaid;
  }

  /** Method that returns remained contract months */
  public int getMonthsRemained() {
    return monthsRemained;
  }

  /** Method that sets remained contract months */
  public void setMonthsRemained(int monthsRemained) {
    this.monthsRemained = monthsRemained;
  }

  /** Method that returns DistributorId */
  public int getDistributorID() {
    return distributorID;
  }

  /** Method that sets distributor Id */
  public void setDistributorID(int distributorID) {
    this.distributorID = distributorID;
  }

  /** Method that returns Contract Price */
  public long getContractPrice() {
    return contractPrice;
  }

  /** Method that sets ContractPrice */
  public void setContractPrice(long contractPrice) {
    this.contractPrice = contractPrice;
  }

  /** Method that returns amount of non paid last months */
  public long getOldMonthNonPaid() {
    return oldMonthNonPaid;
  }

  /** Method that sets amount of non paid last months */
  public void setOldMonthNonPaid(long oldMonthNonPaid) {
    this.oldMonthNonPaid = oldMonthNonPaid;
  }

  /** Method that returns the id */
  public int getId() {
    return id;
  }

  /** Method that setts the id */
  public void setId(int id) {
    this.id = id;
  }

  /** Method that returns budget */
  public int getInitialBudget() {
    return initialBudget;
  }

  /** Method that set the budget */
  public void setInitialBudget(int initialBudget) {
    this.initialBudget = initialBudget;
  }

  /** Method that returns monthly income */
  public int getMonthlyIncome() {
    return monthlyIncome;
  }

  /** Method that sets monthly income */
  public void setMonthlyIncome(int monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  /** Method that returns bankrupt status */
  public boolean isBankrupt() {
    return isBankrupt;
  }

  /** Method that sets bankrupt status */
  public void setBankrupt(boolean bankrupt) {
    isBankrupt = bankrupt;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "Consumer{"
        + "id="
        + id
        + ", initialBudget="
        + initialBudget
        + ", monthlyIncome="
        + monthlyIncome
        + ", isBankrupt="
        + isBankrupt
        + ", distributorID="
        + distributorID
        + ", contractPrice="
        + contractPrice
        + ", oldMonthNonPaid="
        + oldMonthNonPaid
        + ", monthsRemained="
        + monthsRemained
        + ", nonPaid="
        + nonPaid
        + ", multiplier="
        + multiplier
        + '}';
  }
}
