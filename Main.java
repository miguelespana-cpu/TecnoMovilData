
import data.GeneradorDatos;
import model.Registro;
import service.AnalizadorDatos;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Registro> registros = GeneradorDatos.obtenerRegistros();

        System.out.println("==================================");
        System.out.println("       TECNOMOVIL DATA");
        System.out.println("==================================");

        System.out.println("\nTotal de registros: " + registros.size());

        // A. Afluencia por estacion
        System.out.println("\n--- A. AFLUENCIA POR ESTACION ---");
        AnalizadorDatos.calcularAfluencia(registros)
                .forEach((estacion, cantidad) ->
                        System.out.println(estacion + ": " + cantidad + " entradas"));

        // B. Horas pico
        System.out.println("\n--- B. HORAS PICO ---");
        AnalizadorDatos.calcularHorasPico(registros)
                .forEach((hora, cantidad) ->
                        System.out.println(hora + ":00 - " + cantidad + " entradas"));

        // C. Rutas mas utilizadas
        System.out.println("\n--- C. RUTAS MAS UTILIZADAS ---");
        AnalizadorDatos.calcularRutasMasUtilizadas(registros)
                .forEach((ruta, cantidad) ->
                        System.out.println(ruta + ": " + cantidad + " usos"));

        // D. Patrones de viaje
        System.out.println("\n--- D. PATRONES DE VIAJE ---");
        AnalizadorDatos.calcularPatronesViaje(registros)
                .forEach((usuario, estaciones) ->
                        System.out.println(usuario + ": " + estaciones));

        // E. Tiempo promedio
        System.out.println("\n--- E. TIEMPO PROMEDIO ENTRE REGISTROS ---");
        double promedio = AnalizadorDatos.calcularTiempoPromedio(registros);
        System.out.printf("Tiempo promedio: %.2f minutos%n", promedio);

        // F. Sobrecarga
        System.out.println("\n--- F. DETECCION DE SOBRECARGA ---");
        int umbral = 2;

        var sobrecargas = AnalizadorDatos.detectarSobrecarga(registros, umbral);

        if (sobrecargas.isEmpty()) {
            System.out.println("No se detectaron rutas criticas.");
        } else {
            sobrecargas.forEach((ruta, ocupacion) ->
                    System.out.println(
                            ruta + ": CRITICA (" + ocupacion + " pasajeros simulados)"
                    ));
        }

        System.out.println("\n==================================");
        System.out.println("       ANALISIS FINALIZADO");
        System.out.println("==================================");
    }
}
