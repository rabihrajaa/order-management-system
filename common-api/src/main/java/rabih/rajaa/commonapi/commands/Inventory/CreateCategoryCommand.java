package rabih.rajaa.commonapi.commands.Inventory;

import lombok.Getter;

public class CreateCategoryCommand extends BaseCommand<String> {
    @Getter private String name;
    @Getter private String description;

    public CreateCategoryCommand(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
