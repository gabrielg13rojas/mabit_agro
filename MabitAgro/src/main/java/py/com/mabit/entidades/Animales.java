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
import py.com.mabit.enums.ColorAnimal;
import py.com.mabit.enums.EstadoAnimal;
import py.com.mabit.enums.SexoAnimal;

@Entity
public class Animales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String foto;

	@ManyToOne
	@JoinColumn
	private Razas raza;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ColorAnimal color;

	@Column(nullable = false)
	private Double peso;

	@Column(nullable = false)
	private String identificador;

	@Column(nullable = false)
	private String alias;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SexoAnimal sexo;

	@Column(nullable = false)
	private LocalDate fechaIngreso = LocalDate.now();

	@Column(nullable = false)
	private LocalDate nacimiento = LocalDate.of(2000, 1, 1);

	@Column(nullable = false)
	private BigDecimal precioCompra;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoAnimal estado;

	public Long getId() {
		return id;
	}

	public Razas getRaza() {
		return raza;
	}

	public void setRaza(Razas raza) {
		this.raza = raza;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public SexoAnimal getSexo() {
		return sexo;
	}

	public void setSexo(SexoAnimal sexo) {
		this.sexo = sexo;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public EstadoAnimal getEstado() {
		return estado;
	}

	public void setEstado(EstadoAnimal estado) {
		this.estado = estado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public ColorAnimal getColor() {
		return color;
	}

	public void setColor(ColorAnimal color) {
		this.color = color;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
}
