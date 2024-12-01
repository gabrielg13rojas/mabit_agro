package py.com.mabit.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Mortandad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String foto;
	@Column(nullable = false)
	private LocalDate fecha;
	@OneToOne
	@JoinColumn
	private Animales animal;
	@Column(nullable = false)
	private String causaMuerte;
	@Column(nullable = false)
	private String estadoSaludPrevio;
	@Column(nullable = false)
	private Double edad;
	@Column(nullable = false)
	private Double peso;
	@Column(nullable = false)
	private BigDecimal perdidaEconomica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public String getCausaMuerte() {
		return causaMuerte;
	}

	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}

	public String getEstadoSaludPrevio() {
		return estadoSaludPrevio;
	}

	public void setEstadoSaludPrevio(String estadoSaludPrevio) {
		this.estadoSaludPrevio = estadoSaludPrevio;
	}

	public Double getEdad() {
		return edad;
	}

	public void setEdad(Double edad) {
		this.edad = edad;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public BigDecimal getPerdidaEconomica() {
		return perdidaEconomica;
	}

	public void setPerdidaEconomica(BigDecimal perdidaEconomica) {
		this.perdidaEconomica = perdidaEconomica;
	}
}
