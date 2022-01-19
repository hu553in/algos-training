package queue.producer;

public interface EndlessSortedProducer<T> {
    T poll();
}
