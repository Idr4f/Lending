package co.com.ikitech.api.credit;


import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.credit.Deposit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditMapper {

CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target="id", source = "id",
        defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    Credit toEntityCredit(CreditDTO dto);


    CreditDTO toTransferObjectCredit(Credit credit);

    Deposit toTransform(DepositDTO dto);

}