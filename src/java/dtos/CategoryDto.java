package dtos;

public class CategoryDto {
    private String name;
    private String description;
    private int resourceId;
    private int categoryId;

    public CategoryDto(String name, String description, int resourceId, int categoryId) {
        this.name = name;
        this.description = description;
        this.resourceId = resourceId;
        this.categoryId = categoryId;
    }

    public CategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    
}
