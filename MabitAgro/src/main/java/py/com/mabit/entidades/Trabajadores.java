package py.com.mabit.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import py.com.mabit.enums.RegimenTrabajador;

@Entity
public class Trabajadores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String cedula;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column
	private String telefono;

	@Column
	private LocalDate fechaIngreso;

	@Column
	private String funcion;

	@Column
	private BigDecimal salario_base;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RegimenTrabajador regimen;

	@Column
	private BigDecimal tarifa;

	@ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
	private Actividades actividad;

	public Actividades getActividad() {
		return actividad;
	}

	public void setActividad(Actividades actividad) {
		this.actividad = actividad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public BigDecimal getSalario_base() {
		return salario_base;
	}

	public void setSalario_base(BigDecimal salario_base) {
		this.salario_base = salario_base;
	}

	public RegimenTrabajador getRegimen() {
		return regimen;
	}

	public void setRegimen(RegimenTrabajador regimen) {
		this.regimen = regimen;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

}
