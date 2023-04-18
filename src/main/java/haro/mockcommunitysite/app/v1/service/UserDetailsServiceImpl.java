package haro.mockcommunitysite.app.v1.service;


import haro.mockcommunitysite.app.v1.domain.Member;
import haro.mockcommunitysite.app.v1.domain.UserDetailsImpl;
import haro.mockcommunitysite.app.v1.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String displayMemberId) throws UsernameNotFoundException {
    Optional<Member> member = memberRepository.findByDisplayMemberId(displayMemberId);
    return member
        .map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException("nickname not found"));
  }
}
