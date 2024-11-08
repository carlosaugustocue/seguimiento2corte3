package com.ejemplo.gestiontareas;

import javax.persistence.EntityManager;

public class RepositorioGenerico<T> {
    private EntityManager em;

    public RepositorioGenerico(EntityManager em) {
        this.em = em;
    }

    public void guardar(T entidad) {
        em.getTransaction().begin();
        em.persist(entidad);
        em.getTransaction().commit();
    }

    public T buscarPorId(Class<T> clase, Object id) {
        return em.find(clase, id);
    }

    public void eliminar(T entidad) {
        em.getTransaction().begin();
        em.remove(entidad);
        em.getTransaction().commit();
    }
}
