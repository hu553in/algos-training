package queue.consumer;

import java.util.List;

public interface EndlessSortedConsumer<T> {
    void offer(final T element);

    List<T> toList();
}
