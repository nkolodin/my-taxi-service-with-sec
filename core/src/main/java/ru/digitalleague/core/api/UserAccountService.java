package ru.digitalleague.core.api;

import ru.digitalleague.core.model.UserAccount;

public interface UserAccountService {
    UserAccount registration(UserAccount userAccount);
}
