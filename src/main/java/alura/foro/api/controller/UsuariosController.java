package alura.foro.api.controller;//package med.voll.api.controller;
//
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//import alura.foro.api.usuarios.Repositories.DTOs.UsuarioRepository;
//import alura.foro.api.usuarios.DTOs.DatosRegistroUsuario;
//import alura.foro.api.usuarios.DTOs.DatosRespuestaUsuario;
//import alura.foro.api.usuarios.models.Usuario;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//
//@RestController
//@RequestMapping("/usuarios")
//public class UsuariosController {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping
//    @Transactional
//    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(
//            @RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
//            UriComponentsBuilder uriComponentsBuilder
//    ) {
//        System.out.println(datosRegistroUsuario);
//        // Encriptar la contraseña
//        String encodedPassword = passwordEncoder.encode(datosRegistroUsuario.password());
//
//        // Crear el usuario con la contraseña encriptada
//        Usuario usuario = new Usuario(datosRegistroUsuario);
//        usuario.getPassword(encodedPassword);
//
//        // Guardar el usuario
//        usuario = usuarioRepository.save(usuario);
//
//        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
//                usuario.getId(),
//                usuario.getNombre(),
//                usuario.getEmail()
//        );
//
//        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
//        System.out.println(datosRespuestaUsuario);
//        return ResponseEntity.created(url).body(datosRespuestaUsuario);
//    }
//
////    @GetMapping
////    public ResponseEntity<Page<DatosListarUsuarios>> listadoUsuarios(@PageableDefault(
////            size = 10) Pageable paginacion) {
////
////        return ResponseEntity.ok(UsuarioRepository.(paginacion).map(DatosListarUsuarios::new));
////    }
////
////    @PutMapping
////    @Transactional
////    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
////        Optional<Usuario> optionalUsuario = UsuarioRepository.findByIdAndStatusTrue(datosActualizarUsuario.id());
////        if (optionalUsuario.isEmpty()) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado o inactivo.");
////        }
////
////        Usuario usuario = optionalUsuario.get();
////        usuario.actualizarDatos(datosActualizarUsuario);
////        return ResponseEntity.ok(new DatosRespuestaUsuario(
////                usuario.getId(),
////                usuario.getTitulo(),
////                usuario.getMensaje(),
////                usuario.getAutor(),
////                usuario.getCurso()));
////    }
////
////
////    // DELETE LOGICO
////    @Deleusuariopping("/{id}")
////    @Transactional
////    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
////        Optional<Usuario> usuarioOptional = UsuarioRepository.findByIdAndStatusTrue(id);
////        if (usuarioOptional.isPresent()) {
////            Usuario usuario = usuarioOptional.get();
////            usuario.desactivarUsuario();
////            UsuarioRepository.save(usuario); // Guardar los cambios
////            return ResponseEntity.noContent().build();
////        } else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////        }
////
////    }
////
////    @GetMapping("/{id}")
////    public ResponseEntity<DatosRespuestaUsuario> retornaDatosUsuario(@PathVariable Long id) {
////        Usuario usuario = UsuarioRepository.getReferenceById(id);
////        var datosUsuario = new DatosRespuestaUsuario(
////                usuario.getId(),
////                usuario.getTitulo(),
////                usuario.getMensaje(),
////                usuario.getAutor(),
////                usuario.getCurso());
////        return ResponseEntity.ok(datosUsuario);
////    }
//
//}
