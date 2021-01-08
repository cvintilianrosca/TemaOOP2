package readentities;

import java.util.ArrayList;

public class MonthlyStats {
    private int month;
    private ArrayList<Integer> distributorsIds= new ArrayList<>();

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

    @Override
    public String toString() {
        return "MonthlyStats{" +
                "month=" + month +
                ", distributorsIds=" + distributorsIds +
                '}';
    }
}
