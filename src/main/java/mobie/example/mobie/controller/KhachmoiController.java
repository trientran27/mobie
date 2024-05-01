package mobie.example.mobie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mobie.example.mobie.dto.MailDTO;
import mobie.example.mobie.service.EmailService;

@RestController
@RequestMapping("/khachmoi")
@Slf4j
public class KhachmoiController {
	@Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public void sendScheduleAndLocation(@RequestBody MailDTO mailDTO) {
    	List<String> attendees = mailDTO.getMailList();
    	int id_sk = mailDTO.getId_sk();
        for (String to : attendees) {
        	emailService.ScheduleLocation(to, id_sk);
        	log.info("Send mail : " + to);
        }
    }
}
