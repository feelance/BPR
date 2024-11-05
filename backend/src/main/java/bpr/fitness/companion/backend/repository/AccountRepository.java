package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
