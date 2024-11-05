package py.com.mabit.entidades;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Enumerated(EnumType.STRING)
	private EstadoLote estado;

	@Column(nullable = false)
	private String observaciones;

	@OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Animales> animales;
	
	public Lotes() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUbicación() {
		return ubicación;
	}

	public void setUbicación(String ubicación) {
		this.ubicación = ubicación;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public TipoLote getTipo() {
		return tipo;
	}

	public void setTipo(TipoLote tipo) {
		this.tipo = tipo;
	}

	public String getCondicion_alimentacion() {
		return condicion_alimentacion;
	}

	public void setCondicion_alimentacion(String condicion_alimentacion) {
		this.condicion_alimentacion = condicion_alimentacion;
	}

	public String getCondicion_resguardo() {
		return condicion_resguardo;
	}

	public void setCondicion_resguardo(String condicion_resguardo) {
		this.condicion_resguardo = condicion_resguardo;
	}

	public TipoSueloLote getTipo_suelo() {
		return tipo_suelo;
	}

	public void setTipo_suelo(TipoSueloLote tipo_suelo) {
		this.tipo_suelo = tipo_suelo;
	}

	public EstadoLote getEstado() {
		return estado;
	}

	public void setEstado(EstadoLote estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Animales> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animales> animales) {
		this.animales = animales;
	}
	
}
