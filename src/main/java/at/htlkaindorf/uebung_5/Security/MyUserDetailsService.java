package at.htlkaindorf.uebung_5.Security;

import at.htlkaindorf.uebung_5.entities.MyUser;
import at.htlkaindorf.uebung_5.repos.MyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;

    public MyUserDetailsService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserRepository.findByName(username);

        UserDetails userDetails = User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getName().trim().contains(" ") ? "ADMIN": "USER")
                .build();

        return userDetails;
    }
}
