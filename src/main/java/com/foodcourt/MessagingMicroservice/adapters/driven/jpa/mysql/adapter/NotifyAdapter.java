package com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.mapper.INotifyEntityMapper;
import com.foodcourt.MessagingMicroservice.adapters.driven.jpa.mysql.repository.INotifyRepository;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;
import com.foodcourt.MessagingMicroservice.domain.spi.INotifyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class NotifyAdapter implements INotifyPersistencePort {
    private final INotifyRepository notifyRepository;
    private final INotifyEntityMapper notifyEntityMapper;

    @Override
    public void saveNotify(Notify notify) {
        notifyRepository.save(notifyEntityMapper.toEntity(notify));
    }

    @Override
    public boolean isPinAndPhoneValid(String securityPin, String phoneNumber) {
        // Recorremos todas las notificaciones en la base de datos
        return notifyRepository.findAll().stream()
                .anyMatch(entity -> {
                    String message = entity.getMessage();  // Obtenemos el cuerpo del mensaje
                    String phone = entity.getPhoneNumber(); // Obtenemos el número de teléfono

                    // Extraemos los números del mensaje usando una expresión regular
                    String extractedPin = extractPinFromMessage(message);

                    // Validamos si coinciden el PIN y el teléfono
                    return extractedPin.equals(securityPin) && phone.equals(phoneNumber);
                });
    }

    // Método privado para extraer el PIN del cuerpo del mensaje
    private String extractPinFromMessage(String message) {
        Pattern pattern = Pattern.compile("\\b\\d{5}\\b"); // Coincide con un número de 5 dígitos
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(); // Retorna el PIN encontrado
        }
        return ""; // Retorna vacío si no se encuentra un PIN
    }
}
