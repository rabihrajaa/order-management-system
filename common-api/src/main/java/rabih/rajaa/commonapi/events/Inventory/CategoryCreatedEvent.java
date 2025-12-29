package rabih.rajaa.commonapi.events.Inventory;

import lombok.Getter;

public class CategoryCreatedEvent extends BaseEvent<String> {
    @Getter private String name;
    @Getter private String description;

    public CategoryCreatedEvent(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}