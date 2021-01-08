package readentities;

public class ProducerUpdates {
  private int id;
  private int energyPerDistributor;

  /** Method that returns Id */
  public int getId() {
    return id;
  }
  /** Method that sets Id */
  public void setId(int id) {
    this.id = id;
  }
  /** Method that returns energy per Distributor */
  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }
  /** Method that sets energy per Distributor */
  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "ProducerUpdates{" + "id=" + id + ", energyPerDistributor=" + energyPerDistributor + '}';
  }
}
