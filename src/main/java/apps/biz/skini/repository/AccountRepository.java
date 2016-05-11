package apps.biz.skini.repository;

import apps.biz.skini.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by skinibizapps on 5/11/16.
 */
@Repository
public interface AccountRepository {
    public Optional<Account> findAccount(final long accountId);
}
