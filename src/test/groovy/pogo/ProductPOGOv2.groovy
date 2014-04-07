package pogo

class ProductPOGOv2 {

    Long id
    String name
    BigDecimal price

    private String companyName = "Arni Best Food"

    String getName() { name + ", $companyName" }

}