package readentities;

public class ProducerUpdates {
    private int id;
    private int energyPerDistributor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    @Override
    public String toString() {
        return "ProducerUpdates{" +
                "id=" + id +
                ", energyPerDistributor=" + energyPerDistributor +
                '}';
    }
}
