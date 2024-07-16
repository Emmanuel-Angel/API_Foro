package alura.foro.api.models.tema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import alura.foro.api.DTOs.tema.DatosActualizarTema;
import alura.foro.api.DTOs.tema.DatosRegistroTema;

import java.time.LocalDateTime;

@Table(name = "temas")
@Entity(name = "Tema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private String autor;
    private String curso;
    private Boolean status;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Tema(DatosRegistroTema datosRegistroTema) {
        this.status = true;
        this.fechaCreacion = LocalDateTime.now();
        this.titulo = datosRegistroTema.titulo();
        this.mensaje = datosRegistroTema.mensaje();
        this.autor = datosRegistroTema.autor();
        this.curso = datosRegistroTema.curso();

    }

    public void actualizarDatos(DatosActualizarTema datosActualizarTema) {
        if (datosActualizarTema.titulo() != null) {
            this.titulo = datosActualizarTema.titulo();
        }
        if (datosActualizarTema.mensaje() != null) {
            this.mensaje = datosActualizarTema.mensaje();
        }
        if (datosActualizarTema.autor() != null) {
            this.autor = datosActualizarTema.autor();
        }
        if (datosActualizarTema.curso() != null) {
            this.curso = datosActualizarTema.curso();
        }
    }

    public void desactivarTema() {
        this.status = false;
    }
}
