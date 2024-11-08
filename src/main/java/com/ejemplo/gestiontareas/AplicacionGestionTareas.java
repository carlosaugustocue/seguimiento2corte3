package com.ejemplo.gestiontareas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AplicacionGestionTareas {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TareasPU");
        EntityManager em = emf.createEntityManager();

        RepositorioGenerico<Tarea> repositorio = new RepositorioGenerico<>(em);

        // Crear y guardar tareas de ejemplo
        Tarea tarea1 = new Tarea("Estudiar para el examen", EstadoTarea.PENDIENTE, new Date(System.currentTimeMillis() + 50000000L));
        Tarea tarea2 = new Tarea("Finalizar proyecto", EstadoTarea.EN_PROGRESO, new Date(System.currentTimeMillis() + 100000000L));

        repositorio.guardar(tarea1);
        repositorio.guardar(tarea2);

        List<Tarea> tareas = new ArrayList<>();
        tareas.add(tarea1);
        tareas.add(tarea2);

        // Iniciar hilo de notificaciones
        Thread notificadorThread = new Thread(new NotificadorTareas(tareas));
        notificadorThread.start();

        // Para este ejemplo, cerramos después de un tiempo
        try {
            Thread.sleep(180000); // Ejecuta por 3 minutos antes de finalizar
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            notificadorThread.interrupt();
            em.close();
            emf.close();
        }
    }
}
