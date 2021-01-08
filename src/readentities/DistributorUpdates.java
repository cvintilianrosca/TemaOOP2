package readentities;

public class DistributorUpdates {
    private int id;
    private int infrastructureCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    @Override
    public String toString() {
        return "DistributorUpdates{" +
                "id=" + id +
                ", infrastructureCost=" + infrastructureCost +
                '}';
    }
}
