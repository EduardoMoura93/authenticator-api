package br.com.authenticator.user.assembler;

import br.com.authenticator.common.AbstractMapper;
import br.com.authenticator.user.model.UserModel;
import br.com.authenticator.user.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAssemblerMapper extends AbstractMapper<UserModel, UserRequest> {
}
