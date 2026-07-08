package com.mateofiorotto.pocketcare.service.usersec;

import com.mateofiorotto.pocketcare.entity.UserSec;

public interface IUserSecService {
    public int usersCount();
    public String encriptPassword(String password);
    public UserSec findAuthenticatedUser();
}
