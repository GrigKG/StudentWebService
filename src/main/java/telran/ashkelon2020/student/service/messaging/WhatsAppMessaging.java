package telran.ashkelon2020.student.service.messaging;

import org.springframework.stereotype.Service;

import telran.ashkelon2020.student.dto.MessageDto;
import telran.ashkelon2020.student.service.MessagingService;

@Service
public class WhatsAppMessaging implements MessagingService {

	@Override
	public MessageDto sendMessage(String message) {
		return MessageDto.builder().message("WhatsApp").payload(message).build();
	}
	
}
