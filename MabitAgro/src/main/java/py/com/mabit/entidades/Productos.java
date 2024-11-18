package py.com.mabit.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Productos {
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
	private BigDecimal costo;
	
	@Column(nullable = false)
	private Double stock;
	
	@Column(nullable = false)
	private Double stockMinimo;
	
	@ManyToOne
	@JoinColumn
	private UnidadesMedida unidad;
	
	@ManyToOne
	@JoinColumn
	private Proveedores proveedor_habitual;
	
	public Productos() {
		
	}

}
