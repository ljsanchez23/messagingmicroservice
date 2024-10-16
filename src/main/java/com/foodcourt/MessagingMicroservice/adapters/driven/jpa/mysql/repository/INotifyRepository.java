package com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.entity.NotifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotifyRepository extends JpaRepository<NotifyEntity, Long> {
}
