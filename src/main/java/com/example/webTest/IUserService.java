package com.example.webTest;

import com.example.webTest.dto.UserDto;
import com.example.webTest.model.User;

public interface IUserService {
    User registerNewUserAccount(UserDto userDto);
}
