package readentities;

import java.util.ArrayList;

public class MonthlyStats {
  private int month;
  private ArrayList<Integer> distributorsIds = new ArrayList<>();

  /** Method that returns month */
  public int getMonth() {
    return month;
  }
  /** Method that sets month */
  public void setMonth(int month) {
    this.month = month;
  }
  /** Method that returns distributors List */
  public ArrayList<Integer> getDistributorsIds() {
    return distributorsIds;
  }
  /** Method that sets distributors List */
  public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
    this.distributorsIds = distributorsIds;
  }
  /** Method toString() */
  @Override
  public String toString() {
    return "MonthlyStats{" + "month=" + month + ", distributorsIds=" + distributorsIds + '}';
  }
}
