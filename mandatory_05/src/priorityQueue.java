package com.company;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private long time = 32400000;

    List<QueueSpot> queue = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.customerWalksIn();
        main.processCustomers();

    }

    /**
     * Get the next prioritised customer..
     * @return  queueSpot object cointaining the customer and checkout time.
     */
    private QueueSpot getNextCustomerInQueue() {
        QueueSpot nextInLine = null;
        for (QueueSpot spot : queue) {
            if (nextInLine != null) {
                if (spot.time.getTime() <  nextInLine.time.getTime()) {
                    nextInLine = spot;
                }
            } else {
                nextInLine = spot;
            }
        }
        queue.remove(nextInLine);
        return nextInLine;
    }


    /**
     * Simulate some customers to enter our store and to our queue
     */
    private void customerWalksIn() {
        String[] names = {"Christian", "Erlend", "martin", "Hallgeir", "Even", "Sindre", "Moen", "Pelle", "Kåre", "Peter"};

        for (String name : names) {
            Customer customer = new Customer(name);

            // enter time
            Time enterStoreTime = Time.valueOf("12:00:00");
            enterStoreTime.setTime(time + new Random().nextInt(3000));
            System.out.println(enterStoreTime + ": Kunden " + customer.name + " kom inn i butikken");

            // checkout time
            Time checkoutTime = Time.valueOf("12:00:00");
            checkoutTime.setTime(time + new Random().nextInt(50000000));

            // add to queue
            queue.add(new QueueSpot(customer, checkoutTime));

        }
    }

    /**
     * Handle the customers to the priority until queue is empty.
     */
    private void processCustomers() {
        while (!queue.isEmpty()) {
            // Priority queue gives out a customer -> checkout the customer
            QueueSpot queueSpot = getNextCustomerInQueue();
            System.out.println(queueSpot.time+ ": " + queueSpot.customer.name + ", gikk til kassen: ");
            
            // customer has been dealt with, now print out some exit text with a timestamp..
            queueSpot.time.setTime(queueSpot.time.getTime() + new Random().nextInt(800000));
            System.out.println(queueSpot.time+ ": " +queueSpot.customer.name + ", gikk ut av butikken");
        }
    }

    /**
     * A class containing the customer
     */
    private static class Customer {
        String name;

        public Customer (String name) {
            this.name = name;
        }
    }

    /**
     * Class to hold the time and the customer, so we later can compare it in the queue
     */
    private static class QueueSpot{
        public Customer customer;
        public Time time;

        public QueueSpot(Customer customer, Time time) {
            this.customer = customer;
            this.time = time;
        }

    }
}
