package py.com.mabit.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Insumos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String codigo;
	
	@Column(nullable = false)
	private String imagen;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private String detalles;
	
	@Column(nullable = false)
	private Double stockMinimo;
	
	@ManyToOne
	@JoinColumn
	private UnidadesMedida unidad;
	
	
	public Insumos() {
		
	}

}
