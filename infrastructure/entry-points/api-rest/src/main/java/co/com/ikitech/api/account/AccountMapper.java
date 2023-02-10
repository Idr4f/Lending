package co.com.ikitech.api.account;


import co.com.ikitech.model.user.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target="id", source = "id",
        defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    Account toEntity(AccountDTO dto);


    AccountDTO toTransferObject(Account account);
}