package readentities;

public class DistributorUpdates {
  private int id;
  private int infrastructureCost;

  /** Method that returns Id */
  public int getId() {
    return id;
  }
  /** Method that sets Id */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns infrastructure cost */
  public int getInfrastructureCost() {
    return infrastructureCost;
  }
  /** Method that sets infrastructure cost */
  public void setInfrastructureCost(int infrastructureCost) {
    this.infrastructureCost = infrastructureCost;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "DistributorUpdates{" + "id=" + id + ", infrastructureCost=" + infrastructureCost + '}';
  }
}
