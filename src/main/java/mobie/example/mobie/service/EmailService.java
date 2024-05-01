package mobie.example.mobie.service;

import java.nio.charset.StandardCharsets;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import mobie.example.mobie.dto.DiaDiemDTO;
import mobie.example.mobie.dto.LichTrinhDTO;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	LichTrinhService lichTrinhService;
	
	@Autowired
	DiaDiemService diaDiemService;
	
	public void sendMail(String to, String subject, String body) {
		MimeMessage message = javaMailSender.createMimeMessage();		//tao 1 doi tuong mail
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());  //tao doi tuong tu mail de thiet lap cac thuoc tinh
		
		try {
			helper.setTo(to);	//dia chi nhan 
			helper.setSubject(subject);		//tieu de
			helper.setText(body, true);		//noi dung, true la html
			helper.setFrom("trientran2722002@gmail.com");
			
			javaMailSender.send(message);
		} catch ( MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void ScheduleLocation(String to, int id_sk) {
		// Lấy danh sách lịch trình và địa điểm từ service
        List<LichTrinhDTO> lichTrinhDTOs = lichTrinhService.getAllByIdSk(id_sk);
        List<DiaDiemDTO> diaDiemDTOs = diaDiemService.getByIdSk(id_sk);

        // Xây dựng nội dung email với lịch trình và địa điểm
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("<b>Kính chào quý khách</b><br>")
                   .append("<b>Thông tin lịch trình và địa điểm của sự kiện quý khách xác nhận tham dự:</b><br><br>");

        // Thêm thông tin về lịch trình
        bodyBuilder.append("<b>Lịch trình:</b><br>");
        for (LichTrinhDTO lichTrinhDTO : lichTrinhDTOs) {
            bodyBuilder.append("- ").append(lichTrinhDTO.getName()).append(": ").append("<br>")
            						.append(" + Thời gian: ")
            							.append(lichTrinhDTO.getCreateAt()).append(" - ")
            							.append(lichTrinhDTO.getEndAt()).append("<br>")
            						.append(" + Mô tả: ")
            						.append(lichTrinhDTO.getDescrision())
            						.append("<br>");
        }
        bodyBuilder.append("<br>");

        // Thêm thông tin về địa điểm
        bodyBuilder.append("<b>Địa điểm:</b><br>");
        for (DiaDiemDTO diaDiemDTO : diaDiemDTOs) {
            bodyBuilder.append("- ").append("Tên địa điểm: ")
            							.append(diaDiemDTO.getName()).append("<br>")
            						.append(" + Địa chỉ: ")
            							.append(diaDiemDTO.getAddress()).append("<br>");
        }

        String body = bodyBuilder.toString();

        // Gửi email cho khách mời
        sendMail(to, "Lịch trình và địa điểm", body);
	}

}
