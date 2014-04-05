package pogo


class ProductPOGOv3 {

    Long id
    String name
    BigDecimal price

    String getName(Closure closure) { closure.call(name) }

}