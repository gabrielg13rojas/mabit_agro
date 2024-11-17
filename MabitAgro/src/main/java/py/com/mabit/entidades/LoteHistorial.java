package py.com.mabit.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class LoteHistorial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate fecha;
	@OneToOne
	private Lotes lote;
	@OneToOne
	private Animales animal;
	
	public LoteHistorial() {
		id = 0l;
		fecha = LocalDate.now();
		animal = new Animales();
		lote = new Lotes();
	}

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

	public Lotes getLote() {
		return lote;
	}

	public void setLote(Lotes lote) {
		this.lote = lote;
	}

	public Animales getAnimal() {
		return animal;
	}

	public void setAnimal(Animales animal) {
		this.animal = animal;
	}
	
}
