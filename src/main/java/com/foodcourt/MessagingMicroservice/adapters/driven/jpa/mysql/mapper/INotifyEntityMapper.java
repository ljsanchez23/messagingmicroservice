package com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.entity.NotifyEntity;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;

public interface INotifyEntityMapper {
    NotifyEntity toEntity(Notify notify);
    Notify toModel(NotifyEntity notifyEntity);
}
