package alura.foro.api.DTOs.tema;


import alura.foro.api.models.tema.Tema;

import java.time.LocalDate;

public record DatosListadoTema(

        long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        boolean status,
        LocalDate fechaCreacion
)
{

    public DatosListadoTema(Tema tema) {
        this(

                tema.getId(),
                tema.getTitulo(),
                tema.getMensaje(),
                tema.getAutor(),
                tema.getCurso(),
                tema.getStatus(),
                tema.getFechaCreacion().toLocalDate()
        );
    }
}


