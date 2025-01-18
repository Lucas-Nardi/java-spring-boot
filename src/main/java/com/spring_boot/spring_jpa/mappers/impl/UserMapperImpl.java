package com.spring_boot.spring_jpa.mappers.impl;

import com.spring_boot.spring_jpa.domain.User;
import com.spring_boot.spring_jpa.dto.UserDTO;
import com.spring_boot.spring_jpa.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class UserMapperImpl implements Mapper<User, UserDTO> {

    private ModelMapper modalMapper;

    public UserMapperImpl(ModelMapper modalMapper) {
        this.modalMapper = modalMapper;
    }

    @Override
    public UserDTO mapTo(User user) {
        return modalMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapFrom(UserDTO userDTO) {
        return modalMapper.map(userDTO, User.class);
    }

    @Override
    public List<UserDTO> mapTo(List<User> users) {
        Type listType = new TypeToken<List<UserDTO>>() {
        }.getType();
        return modalMapper.map(users, listType);
    }

    @Override
    public List<User> mapFrom(List<UserDTO> usersDTO) {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        return modalMapper.map(usersDTO, listType);
    }
}
