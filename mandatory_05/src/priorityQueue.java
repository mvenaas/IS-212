package com.company;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private long time = 32400000;

    List<QueueSpot> queue = new ArrayList<>();

    public QueueSpot getNextCustomerInQueue() {
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


    private void customerWalksIn() {
        String[] names = {"Christian", "Erlend", "martin", "Hallgeir", "Even", "Sindre", "Moen", "Pelle", "Kåre", "Peter"};

        for (String name : names) {
            Customer customer = new Customer(name);
            Time tidIButikken = Time.valueOf("12:00:00");
            tidIButikken.setTime(time);
            Random rnd = new Random();
            tidIButikken.setTime(time + rnd.nextInt(3000));
            Time tidspunkt = Time.valueOf("12:00:00");
            tidspunkt.setTime(time + new Random().nextInt(50000000));
            queue.add(new QueueSpot(customer, tidspunkt));
            System.out.println(tidIButikken + ": Kunden " + customer.name + " kom inn i butikken");
        }


    }

    public static void main(String[] args) {
        Main main = new Main();




        main.customerWalksIn();

        //main.time += 10000;
        while (!main.queue.isEmpty()) {
            try {
                QueueSpot queueSpot = main.getNextCustomerInQueue();
                System.out.println(queueSpot.time+ ": " + queueSpot.customer.name + ", gikk til kassen: ");


                queueSpot.time.setTime(queueSpot.time.getTime() + new Random().nextInt(800000));
                System.out.println(queueSpot.time+ ": " +queueSpot.customer.name + ", gikk ut av butikken");


            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static class Customer {
        String name;

        public Customer (String name) {
            this.name = name;
        }
    }

    public static class QueueSpot{
        public Customer customer;
        public Time time;

        public QueueSpot(Customer customer, Time time) {
            this.customer = customer;
            this.time = time;
        }

    }
}
