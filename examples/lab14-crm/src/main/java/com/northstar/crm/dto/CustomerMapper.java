package com.northstar.crm.mapper;

import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class CustomerMapper {
    private CustomerMapper() {}

    public static Customer toEntity(CustomerRequestDTO req) {
        // Adapt constructor args to YOUR Customer (phone, timestamps, etc.)
        return new Customer(
                req.getCustomerId(),
                req.getFullName(),
                req.getEmail(),
                null,//req.getPhone(),
                CustomerStatus.valueOf(req.getStatus()),
                /* createdAt */
                java.time.LocalDateTime.now()
        );
    }

    public static CustomerResponseDTO toResponse(Customer entity) {
        return CustomerResponseDTO.of(
                entity.getCustomerId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getStatus().name(),
                //((entity.getCreatedAt()).atZone(ZoneId.systemDefault())).toInstant(),   //im EST
                entity.getCreatedAt()
                //LocalDateTime.now()
                //Instant.now()
        );
    }
}
