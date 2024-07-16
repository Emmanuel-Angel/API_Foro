package alura.foro.api.DTOs.tema;

public record DatosRespuestaTema(

        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
}
