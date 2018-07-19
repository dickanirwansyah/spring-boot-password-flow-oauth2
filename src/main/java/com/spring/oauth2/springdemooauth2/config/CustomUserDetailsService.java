package com.spring.oauth2.springdemooauth2.config;

import com.spring.oauth2.springdemooauth2.entity.Account;
import com.spring.oauth2.springdemooauth2.entity.Privilege;
import com.spring.oauth2.springdemooauth2.entity.Role;
import com.spring.oauth2.springdemooauth2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<Account> accountByUsername = accountRepository.findByUsername(username);
        if (!accountByUsername.isPresent()){
            throw new UsernameNotFoundException("Login failed because username "
                    +accountByUsername+" " + "is not found !");

        }
        final Account account = accountByUsername.get();
        if (account.getRoles() == null || account.getRoles().isEmpty()){
            throw new UsernameNotFoundException("authorize request is failed ");
        }
        return new User(
                account.getUsername(),
                account.getPassword(),
                account.isEnabled(),
                true,
                true,
                true,
                getAuthorities(account.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles){
        return getGrantedAuthorities(getPrivilege(roles));
    }

    private List<String> getPrivilege(final Collection<Role> roles){
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> colllection = new ArrayList<Privilege>();
        for (final Role role : roles){
            colllection.addAll(role.getPrivileges());
        }

        for (final Privilege item: colllection){
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges){
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege: privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
