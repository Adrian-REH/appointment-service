package app.appointment.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Objects;

@SpringBootApplication
@ServletComponentScan
@EnableFeignClients
@Slf4j
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);

        try {
                ClassPathResource resource = new ClassPathResource("serviceAccountKey.json");
                InputStream serviceAccount = resource.getInputStream();

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            log.error("Error al cargar el archivo serviceAccountKey.json", e);
        }

    }
}
