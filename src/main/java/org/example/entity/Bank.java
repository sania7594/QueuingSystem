package org.example.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.example.entity.СlientBank;

public class Bank {
    private final int PEOPLE_COUNT=5;

    private final List<LinkedList<СlientBank>> clientBank=new ArrayList<>(PEOPLE_COUNT);
    private final List<Operator> operators=new ArrayList<>(PEOPLE_COUNT);

    public Bank(){
        for (int i=0;i<this.PEOPLE_COUNT;i++){
            clientBank.add(new LinkedList<>());
        }

        for (LinkedList<СlientBank> clientBankNew: clientBank){
            Operator operator=new Operator(clientBankNew);
            operators.add(operator);
        }

        for (Operator operator:operators){
            Thread thread=new Thread(operator);
            thread.start();
        }

        CustomerGenerator customerGenerator=new CustomerGenerator(this);
        Thread thread=new Thread(customerGenerator);
        thread.start();
    }

    public void addClient(СlientBank clientBankT){
        int queueSize=100;
        LinkedList<СlientBank> queue=new LinkedList<>();
        int a=0;
        for (int i=0;i<clientBank.size();i++) {
            if (clientBank.get(i).size() < queueSize) {
                queue = clientBank.get(i);
                queueSize = queue.size();
                a = 1;
            }
        }

            if (queue.isEmpty()) {
                synchronized (operators.get(a)){
                    queue.add(clientBankT);
                    operators.get(a).notify();
                }
            }
            else
                queue.add(clientBankT);
    }
}
