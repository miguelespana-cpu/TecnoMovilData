
package service;

import model.Registro;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;

public class AnalizadorDatos {

    // A. Afluencia por estacion
    public static Map<String, Long> calcularAfluencia(List<Registro> registros) {

        return registros.stream()
                .filter(r -> r.getAccion().equalsIgnoreCase("entrada"))
                .collect(Collectors.groupingBy(
                        Registro::getEstacion,
                        Collectors.counting()
                ));
    }

    // B. Identificacion de horas pico
    public static Map<Integer, Long> calcularHorasPico(List<Registro> registros) {

        return registros.stream()
                .filter(r -> r.getAccion().equalsIgnoreCase("entrada"))
                .collect(Collectors.groupingBy(
                        r -> r.getTimestamp().getHour(),
                        Collectors.counting()
                ));
    }

    // C. Rutas mas utilizadas
    public static Map<String, Long> calcularRutasMasUtilizadas(List<Registro> registros) {

        return registros.stream()
                .filter(r -> r.getAccion().equalsIgnoreCase("entrada"))
                .collect(Collectors.groupingBy(
                        Registro::getRuta,
                        Collectors.counting()
                ));
    }

    // D. Patrones de viaje por usuario
    public static Map<String, List<String>> calcularPatronesViaje(List<Registro> registros) {

        return registros.stream()
                .collect(Collectors.groupingBy(
                        Registro::getIdUsuario,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                lista -> lista.stream()
                                        .sorted((a, b) -> a.getTimestamp()
                                                .compareTo(b.getTimestamp()))
                                        .map(Registro::getEstacion)
                                        .toList()
                        )
                ));
    }

    
// E. Tiempo promedio entre estaciones consecutivas
public static double calcularTiempoPromedio(List<Registro> registros) {

    // Agrupar únicamente las entradas de cada usuario
    Map<String, List<Registro>> registrosPorUsuario = registros.stream()
            .filter(r -> r.getAccion().equalsIgnoreCase("entrada"))
            .collect(Collectors.groupingBy(Registro::getIdUsuario));

    // Calcular el tiempo entre las entradas consecutivas
    return registrosPorUsuario.values().stream()
            .flatMapToDouble(lista -> {

                List<Registro> ordenados = lista.stream()
                        .sorted((a, b) -> a.getTimestamp()
                                .compareTo(b.getTimestamp()))
                        .toList();

                return IntStream.range(1, ordenados.size())
                        .mapToDouble(i -> Duration.between(
                                ordenados.get(i - 1).getTimestamp(),
                                ordenados.get(i).getTimestamp()
                        ).toMinutes());
            })
            .average()
            .orElse(0.0);
}


    // F. Deteccion de sobrecarga en rutas
    public static Map<String, Integer> detectarSobrecarga(
            List<Registro> registros, int umbral) {

        return registros.stream()
                .collect(Collectors.groupingBy(
                        Registro::getRuta,
                        Collectors.summingInt(r ->
                                r.getAccion().equalsIgnoreCase("entrada") ? 1 : -1)
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > umbral)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }
}
