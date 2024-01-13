package com.devsu.microserviceclient.mapper;

import com.devsu.microserviceclient.dto.ClientDto;
import com.devsu.microserviceclient.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto toDTO(Client client);
    Client toClient(ClientDto clientDto);
}
