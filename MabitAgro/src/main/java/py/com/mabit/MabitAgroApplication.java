package py.com.mabit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import py.com.mabit.entidades.Privilegios;
import py.com.mabit.repositorios.PrivilegiosRepositorio;

@SpringBootApplication
public class MabitAgroApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MabitAgroApplication.class, args);
	}

	@Autowired
	PrivilegiosRepositorio repPriv;

	@Override
	public void run(String... args) throws Exception {
		if (!repPriv.findById(1l).isPresent()) {
			Privilegios p = new Privilegios(0l, "ADMINISTRADOR", "FULL", true);
			repPriv.save(p);
		}
	}

}
