package spockintro.commons

import spock.lang.Specification


class MockSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - you can verify interaction
     * - you can teach mock by double less-than sign
     * - mocks don't return default value for non primitive types
     */
    def "should verify interaction with collaborator"() {
        given:
        NameDecorator nameDecorator = Mock()
        nameDecorator.getPrefix() >> "mr"

        Royals royals = new RoyalsDecorated(nameDecorator, "Harold")

        when:
        royals.getName()

        then:
        1 * nameDecorator.getPrefix()
    }
}
