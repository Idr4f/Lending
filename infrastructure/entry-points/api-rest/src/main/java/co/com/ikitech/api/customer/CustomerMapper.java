package co.com.ikitech.api.customer;


import co.com.ikitech.model.user.customer.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper
public interface CustomerMapper {

CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target="id", source = "id",
        defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    Customer toEntity(CustomerDTO dto);


    CustomerDTO toTransferObject(Customer user);
}