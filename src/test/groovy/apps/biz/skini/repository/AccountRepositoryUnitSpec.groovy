package apps.biz.skini.repository

import org.springframework.dao.DataRetrievalFailureException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by skinibizapps on 5/10/16.
 */
class AccountRepositoryUnitSpec extends Specification {

    @Shared
    def jdbcTemplate = Mock(NamedParameterJdbcTemplate)

    @Subject
    def accountRepository

    def setup() {
        accountRepository = AccountRepository.newInstance(namedParameterJdbcTemplate : jdbcTemplate)
    }


    def "findAccount(#accountId) with an invalid accountId must throw an IllegalArgumentException"() {
        when:
            accountRepository.findAccount(accountId)
        then:
            0 * jdbcTemplate.query(*_)
        then:
            thrown(IllegalArgumentException)
        where:
            accountId << [0, Long.MIN_VALUE]
    }

    def "findAccount(#accountId) with an valid accountId must throw an DataRetrievalFailureException when query returns null"() {
        when:
        accountRepository.findAccount(accountId)
        then:
        1 * jdbcTemplate.query(*_) >> null
        then:
        thrown(DataRetrievalFailureException)
        where:
        accountId << [Long.MAX_VALUE, 1234567]
    }


    def "findAccount(#accountId) with an valid accountId must return an Optional<Account> object"() {
        when:
        accountRepository.findAccount(accountId)
        then:
        1 * jdbcTemplate.query(*_) >> null
        then:
        thrown(DataRetrievalFailureException)
        where:
        accountId || expectedValue
        1234567 || Account.newInstance(firstName: 'John', lastName: "Doe", age : 25, country : 'US')
        Long.MAX_VALUE || Account.newInstance(firstName: 'Joe', Smith: "Doe", age : 25, country : 'US')
    }

}
