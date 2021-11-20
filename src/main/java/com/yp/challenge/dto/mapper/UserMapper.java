package com.yp.challenge.dto.mapper;

import com.yp.challenge.dto.UserDto;
import com.yp.challenge.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserDto toUserView(User user);

}
