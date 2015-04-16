package com.mycompany.automoviles.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name="Automovil.findAll",query="select a from Automovil a")
})
public class Automovil implements Serializable {

	private static final long serialVersionUID = 7032199550439391807L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Min(1900)
	@Column(name="annoFabricacion")
	private Integer anoFabricacion;
	@Min(1900)
	@Column(name="annoModelo")
	private Integer anoModelo;
	@Size(max=255)
	@Column(nullable = false, updatable = false)
	private String observaciones;
	@Column(name="precio")
	private double precio;
	@Column(name="kilometraje")
	private Integer kilometraje;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="modelo_id",referencedColumnName="id")
	private Modelo modeloId;
	
	public Automovil() {
	}
	
	public Automovil(Integer id) {
		super();
		this.id = id;
	}

	public Automovil(Integer anoFabricacion, Integer anoModelo,
			String observaciones, double precio, Integer kilometraje) {
		super();
		this.anoFabricacion = anoFabricacion;
		this.anoModelo = anoModelo;
		this.observaciones = observaciones;
		this.precio = precio;
		this.kilometraje = kilometraje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modelo getModeloId() {
		return modeloId;
	}

	public void setModeloId(Modelo modeloId) {
		this.modeloId = modeloId;
	}

	public Integer getAnoFabricacion() {
		return anoFabricacion;
	}

	public void setAnoFabricacion(Integer anoFabricacion) {
		this.anoFabricacion = anoFabricacion;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Integer d) {
		this.kilometraje = d;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Automovil other = (Automovil) obj;
		if (this.id != other.id
				&& (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Automovil [id=" + id + ", anoFabricacion=" + anoFabricacion
				+ ", anoModelo=" + anoModelo + ", precio=" + precio
				+ ", kilometraje=" + kilometraje + ", observaciones="
				+ observaciones + "]";
	}

}
