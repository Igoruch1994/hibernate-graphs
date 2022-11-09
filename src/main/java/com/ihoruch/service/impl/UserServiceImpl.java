package com.ihoruch.service.impl;

import com.ihoruch.dto.UserRegistrationDto;
import com.ihoruch.facade.AddressFacade;
import com.ihoruch.facade.CarFacade;
import com.ihoruch.mapper.UserMapper;
import com.ihoruch.model.Address;
import com.ihoruch.model.Car;
import com.ihoruch.model.User;
import com.ihoruch.model.UserCar;
import com.ihoruch.repository.UserRepository;
import com.ihoruch.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final CarFacade carFacade;
    private final UserMapper userMapper;
    private final AddressFacade addressFacade;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(final UserRegistrationDto userRegistrationDto) {
        final User user = userMapper.map(userRegistrationDto);
        final Car car = carFacade.getOrCreate(userRegistrationDto.getCar());
        final Address address = addressFacade.getOrCreate(userRegistrationDto.getAddress());
        final UserCar userCar = UserCar.builder().user(user).car(car).build();
        user.setAddress(address);
        user.setUserCars(Set.of(userCar));
        userRepository.save(user);
        //comment
        //comment 2
    }

}
