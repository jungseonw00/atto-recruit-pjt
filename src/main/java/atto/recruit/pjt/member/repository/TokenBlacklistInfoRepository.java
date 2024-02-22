package atto.recruit.pjt.member.repository;

import atto.recruit.pjt.member.domain.entity.TokenBlacklistInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenBlacklistInfoRepository extends JpaRepository<TokenBlacklistInfo, Long> {

    @Query("select t from TokenBlacklistInfo t where t.accessToken = :accessToken")
    Optional<TokenBlacklistInfo> validateBlacklist(String accessToken);
}

