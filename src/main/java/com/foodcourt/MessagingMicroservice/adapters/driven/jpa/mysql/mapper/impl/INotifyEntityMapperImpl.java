package com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.entity.NotifyEntity;
import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.mapper.INotifyEntityMapper;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;
import org.springframework.stereotype.Component;

@Component
public class INotifyEntityMapperImpl implements INotifyEntityMapper {
    @Override
    public NotifyEntity toEntity(Notify notify) {
        if(notify == null){
            return null;
        }
        NotifyEntity notifyEntity = new NotifyEntity();
        notifyEntity.setMessage(notify.getMessage());
        notifyEntity.setPhoneNumber(notify.getPhoneNumber());
        return notifyEntity;
    }

    @Override
    public Notify toModel(NotifyEntity notifyEntity) {
        if(notifyEntity == null){
            return null;
        }
        String phoneNumber = notifyEntity.getPhoneNumber();
        String message = notifyEntity.getMessage();

        return new Notify(phoneNumber, message);
    }
}
