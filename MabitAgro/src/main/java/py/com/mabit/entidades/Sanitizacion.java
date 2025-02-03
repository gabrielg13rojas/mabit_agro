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
public class Sanitizacion implements Serializable {
	private static final long serialVersionUID = 1L;
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
	private Insumos insumo;
	@Column(nullable = false)
	private BigDecimal cantidad;
	@Column(nullable = false)
	private BigDecimal costo;
	@Column(nullable = false)
	private String loteInsumo;

	public Long getId() {
		return id;
	}

	public String getLoteInsumo() {
		return loteInsumo;
	}

	public void setLoteInsumo(String loteInsumo) {
		this.loteInsumo = loteInsumo;
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

	public Insumos getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumos insumo) {
		this.insumo = insumo;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
}
