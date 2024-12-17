package py.com.mabit.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Alimentacion implements Serializable {
	private static final long serialVersionUID = 123456759L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate fecha;
	@ManyToOne
	@JoinColumn
	private Animales animal;
	@ManyToOne
	@JoinColumn
	private Alimentos alimento;
	@Column(nullable = false)
	private Double cantidad;
	@Column(nullable = false)
	private BigDecimal costo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Animales getAnimal() {
		return animal;
	}

	public void setAnimal(Animales animal) {
		this.animal = animal;
	}

	public Alimentos getAlimento() {
		if (alimento == null) {
			alimento = new Alimentos();
		}
		return alimento;
	}

	public void setAlimento(Alimentos alimento) {
		this.alimento = alimento;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
}
