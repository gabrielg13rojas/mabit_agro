package py.com.mabit.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import py.com.mabit.enums.EstadoLote;
import py.com.mabit.enums.TipoLote;
import py.com.mabit.enums.TipoSueloLote;

@Entity
public class Lotes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String codigo;

	@Column(nullable = false)
	private String ubicación;

	@Column(nullable = false)
	private int capacidad;

	@Column(nullable = false)
	private String tamanho;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoLote tipo;

	@Column(nullable = false)
	private String condicion_alimentacion;// (comedores, bebederos) y si tiene sistemas de riego o suplementos.

	@Column(nullable = false)
	private String condicion_resguardo;// Descripción de las condiciones de sombra, techado o protección contra el
										// clima.
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoSueloLote tipo_suelo;

	@Column(nullable = false)
	private String observaciones;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoLote estado;
	
	
}
