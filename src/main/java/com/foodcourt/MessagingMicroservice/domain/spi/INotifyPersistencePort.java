package com.foodcourt.MessagingMicroservice.domain.spi;

import com.foodcourt.MessagingMicroservice.domain.model.Notify;

public interface INotifyPersistencePort {
    void saveNotify(Notify notify);
    boolean isPinAndPhoneValid(String securityPin, String phoneNumber);
}
