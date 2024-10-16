package com.foodcourt.MessagingMicroservice.configuration;

import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.adapter.NotifyAdapter;
import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.mapper.INotifyEntityMapper;
import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.repository.INotifyRepository;
import com.foodcourt.MessagingMicroservice.adapters.driven.twilio.MessageAdapter;
import com.foodcourt.MessagingMicroservice.adapters.feign.adapter.FoodCourtAdapter;
import com.foodcourt.MessagingMicroservice.adapters.feign.adapter.UserAdapter;
import com.foodcourt.MessagingMicroservice.adapters.feign.client.IFoodCourtFeignClient;
import com.foodcourt.MessagingMicroservice.adapters.feign.client.IUserFeignClient;
import com.foodcourt.MessagingMicroservice.domain.api.INotifyServicePort;
import com.foodcourt.MessagingMicroservice.domain.api.usecase.NotifyUseCase;
import com.foodcourt.MessagingMicroservice.domain.spi.IMessagePort;
import com.foodcourt.MessagingMicroservice.domain.spi.INotifyPersistencePort;
import com.foodcourt.MessagingMicroservice.domain.spi.IOrderPort;
import com.foodcourt.MessagingMicroservice.domain.spi.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final INotifyRepository notifyRepository;
    private final INotifyEntityMapper notifyEntityMapper;
    private final IUserFeignClient userFeignClient;
    private final IFoodCourtFeignClient foodCourtFeignClient;


    @Bean
    public IMessagePort messagePort(){return new MessageAdapter();}
    @Bean
    public IUserPort userPort(){return new UserAdapter(userFeignClient);}
    @Bean
    public IOrderPort orderPort(){return new FoodCourtAdapter(foodCourtFeignClient);
    }
    @Bean
    public INotifyPersistencePort notifyPersistencePort(){return new NotifyAdapter(notifyRepository, notifyEntityMapper);
    }
    @Bean
    public INotifyServicePort notifyServicePort(){return new NotifyUseCase(userPort(), orderPort(), notifyPersistencePort(), messagePort());}
}
