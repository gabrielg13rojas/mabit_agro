package py.com.mabit.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Nacimientos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Animales cria;
	
	@Column
	private Double peso;
	
	@ManyToOne
	@JoinColumn
	private Animales madre;
	
	@ManyToOne
	@JoinColumn
	private Animales padre;
	
	@Column
	private String observaciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Animales getCria() {
		return cria;
	}

	public void setCria(Animales cria) {
		this.cria = cria;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Animales getMadre() {
		return madre;
	}

	public void setMadre(Animales madre) {
		this.madre = madre;
	}

	public Animales getPadre() {
		return padre;
	}

	public void setPadre(Animales padre) {
		this.padre = padre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
