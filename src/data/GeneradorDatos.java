
package data;

import model.Registro;
import java.time.LocalDateTime;
import java.util.List;

public class GeneradorDatos {

    public static List<Registro> obtenerRegistros() {

        return List.of(
            new Registro("U001", "Ruta 5", "Central", "entrada",
                LocalDateTime.of(2026, 11, 6, 7, 0)),

            new Registro("U002", "Ruta 2", "Norte", "entrada",
                LocalDateTime.of(2026, 11, 6, 7, 10)),

            new Registro("U001", "Ruta 5", "Universidad", "entrada",
                LocalDateTime.of(2026, 11, 6, 7, 15)),

            new Registro("U003", "Ruta 2", "Norte", "entrada",
                LocalDateTime.of(2026, 11, 6, 7, 20)),

            new Registro("U002", "Ruta 2", "Centro", "entrada",
                LocalDateTime.of(2026, 11, 6, 7, 30)),

            new Registro("U001", "Ruta 5", "Terminal", "salida",
                LocalDateTime.of(2026, 11, 6, 7, 45)),

            new Registro("U004", "Ruta 3", "Central", "entrada",
                LocalDateTime.of(2026, 11, 6, 8, 0)),

            new Registro("U003", "Ruta 2", "Universidad", "entrada",
                LocalDateTime.of(2026, 11, 6, 8, 10)),

            new Registro("U004", "Ruta 3", "Centro", "entrada",
                LocalDateTime.of(2026, 11, 6, 8, 20)),

            new Registro("U005", "Ruta 5", "Central", "entrada",
                LocalDateTime.of(2026, 11, 6, 8, 30)),

            new Registro("U005", "Ruta 5", "Universidad", "entrada",
                LocalDateTime.of(2026, 11, 6, 8, 45)),

            new Registro("U003", "Ruta 2", "Terminal", "salida",
                LocalDateTime.of(2026, 11, 6, 9, 0))
        );
    }
}
