package co.com.ikitech.api.customer;


import co.com.ikitech.model.user.customer.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper
public interface CustomerMapper {

CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target="id", source = "id",
        defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    Customer toEntityUser(CustomerDTO dto);


    CustomerDTO toTransferObject(Customer customer);
}