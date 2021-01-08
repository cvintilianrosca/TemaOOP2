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

  public int getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(int consumerId) {
    this.consumerId = consumerId;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public int getRemainedContractMonths() {
    return remainedContractMonths;
  }

  public void setRemainedContractMonths(int remainedContractMonths) {
    this.remainedContractMonths = remainedContractMonths;
  }

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
