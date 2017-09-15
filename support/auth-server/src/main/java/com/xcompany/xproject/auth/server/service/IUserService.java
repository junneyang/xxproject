package com.xcompany.xproject.auth.server.service;

import com.xcompany.xproject.auth.server.controller.DeviceAlreadyExistException;
import com.xcompany.xproject.auth.server.controller.UserAlreadyExistException;
import com.xcompany.xproject.auth.server.model.Device;
import com.xcompany.xproject.auth.server.model.User;
import com.xcompany.xproject.auth.server.serializers.DeviceDto;
import com.xcompany.xproject.auth.server.serializers.UserDto;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    void saveRegisteredUser(User user);

    Device registerNewUserDevice(DeviceDto deviceDto)  throws DeviceAlreadyExistException;
}
