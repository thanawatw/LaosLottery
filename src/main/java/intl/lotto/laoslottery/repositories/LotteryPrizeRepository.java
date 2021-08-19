package intl.lotto.laoslottery.repositories;

import intl.lotto.laoslottery.entities.LotteryPrize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;


public interface LotteryPrizeRepository extends JpaRepository<LotteryPrize, Long> {
    Optional<LotteryPrize> findByDrawDate(Date drawDate);
}
