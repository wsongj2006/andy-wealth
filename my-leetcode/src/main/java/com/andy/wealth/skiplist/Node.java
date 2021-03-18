package com.andy.wealth.skiplist;

public class Node {
    //实际值
    private Integer value;

    //下一个节点
    private Node next;

    //下一层的同一个节点
    private Node nextLayer;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNextLsayer() {
        return nextLayer;
    }

    public void setNextLayer(Node nextLayer) {
        this.nextLayer = nextLayer;
    }
}
