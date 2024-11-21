package py.com.mabit.entidades;

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
public class Alimentacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Lotes lote;

	@ManyToOne
	@JoinColumn
	private Alimentos alimento;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private BigDecimal cantidad;

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

	public Alimentos getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimentos alimento) {
		this.alimento = alimento;
	}

	public Lotes getLote() {
		return lote;
	}

	public void setLote(Lotes lote) {
		this.lote = lote;
	}
}
