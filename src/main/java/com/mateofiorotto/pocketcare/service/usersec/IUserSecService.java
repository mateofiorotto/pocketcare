package com.mateofiorotto.pocketcare.service.usersec;

public interface IUserSecService {
    public int usersCount();
    public String encriptPassword(String password);
}
