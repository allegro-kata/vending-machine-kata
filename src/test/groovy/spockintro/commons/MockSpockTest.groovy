package spockintro.commons

import spock.lang.Specification


class MockSpockTest extends Specification {

    def "mocking"() {
        given:
        Repository repository = Mock()
//      another syntax:  def repository = Mock(Repository)
        Service service = new Service(repository: repository)

        when:
        service.findAll()

        then:
        1 * repository.findAll()
    }

    class Service {

        Repository repository

        void findAll() {
            repository.findAll()
        }
    }

    class Repository {
        void findAll() { }
    }
}
