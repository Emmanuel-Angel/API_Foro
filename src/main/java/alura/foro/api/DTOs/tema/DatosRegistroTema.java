package alura.foro.api.DTOs.tema;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTema(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
