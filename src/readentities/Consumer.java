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

  public void addMonthlyIncome() {
    if (!isBankrupt) {
      this.initialBudget += this.monthlyIncome;
    }
  }

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

  public void decrementMonth() {
    monthsRemained--;
  }

  public boolean isNonPaid() {
    return nonPaid;
  }

  public void setNonPaid(boolean nonPaid) {
    this.nonPaid = nonPaid;
  }

  public int getMonthsRemained() {
    return monthsRemained;
  }

  public void setMonthsRemained(int monthsRemained) {
    this.monthsRemained = monthsRemained;
  }

  public int getDistributorID() {
    return distributorID;
  }

  public void setDistributorID(int distributorID) {
    this.distributorID = distributorID;
  }

  public long getContractPrice() {
    return contractPrice;
  }

  public void setContractPrice(long contractPrice) {
    this.contractPrice = contractPrice;
  }

  public long getOldMonthNonPaid() {
    return oldMonthNonPaid;
  }

  public void setOldMonthNonPaid(long oldMonthNonPaid) {
    this.oldMonthNonPaid = oldMonthNonPaid;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getInitialBudget() {
    return initialBudget;
  }

  public void setInitialBudget(int initialBudget) {
    this.initialBudget = initialBudget;
  }

  public int getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(int monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public boolean isBankrupt() {
    return isBankrupt;
  }

  public void setBankrupt(boolean bankrupt) {
    isBankrupt = bankrupt;
  }

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
