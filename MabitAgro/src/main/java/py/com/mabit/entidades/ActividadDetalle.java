package py.com.mabit.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ActividadDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn
	private Animales animal;
	
	@Column(nullable = false)
	private BigDecimal costo;
	
	@ManyToOne
    @JoinColumn(name = "cabecera_id", nullable = false)
	private Actividades cabecera;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animales getAnimal() {
		return animal;
	}

	public void setAnimal(Animales animal) {
		this.animal = animal;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Actividades getCabecera() {
		return cabecera;
	}

	public void setCabecera(Actividades cabecera) {
		this.cabecera = cabecera;
	}	
}
