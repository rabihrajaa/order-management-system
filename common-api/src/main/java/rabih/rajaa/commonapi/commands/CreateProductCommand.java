package rabih.rajaa.commonapi.commands;


import lombok.Getter;

public class CreateProductCommand extends BaseCommand<String> {
    @Getter private String name;
    @Getter private double price;
    @Getter private int quantity;
    @Getter private String categoryId;

    public CreateProductCommand(String id, String name, double price, int quantity, String categoryId) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }
}