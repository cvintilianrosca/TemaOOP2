package readentities;

public class Contracts {
  private int consumerId;
  private long price;
  private int remainedContractMonths;

  public Contracts(int consumerId, long price, int remainedContractMonths) {
    this.consumerId = consumerId;
    this.price = price;
    this.remainedContractMonths = remainedContractMonths;
  }
  /** Method that returns Id */
  public int getConsumerId() {
    return consumerId;
  }
  /** Method that sets Id */
  public void setConsumerId(int consumerId) {
    this.consumerId = consumerId;
  }
  /** Method that returns price */
  public long getPrice() {
    return price;
  }
  /** Method that sets price */
  public void setPrice(long price) {
    this.price = price;
  }
  /** Method that returns remained contract months */
  public int getRemainedContractMonths() {
    return remainedContractMonths;
  }
  /** Method that sets remained contract months */
  public void setRemainedContractMonths(int remainedContractMonths) {
    this.remainedContractMonths = remainedContractMonths;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "Contracts{"
        + "consumerId="
        + consumerId
        + ", price="
        + price
        + ", remainedContractMonths="
        + remainedContractMonths
        + '}';
  }
}
