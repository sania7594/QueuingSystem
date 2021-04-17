package org.example.entity;

import java.util.LinkedList;
import java.util.logging.Logger;

import org.example.entity.СlientBank;
import org.example.entity.СashBank;


/**
 * class operator bank
 * author: ALeksandr Lozovoy
 */

public class Operator extends Thread {

    private LinkedList<СlientBank> queue;
    private СashBank cashBank;
    private final Logger logger = Logger.getLogger(String.valueOf(Operator.class));

    public Operator(LinkedList<СlientBank> queue){
        this.queue=queue;
        this.cashBank=new СashBank();
    }

    /**  insert money
     * @param money money
     */
    public synchronized void sumAdd(int money){
        this.cashBank.plusMoney(money);
    }


    /** take off money
     * @param money money
     */
    public synchronized  void minusSum(int money){
        this.cashBank.minusMoney(money);
    }

    @Override
    public void run(){
        while (true){
            try {
                clientOperation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /** client operation
     * @throws InterruptedException
     */
    private synchronized void clientOperation() throws InterruptedException{
        sleep();
        СlientBank clientBank=queue.getFirst();
        try{
            wait(clientBank.getServiceTime());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if (clientBank.getOperation().equals(Operation.TAKEOFF)){
            this.minusSum(clientBank.getSum());
            logger.info("The client withdrew the specified amount"+clientBank.getSum());
        }
        else if (clientBank.getOperation().equals(Operation.PUT)){
            this.sumAdd(clientBank.getSum());
            logger.info("The client deposited the specified amount"+clientBank.getSum());
        }
        queue.removeFirst();
    }

    /** expect if empty
     * @throws InterruptedException
     */
    private void sleep() throws InterruptedException{
        if (queue.isEmpty()){
            wait();
        }
    }

    public LinkedList<СlientBank> getQueue() {
        return queue;
    }

    public void setQueue(LinkedList<СlientBank> queue) {
        this.queue = queue;
    }

}
