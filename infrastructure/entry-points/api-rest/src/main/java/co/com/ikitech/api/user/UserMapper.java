package co.com.ikitech.api.user;


import co.com.ikitech.model.user.user.User;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper
public interface UserMapper {

UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target="id", source = "id",
        defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    User toEntity(UserDTO dto);


    UserDTO toTransferObject(User user);
}