package com.ejemplo.gestiontareas;

import java.util.List;

public class NotificadorTareas implements Runnable {
    private List<Tarea> tareas;

    public NotificadorTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    @Override
    public void run() {
        while (true) {
            for (Tarea tarea : tareas) {
                if (tarea.getEstado() == EstadoTarea.PENDIENTE && tarea.estaProximaALaFechaLimite()) {
                    System.out.println("¡Notificación! La tarea " + tarea.getNombre() + " está próxima a su fecha límite.");
                }
            }
            try {
                Thread.sleep(60000); // Pausa de 1 minuto
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
