package apap.ti.silogistik2106752073;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106752073.model.Gudang;
import apap.ti.silogistik2106752073.service.GudangService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106752073Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106752073Application.class, args);
		
	}

	@Bean
	@Transactional
	CommandLineRunner run (GudangService gudangService) { 
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			var namaGudang = faker.company().name();
            var alamatGudang = faker.address().fullAddress();

            var gudang = new Gudang();
            gudang.setNama(namaGudang);
            gudang.setAlamatGudang(alamatGudang);

            // Simpan gudang ke dalam database
            gudangService.saveGudang(gudang);
			};
}
}
