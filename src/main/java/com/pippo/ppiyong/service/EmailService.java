package com.pippo.ppiyong.service;

import com.pippo.ppiyong.domain.EmailValidation;
import com.pippo.ppiyong.repository.EmailValidationRepository;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class EmailService {

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    EmailValidationRepository emailRepository;


    private MimeMessage createMessage(String to)throws Exception{
        String ePw = createKey();
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);

        isnertDB(to, ePw);
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("삐용 이메일 인증입니다.");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 삐용 임시 인증번호입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 임시 인증번호를 입력해주세요.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>임시 인증번호</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("ppiyong","ppiyong"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 4; i++) { // 비밀번호 4자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0 -> key.append((char) ((int) (rnd.nextInt(26)) + 97));

                //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                case 1 -> key.append((char) ((int) (rnd.nextInt(26)) + 65));

                //  A~Z
                case 2 -> key.append((rnd.nextInt(10)));

                // 0~9
            }
        }
        return key.toString();
    }

    public void sendMessage(String to)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
            //TODO : 비밀번호 바꾸기 (DB)
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private void isnertDB(String email, String ePw){

        //중복제거
        List<EmailValidation> emailValidationList = emailRepository.findAllByEmail(email);
        if (!emailValidationList.isEmpty()) {
            emailRepository.deleteAll(emailValidationList);
        }

        Date now = new Date();
        Long EMAIL_EXP = 1000L * 60 * 5; // 5 minutes
        Date exp = new Date(now.getTime() + EMAIL_EXP);
        EmailValidation emailValidation = new EmailValidation(email, exp, ePw);
        emailRepository.save(emailValidation);
    }

    public boolean ValidationCheck(String email, String code){
        Optional<EmailValidation> emailValidation = emailRepository.findById(email);
        Date now = new Date();
        log.info("exp: "+ emailValidation.get());
        //expiration Check
        if (now.after(emailValidation.get().getExp())) {
            return false;
        }
        //Pw Check
        return emailValidation.map(validation -> (validation.getEPw().equals(code))).orElse(false);
    }
}
