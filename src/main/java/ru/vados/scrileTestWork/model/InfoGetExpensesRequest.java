package ru.vados.scrileTestWork.model;

public class InfoGetExpensesRequest {
    private int id;
    private int minSum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinSum() {
        return minSum;
    }

    public void setMinSum(int minSum) {
        this.minSum = minSum;
    }
}
