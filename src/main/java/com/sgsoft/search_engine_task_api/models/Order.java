package com.sgsoft.search_engine_task_api.models;

public class Order implements Comparable<Order>{

    int count;

    Resource resource;

    public Order() {
    }

    public Order(int count, Resource resource) {
        this.count = count;
        this.resource = resource;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void incrementCount(int amount){
        this.count+=amount;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public int compareTo(Order o) {
        return this.count - o.getCount();
    }
}
