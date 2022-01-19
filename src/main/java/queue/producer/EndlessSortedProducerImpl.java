package queue.producer;

import java.util.concurrent.atomic.AtomicLong;

public class EndlessSortedProducerImpl implements EndlessSortedProducer<Long> {
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Long poll() {
        return counter.getAndIncrement();
    }
}
