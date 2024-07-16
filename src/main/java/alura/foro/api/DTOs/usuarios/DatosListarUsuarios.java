package alura.foro.api.DTOs.usuarios;


import alura.foro.api.models.usuarios.Usuario;

public record DatosListarUsuarios(

        long id,
        String nombre,
        String email
)
{

    public DatosListarUsuarios(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()

        );
    }
}


