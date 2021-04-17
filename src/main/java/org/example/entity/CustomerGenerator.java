package org.example.entity;

import org.example.entity.СlientBank;

import java.util.Random;

/**
 * customer generator
 */
public class CustomerGenerator extends Thread {

    private final Bank bank;

    public CustomerGenerator(Bank bank){
        this.bank=bank;
    }

    @Override
    public void run(){
        while(true){
            СlientBank client=null;
            try{
                client=generateClient();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized СlientBank generateClient() throws InterruptedException{
        wait(200);
        int serviceTime=getRandomTime();
        int randomSum=getRandomSum();
        Operation operation=getOperation();
        СlientBank clientBank=new СlientBank(operation,randomSum,serviceTime);
        return clientBank;
    }


    /**
     * counting service time
     * @return service time
     */
    private int getRandomTime(){
        int MIN_TIME_INTERVAL=1100;
        int MAX_TIME_INTERVAL=4500;
        Random random=new Random();
        return random.nextInt(MAX_TIME_INTERVAL-MIN_TIME_INTERVAL)+MIN_TIME_INTERVAL;
    }

    /**
     * determining the random amount
     * @return random sum
     */
    private int getRandomSum(){
        int MIN_SUM=20;
        int MAX_SUM=220;
        Random random=new Random();
        return random.nextInt(MAX_SUM-MIN_SUM)+MIN_SUM;
    }

    /**
     * determining the operation
     * @return operation
     */
    private Operation getOperation(){
        Operation[] operations=Operation.values();
        Random random=new Random();
        return operations[random.nextInt((operations.length))];
    }
}
