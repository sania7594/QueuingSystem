package org.example.entity;

/**
 * class clien bank
 * author: ALeksandr Lozovoy
 */

public class СlientBank {
   private Operation operation;
   private int sum;
   private long serviceTime;

    public СlientBank(Operation operation, int sum, int serviceTime) {
        this.operation = operation;
        this.sum = sum;
        this.serviceTime = serviceTime;
    }

    public СlientBank(){}

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }
}
