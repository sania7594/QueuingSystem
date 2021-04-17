package org.example.entity;

import java.util.logging.Logger;

/**
 * class cash bank
 * author: ALeksandr Lozovoy
 */

public class СashBank {

    int sum;
    private Logger logger = Logger.getLogger(String.valueOf(СashBank.class));

    public СashBank(){

    }

    public СashBank(int sum){
        this.sum=sum;
    }
    /*
    * deposit replenishment
    * */
    public synchronized void plusMoney(int money){
        this.sum +=money;
        logger.info("deposit topped up");
    }

    public synchronized  void minusMoney(int money){
        if (this.sum-money<0){
            logger.warning("there are no money in the bank");
        }
        else{
            this.sum -=money;
            logger.info("money withdrawn");
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
