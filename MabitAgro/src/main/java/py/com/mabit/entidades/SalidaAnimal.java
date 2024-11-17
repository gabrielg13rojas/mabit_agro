package py.com.mabit.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import py.com.mabit.enums.TipoSalidaAnimal;

public class SalidaAnimal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoSalidaAnimal tipo;

	@ManyToOne
	@JoinColumn
	private Animales animal;

	@Column(nullable = false)
	private BigDecimal montoVenta;

}
