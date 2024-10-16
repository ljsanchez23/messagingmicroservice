package com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.MessagingMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = AdaptersConstants.NOTIFY_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotifyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String message;

}
