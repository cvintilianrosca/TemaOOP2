package outputdata;

public class OutputConsumers {
    private int id;
    private boolean isBankrupt;
    private int budget;

    public OutputConsumers(int id, boolean isBankrupt, int budget){
        this.id=id;
        this.budget=budget;
        this.isBankrupt=isBankrupt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getisBankrupt() {
        return isBankrupt;
    }

    public void setisBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
