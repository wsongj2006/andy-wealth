package com.andy.wealth.consumer;

import org.apache.kafka.clients.consumer.internals.PartitionAssignor;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Set;

public class MyPartitionAssignor implements PartitionAssignor {
    @Override
    public Subscription subscription(Set<String> topics) {
        return null;
    }

    @Override
    public Map<String, Assignment> assign(Cluster metadata, Map<String, Subscription> subscriptions) {
        return null;
    }

    @Override
    public void onAssignment(Assignment assignment) {

    }

    @Override
    public void onAssignment(Assignment assignment, int generation) {

    }

    @Override
    public String name() {
        return null;
    }
}
