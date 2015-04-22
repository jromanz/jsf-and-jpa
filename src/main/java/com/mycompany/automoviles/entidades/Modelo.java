package com.mycompany.automoviles.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable
@NamedQueries({
	@NamedQuery(name="Modelo.findAll",query="select m from Modelo m")
})
public class Modelo implements Serializable {

	private static final long serialVersionUID = 601800670210775180L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@NotNull(message = "La descripción del modelo no puede ser null")
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="potencia")
	private Integer potencia;
	@OneToMany(mappedBy="modeloId")
	private Collection<Automovil> automoviles;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="marca_id",referencedColumnName="id")
	private Marca marca;

	
	public Modelo() {
	}
	
	public Modelo(Integer id) {
		super();
		this.id = id;
	}

	public Modelo(String descripcion, Integer potencia) {
		super();
		this.descripcion = descripcion;
		this.potencia = potencia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPotencia() {
		return potencia;
	}

	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Modelo))
			return false;
		Modelo other = (Modelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", descripcion=" + descripcion
				+ ", potencia=" + potencia + ", marca=" + marca + "]";
	}

}
