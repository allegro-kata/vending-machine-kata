package pojo;

import java.math.BigDecimal;

public class ProductPOJOv2 {

    private Long id;
    private String name;
    private BigDecimal price;

    private String companyName = "Arni Best Food";

    private ProductPOJOv2() {
    }

    public ProductPOJOv2(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name + ", " + companyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductPOGO(" +
                "id:" + id +
                "name:" + name +
                "price:" + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPOJOv2 that = (ProductPOJOv2) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public static class Builder {

        private ProductPOJOv2 productPOJOv2;

        public static Builder productPOJOv2() {
            return new Builder();
        }

        public Builder withId(Long id) {
            productPOJOv2.setId(id);
            return this;
        }

        public Builder withName(String name) {
            productPOJOv2.setName(name);
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            productPOJOv2.setPrice(price);
            return this;
        }

        public ProductPOJOv2 build() {
            return productPOJOv2;
        }
    }
}
