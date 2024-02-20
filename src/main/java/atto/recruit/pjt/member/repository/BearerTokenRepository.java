package atto.recruit.pjt.member.repository;

import atto.recruit.pjt.member.domain.entity.BearerToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BearerTokenRepository extends JpaRepository<BearerToken, Long> {
    Optional<BearerToken> findByRefreshToken(String refreshToken);

    @Modifying
    @Query(value = "delete from BearerToken rt where rt.memberId = :memberId")
    void deleteAllByMemberId(String memberId);
}
