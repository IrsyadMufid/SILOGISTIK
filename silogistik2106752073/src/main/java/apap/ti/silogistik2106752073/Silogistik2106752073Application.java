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
import apap.ti.silogistik2106752073.model.Karyawan;
import apap.ti.silogistik2106752073.service.GudangService;
import apap.ti.silogistik2106752073.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106752073Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106752073Application.class, args);
		
	}

@Bean
@Transactional
CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService) {
    return args -> {
        var faker = new Faker(new Locale("in-ID"));

        // Generate data for Gudang
        var namaGudang = faker.company().name();
        var alamatGudang = faker.address().fullAddress();

        var gudang = new Gudang();
        gudang.setNama(namaGudang);
        gudang.setAlamatGudang(alamatGudang);

        // Simpan gudang ke dalam database
        gudangService.saveGudang(gudang);

        // Generate data for Karyawan
        for (int i = 0; i < 10; i++) { // Generate 10 dummy Karyawan entries
            var namaKaryawan = faker.name().fullName();
            var jenisKelamin = faker.number().numberBetween(1, 2); // 1 for Laki-Laki, 2 for Perempuan
            var tanggalLahir = faker.date().birthday();
            
            var karyawan = new Karyawan();
            karyawan.setNama(namaKaryawan);
            karyawan.setJenisKelamin(jenisKelamin);
            karyawan.setTanggalLahir(tanggalLahir);

            // Simpan karyawan ke dalam database
            karyawanService.saveKaryawan(karyawan);
        }
    };
}

}
