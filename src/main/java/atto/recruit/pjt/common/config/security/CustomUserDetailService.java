package atto.recruit.pjt.common.config.security;

import atto.recruit.pjt.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        log.info("roles => {}", roles);

        return memberRepository.findByMemberId(memberId)
            .map(m -> new AccountContext(m.getMemberId(), m.getPassword(), roles))
            .orElseThrow(() -> new UsernameNotFoundException("not found user"));
    }

    public static class AccountContext extends User {
        public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }
    }
}
