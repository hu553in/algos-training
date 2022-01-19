package queue.consumer;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class EndlessSortedConsumerImpl<T> implements EndlessSortedConsumer<T> {
    private static final int INITIAL_CAPACITY = 16;

    private final Queue<T> queue;

    public EndlessSortedConsumerImpl() {
        queue = new PriorityBlockingQueue<>(INITIAL_CAPACITY);
    }

    public EndlessSortedConsumerImpl(final Comparator<T> comparator) {
        queue = new PriorityBlockingQueue<>(INITIAL_CAPACITY, comparator);
    }

    @Override
    public void offer(T element) {
        queue.offer(element);
    }

    @Override
    public List<T> toList() {
        return queue.stream().toList();
    }
}
