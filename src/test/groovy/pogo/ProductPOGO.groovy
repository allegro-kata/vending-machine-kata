package pogo

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false, includeNames = true)
class ProductPOGO {

    Long id
    String name
    BigDecimal price
}