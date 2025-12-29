package rabih.rajaa.commonapi.events.Inventory;

import lombok.Getter;

import java.time.Instant;

public abstract class BaseEvent<T> {
    @Getter
    private T id;
    @Getter
    private Instant timestamp;

    public BaseEvent(T id) {
        this.id = id;
        this.timestamp = Instant.now();
    }
}



