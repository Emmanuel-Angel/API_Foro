package alura.foro.api.DTOs.Repositories.tema;

import alura.foro.api.models.tema.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
    Optional<Tema> findByIdAndStatusTrue(Long id);

    Optional<Tema> findByTituloOrMensaje(String titulo, String mensaje);
}
