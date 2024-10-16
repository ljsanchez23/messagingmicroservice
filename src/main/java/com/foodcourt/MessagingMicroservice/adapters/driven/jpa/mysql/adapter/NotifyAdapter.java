package com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.mapper.INotifyEntityMapper;
import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.repository.INotifyRepository;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;
import com.foodcourt.MessagingMicroservice.domain.spi.INotifyPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotifyAdapter implements INotifyPersistencePort {
    private final INotifyRepository notifyRepository;
    private final INotifyEntityMapper notifyEntityMapper;

    @Override
    public void saveNotify(Notify notify) {
        notifyRepository.save(notifyEntityMapper.toEntity(notify));
    }
}
