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
import py.com.mabit.enums.EspecieAnimal;
import py.com.mabit.enums.EstadoAnimal;
import py.com.mabit.enums.SexoAnimal;

@Entity
public class Animales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EspecieAnimal especie;

	@Column(nullable = false)
	private String identificador;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SexoAnimal sexo;

	@Column
	private LocalDate fechaNacimiento;

	@Column(nullable = false)
	private LocalDate fechaIngreso;

	@Column
	private BigDecimal precioCompra;
	
	@ManyToOne
    @JoinColumn(name = "lote_id", nullable = false) // El nombre de la columna en la tabla de Animales
    private Lotes lote;
	
	public Lotes getLote() {
		return lote;
	}

	public void setLote(Lotes lote) {
		this.lote = lote;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoAnimal estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EspecieAnimal getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieAnimal especie) {
		this.especie = especie;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public SexoAnimal getSexo() {
		return sexo;
	}

	public void setSexo(SexoAnimal sexo) {
		this.sexo = sexo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
}
