package alura.foro.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import alura.foro.api.DTOs.Repositories.tema.TemaRepository;
import alura.foro.api.DTOs.tema.DatosActualizarTema;
import alura.foro.api.DTOs.tema.DatosListadoTema;
import alura.foro.api.DTOs.tema.DatosRegistroTema;
import alura.foro.api.DTOs.tema.DatosRespuestaTema;
import alura.foro.api.models.tema.Tema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/temas")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarTema(
            @RequestBody @Valid DatosRegistroTema datosRegistroTema,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        Optional<Tema> temaExistente = temaRepository
                .findByTituloOrMensaje(datosRegistroTema.titulo(), datosRegistroTema.mensaje());

        if (temaExistente.isPresent()) {

            Tema tema = temaExistente.get();
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("El tema con título o mensaje ya existe: Id:" + tema.getId());
        } else {
            Tema nuevoTema = new Tema(datosRegistroTema);
            Tema temaGuardado = temaRepository.save(nuevoTema);

            DatosRespuestaTema datosRespuestaTema = new DatosRespuestaTema(
                    temaGuardado.getId(),
                    temaGuardado.getTitulo(),
                    temaGuardado.getMensaje(),
                    temaGuardado.getAutor(),
                    temaGuardado.getCurso()
            );

            URI url = uriComponentsBuilder
                    .path("/temas/{id}")
                    .buildAndExpand(temaGuardado.getId())
                    .toUri();

            return ResponseEntity
                    .created(url)
                    .body(datosRespuestaTema);
        }
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTema>> listadoTemas(@PageableDefault(
            size = 10) Pageable paginacion) {

        return ResponseEntity
                .ok(temaRepository
                        .findAll(paginacion)
                        .map(DatosListadoTema::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTema(
            @RequestBody @Valid DatosActualizarTema datosActualizarTema
    ) {
        Optional<Tema> optionalTema = temaRepository
                .findByIdAndStatusTrue(datosActualizarTema.id());

        if (optionalTema.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Tema no encontrado o eliminado.");
        }

        Tema tema = optionalTema.get();
        tema.actualizarDatos(datosActualizarTema);
        return ResponseEntity
                .ok(new DatosRespuestaTema(
                        tema.getId(),
                        tema.getTitulo(),
                        tema.getMensaje(),
                        tema.getAutor(),
                        tema.getCurso()
                )
        );
    }

//    // DELETE LOGICO
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<String> eliminarTema(
//    @PathVariable Long id
//    ) {
//        Optional<Tema> temaOptional = temaRepository
//                  .findByIdAndStatusTrue(id);

//        if (temaOptional.isPresent()) {
//            Tema tema = temaOptional.get();
//            tema.desactivarTema();

//            return ResponseEntity
        //            .status(HttpStatus.OK)
        //            .body("Tema eliminado");

//        } else {
//            return ResponseEntity
        //            .status(HttpStatus.NOT_FOUND)
        //            .body("Tema no encontrado o ya eliminado");
//        }
//    }

    // DELETE FÍSICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTema(
            @PathVariable Long id
    ) {
        Optional<Tema> temaOptional = temaRepository
                .findByIdAndStatusTrue(id);
        if (temaOptional.isPresent()) {
            temaRepository.deleteById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Tema eliminado");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Tema no encontrado o ya eliminado");
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<DatosRespuestaTema> retornaDatosTema(@PathVariable Long id) {
//        Tema tema = temaRepository.getReferenceById(id);
//        var datosTema = new DatosRespuestaTema(
//                tema.getId(),
//                tema.getTitulo(),
//                tema.getMensaje(),
//                tema.getAutor(),
//                tema.getCurso());
//        return ResponseEntity.ok(datosTema);
//    }

}
