package dtos;

public class ResourceDto {
    private int id;

    
    private String name;
    private String color;
    private int quantity;
    private int usingDate;
    private String description;
    private String categoryName;
    private String categoryDescription;

    public ResourceDto() {
    }

    public ResourceDto(int id,String name, String color, int quantity, int usingDate, String description, String categoryName, String categoryDescription) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.usingDate = usingDate;
        this.description = description;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(int usingDate) {
        this.usingDate = usingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
    
    
}
