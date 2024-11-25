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

	@OneToOne
	@JoinColumn
	private Animales cria;

	@ManyToOne
	@JoinColumn
	private Animales madre;

	@ManyToOne
	@JoinColumn
	private Animales padre;

	@Column(nullable = false)
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

	public Animales getMadre() {
		if (madre == null) {
			madre = new Animales();
		}
		return madre;
	}

	public Animales getPadre() {
		if (padre == null) {
			padre = new Animales();
		}
		return padre;
	}

	public void setMadre(Animales madre) {
		this.madre = madre;
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
