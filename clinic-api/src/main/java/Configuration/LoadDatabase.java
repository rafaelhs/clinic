package Configuration;

import com.example.clinic.model.Patient;
import com.example.clinic.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository) {
        return args -> {
            log.info("Preloading " +
              patientRepository.save(new Patient(
                      null,
                      "Nome1",
                      1,
                      "document",
                      "phone",
                      LocalDate.now(),
                      "email",
                      "address",
                      1,
                      "district",
                      "complement",
                      "zip",
                      "city",
                      "state",
                      "info")));
        };
    }
}
