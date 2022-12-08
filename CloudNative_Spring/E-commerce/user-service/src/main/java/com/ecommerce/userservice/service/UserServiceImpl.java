package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.jpa.UserEntity;
import com.ecommerce.userservice.jpa.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        // mapper 설정
        // 딱 맞아 떨어지지 않으면 실행되지 않음.
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // userDto를 userEntity로 변경함.
        UserEntity userEntity = mapper.map(userDto,UserEntity.class);
        userEntity.setEncryptedPassword("encrypted_password");

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity,UserDto.class);

        return returnUserDto;
    }
}
