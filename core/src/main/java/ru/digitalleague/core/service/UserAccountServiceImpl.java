package ru.digitalleague.core.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.api.UserAccountService;
import ru.digitalleague.core.model.UserAccount;
import ru.digitalleague.core.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount registration(UserAccount userAccount) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userAccountRepository.findByLogin(s);
    }
}
