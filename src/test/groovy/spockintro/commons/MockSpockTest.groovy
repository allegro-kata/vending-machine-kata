package spockintro.commons

import spock.lang.Specification


class MockSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - you can verify interaction
     * - you can teach mock by double less-than sign
     * - mocks don't return default value for non primitive types
     */
    def "Should return name with correct prefix"() {
        given:
        NameDecorator nameDecorator = Mock()
        nameDecorator.getPrefix() >> "mr"

        Royals royals = new Royals(nameDecorator, "Harold")

        when:
        royals.getName()

        then:
        1 * nameDecorator.getPrefix()
    }
}
