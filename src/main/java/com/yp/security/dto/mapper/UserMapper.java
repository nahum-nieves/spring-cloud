package com.yp.security.dto.mapper;

import com.yp.security.dto.UserDto;
import com.yp.security.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserDto toUserView(User user);

}
