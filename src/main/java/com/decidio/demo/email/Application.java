package com.decidio.demo.email;

import com.decidio.demo.email.service.EmailService;
import com.sendgrid.Email;
import com.sendgrid.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.regex.Pattern;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start? Y/N");
        String start = scanner.nextLine();
        while (start.equalsIgnoreCase("Y")) {
            System.out.println("Enter an email");
            String address = scanner.nextLine();
            if (Pattern.matches("(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)", address)) {
                System.out.println("Enter a name");
                String name = scanner.nextLine();
               Response response = emailService.sendEmail(new Email(address,name));
               if(response.getStatusCode()== HttpStatus.SC_ACCEPTED){
                   System.out.println("Done");
               }
            } else {
                System.out.println("You entered a wrong formatted email. Pls try again");
                address = scanner.nextLine();
            }
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
