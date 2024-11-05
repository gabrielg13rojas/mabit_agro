package py.com.mabit.entidades;

import java.time.LocalDate;
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
import py.com.mabit.enums.TipoActividad;

@Entity
public class Actividades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate fecha;

	@Enumerated(EnumType.STRING)
	private TipoActividad tipo;

	@Column(nullable = false)
	private String detalles;

	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Trabajadores> trabajadores;

	@OneToMany(mappedBy = "cabecera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ActividadDetalle> detalle;

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

	public TipoActividad getTipo() {
		return tipo;
	}

	public void setTipo(TipoActividad tipo) {
		this.tipo = tipo;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public List<Trabajadores> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajadores> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public List<ActividadDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<ActividadDetalle> detalle) {
		this.detalle = detalle;
	}
	
}
