package com.mycompany.automoviles.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import com.mycompany.automoviles.entidades.Marca;
import com.mycompany.automoviles.util.JPAUtil;

@ManagedBean
@ViewScoped
public class MarcaBean {

	private Marca marca;
	private List<Marca> marcas;
	private boolean continuarInsercion;

	@PostConstruct
	public void init() {
		marca = new Marca();
	}

	public void grabar() {
		EntityManager em = JPAUtil.getEntityManager();
		em.persist(marca);

	}

	public List<Marca> getMarcas() {
		if (marcas == null) {
			marcas = JPAUtil
					.getEntityManager()
					.createQuery(
							"SELECT m FROM Marca m",
							Marca.class).getResultList();
		}

		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public boolean isContinuarInsercion() {
		return continuarInsercion;
	}

	public void setContinuarInsercion(boolean continuarInsercion) {
		this.continuarInsercion = continuarInsercion;
	}
}
