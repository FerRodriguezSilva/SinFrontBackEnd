package ucb.com.backendSinFront.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.com.backendSinFront.entity.Publicacion;
import ucb.com.backendSinFront.entity.Respuesta;
import ucb.com.backendSinFront.service.ForoService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController

@RequestMapping("/api/foro")
public class ForoController {

  @Autowired
  private ForoService foroService;

  @PostMapping("/publicacion")
  public ResponseEntity<Publicacion> crearPublicacion(@RequestBody Publicacion publicacion) {
    Publicacion nuevaPublicacion = foroService.crearPublicacion(publicacion);
    return ResponseEntity.ok(nuevaPublicacion);
  }

  @GetMapping("/publicaciones")
  public ResponseEntity<List<Publicacion>> obtenerTodasLasPublicaciones() {
    List<Publicacion> publicaciones = foroService.obtenerTodasLasPublicaciones();
    return ResponseEntity.ok(publicaciones);
  }

  @GetMapping("/publicacion/{id}")
  public ResponseEntity<Publicacion> obtenerPublicacionPorId(@PathVariable Long id) {
    Optional<Publicacion> publicacion = foroService.obtenerPublicacionPorId(id);
    return publicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/publicacion/{id}/respuesta")
  public ResponseEntity<Respuesta> agregarRespuesta(@PathVariable Long id, @RequestBody Respuesta respuesta) {
    Respuesta nuevaRespuesta = foroService.agregarRespuesta(id, respuesta);
    return nuevaRespuesta != null ? ResponseEntity.ok(nuevaRespuesta) : ResponseEntity.notFound().build();
  }
}
