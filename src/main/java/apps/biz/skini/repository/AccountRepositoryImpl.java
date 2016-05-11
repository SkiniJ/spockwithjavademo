package apps.biz.skini.repository;

import apps.biz.skini.entity.Account;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * Created by skinibizapps on 5/11/16.
 */
public class AccountRepositoryImpl implements AccountRepository {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Account> findAccount(final long accountId) {
        Preconditions.checkArgument(accountId > 0, "accountId is invalid");
        return Optional.of(jdbcTemplate.queryForObject("SELECT FIRST_NAME, LAST_NAME, AGE FROM ACCOUNT WHERE ACCOUNT_ID = :accountId",
                new MapSqlParameterSource().addValue("accountId", accountId),
                (ResultSet result, int i) -> {
                    Account account = new Account();
                    account.setAccountId(accountId);
                    account.setFirstName(result.getString("FIRST_NAME"));
                    account.setLastName(result.getString("LAST_NAME"));
                    account.setAge(result.getInt("AGE"));
                    return account;
                }
        ));
    }
}
