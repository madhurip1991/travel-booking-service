package com.travel.travel_booking_service.mapper;

import com.travel.travel_booking_service.dto.PaymentEventDTO;
import com.travel.travel_booking_service.model.PaymentEvents;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentEventsMapper {

    PaymentEventDTO toDto(PaymentEvents paymentEvents);

    PaymentEvents toEntity(PaymentEventDTO paymentEventDTO);

    List<PaymentEventDTO> toDtoList(List<PaymentEvents> paymentEvents);
}
