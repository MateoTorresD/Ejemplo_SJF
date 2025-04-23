import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el número de procesos: ");
        int n = sc.nextInt();

        List<Proceso> procesos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del proceso " + (i + 1) + ": ");
            String nombre = sc.next();
            System.out.print("Tiempo de ráfaga de " + nombre + ": ");
            int rafaga = sc.nextInt();
            System.out.print("Tiempo de llegada de " + nombre + ": ");
            int llegada = sc.nextInt();
            procesos.add(new Proceso(nombre, rafaga, llegada));
        }

        ejecutarSJF(procesos);
    }

    public static void ejecutarSJF(List<Proceso> listaOriginal) {
        List<Proceso> lista = new ArrayList<>(listaOriginal);
        List<Proceso> completados = new ArrayList<>();
        int tiempo = 0;

        while (completados.size() < lista.size()) {
            // Filtrar procesos que han llegado y no se han completado
            List<Proceso> disponibles = new ArrayList<>();
            for (Proceso p : lista) {
                if (p.tiempoLlegada <= tiempo && !completados.contains(p)) {
                    disponibles.add(p);
                }
            }

            if (disponibles.isEmpty()) {
                tiempo++;
                continue;
            }

            // Seleccionar el proceso con menor tiempo de ráfaga
            Proceso actual = disponibles.stream()
                    .min(Comparator.comparingInt(p -> p.tiempoRafaga))
                    .get();

            actual.tiempoEspera = tiempo - actual.tiempoLlegada;
            tiempo += actual.tiempoRafaga;
            actual.tiempoFinal = tiempo;
            completados.add(actual);
        }

        System.out.println("\nResultados:");
        int totalEspera = 0, totalRetorno = 0;
        for (Proceso p : lista) {
            int retorno = p.tiempoFinal - p.tiempoLlegada;
            System.out.println("Proceso " + p.nombre +
                    " | Espera: " + p.tiempoEspera +
                    " | Retorno: " + retorno);
            totalEspera += p.tiempoEspera;
            totalRetorno += retorno;
        }

        System.out.printf("\nPromedio de espera: %.2f\n", (double) totalEspera / lista.size());
        System.out.printf("Promedio de retorno: %.2f\n", (double) totalRetorno / lista.size());
    }
}
