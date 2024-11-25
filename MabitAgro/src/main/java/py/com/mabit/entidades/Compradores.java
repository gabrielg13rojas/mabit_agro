package py.com.mabit.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import py.com.mabit.enums.SexoPersona;
import py.com.mabit.enums.TipoPersona;

@Entity
public class Compradores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPersona tipo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SexoPersona sexo;

	@ManyToOne
	@JoinColumn
	private TiposDocumentoComprador tipo_documento;

	@Column(nullable = false)
	private String documento;
	@Column(nullable = false)
	private String nombre_razon_social;
	@Column(nullable = false)
	private String telefono;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String direccion;
	

public Compradores() {
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public TipoPersona getTipo() {
	return tipo;
}


public void setTipo(TipoPersona tipo) {
	this.tipo = tipo;
}


public SexoPersona getSexo() {
	return sexo;
}


public void setSexo(SexoPersona sexo) {
	this.sexo = sexo;
}


public TiposDocumentoComprador getTipo_documento() {
	return tipo_documento;
}


public void setTipo_documento(TiposDocumentoComprador tipo_documento) {
	this.tipo_documento = tipo_documento;
}


public String getDocumento() {
	return documento;
}


public void setDocumento(String documento) {
	this.documento = documento;
}


public String getNombre_razon_social() {
	return nombre_razon_social;
}


public void setNombre_razon_social(String nombre_razon_social) {
	this.nombre_razon_social = nombre_razon_social;
}


public String getTelefono() {
	return telefono;
}


public void setTelefono(String telefono) {
	this.telefono = telefono;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getDireccion() {
	return direccion;
}


public void setDireccion(String direccion) {
	this.direccion = direccion;
}

}