package py.com.mabit.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import py.com.mabit.enums.TipoMovimientoAnimal;

@Entity
public class MovimientosAnimales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoMovimientoAnimal tipo;

	@ManyToOne
	@JoinColumn
	private Lotes lote;

	@Column(nullable = false)
	private LocalDate fecha;

	@ManyToOne
	@JoinColumn
	private Animales animal;

	@Column(nullable = false)
	private Double peso;

	@Column(nullable = false)
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoMovimientoAnimal getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimientoAnimal tipo) {
		this.tipo = tipo;
	}

	public Lotes getLote() {
		if (lote==null) {
			lote = new Lotes();
		}
		return lote;
	}

	public void setLote(Lotes lote) {
		this.lote = lote;
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
