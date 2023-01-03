package com.ecommerce.userservice.service;

import com.ecommerce.userservice.VO.ResponseOrder;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.jpa.UserEntity;
import com.ecommerce.userservice.jpa.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    //    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        // mapper 설정
        // 딱 맞아 떨어지지 않으면 실행되지 않음.
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // userDto를 userEntity로 변경함.
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }

    @Override
    public UserDto getUserByUserID(String userid) {
        UserEntity userEntity = userRepository.findByUserId(userid);
        if(userEntity == null)
            throw new UsernameNotFoundException("User not found");

        // dto로 변환
        UserDto userDto = new ModelMapper().map(userEntity,UserDto.class);

        //아직 orders가 구현되어 있지 않기 때문에 임의의 List 생성
        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
