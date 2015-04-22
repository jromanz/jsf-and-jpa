package com.mycompany.automoviles;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycompany.automoviles.entidades.Automovil;
import com.mycompany.automoviles.entidades.Marca;
import com.mycompany.automoviles.entidades.Modelo;
import com.mycompany.automoviles.util.JPAUtil;

import static org.junit.Assert.assertNotNull;


@RunWith(Arquillian.class)
public class AutomovilTest {
	
	@Inject
	Automovil automovil;
	
	@Deployment
	public static JavaArchive creandoDeploy() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
				.addClasses(Automovil.class, Modelo.class, Marca.class)
				.addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}
	
	@Test
	public void deberiaPersistirAutomovil(){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		Marca marca = new Marca();
		marca.setNombre("HYUNDAI");
		marca.setObservacion("Marca traida de Japón");
		
		Modelo modelo = new Modelo();
		modelo.setDescripcion("Nuevo modelo Hyundai con 4 puertas y airbag");
		modelo.setPotencia(1200);
		modelo.setMarca(marca);

		List<Modelo> modeloList = new ArrayList<Modelo>();
		modeloList.add(modelo);
		
		marca.setModelos(modeloList);
		
		Automovil automovil = new Automovil();
		automovil.setAnoFabricacion(2014);
		automovil.setAnoModelo(2014);
		automovil.setKilometraje(50);
		automovil.setPrecio(61000.99);
		automovil.setObservaciones("Tengan cuidado con este auto");
		automovil.setModeloId(modelo);
		
		em.persist(automovil);
		
		em.getTransaction().commit();
		
	}
	
	@Test
	public void deberiaTraerAutomoviles() {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("select a from Automovil a",
				Automovil.class);
		List<Automovil> automoviles = query.getResultList();
		assertNotNull(automoviles);
		for (Automovil automovil : automoviles) {
			System.out
					.println(automovil.getModeloId().getMarca().getNombre());
		}
	}
}
