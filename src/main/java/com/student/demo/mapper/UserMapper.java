package com.student.demo.mapper;

import com.student.demo.dto.LoginReqDto;
import com.student.demo.dto.UserReqDto;
import com.student.demo.dto.UserResDto;
import com.student.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User LoginReqDtoToUser(LoginReqDto loginReqDto);

    User toUser(UserReqDto userReqDto);

    UserResDto toDto(User userDetails);
}
