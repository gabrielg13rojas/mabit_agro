package py.com.mabit.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Alimentacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private BigDecimal cantidad;

	@Column(nullable = false)
	private BigDecimal costo;

	@OneToOne
	private Alimentos alimento;

	@OneToOne
	private Animales animal;

	public Alimentacion() {
		id = 0l;
		fecha = LocalDate.now();
		cantidad = new BigDecimal(0);
		costo = new BigDecimal(0);
		alimento = new Alimentos();
		animal = new Animales();
	}

}
