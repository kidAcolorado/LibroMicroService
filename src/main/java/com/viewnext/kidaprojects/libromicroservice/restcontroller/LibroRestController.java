package com.viewnext.kidaprojects.libromicroservice.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.viewnext.kidaprojects.libromicroservice.model.Libro;
import com.viewnext.kidaprojects.libromicroservice.service.LibroService;
import com.viewnext.kidaprojects.libromicroservice.service.LibroServiceImpl;

import jakarta.persistence.EntityNotFoundException;

/**
 * La clase {@code LibroRestController} proporciona un controlador REST para gestionar las operaciones relacionadas
 * con libros en la base de datos. Permite buscar libros, crear nuevos libros, actualizar información de libros y borrar
 * libros existentes.
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 29 de septiembre de 2023
 */
@RestController
public class LibroRestController {

	@Autowired
	private LibroService libroService;

	private static final String LIBRO_NOT_FOUND = "Libro/s con los argumentos introducidos no encontrado";
	private static final String INVALID_PRECIO = "Formato de argumento(Precio) inválido";

	
	/**
     * Obtiene una lista de todos los libros disponibles en la base de datos.
     *
     * @return Una respuesta HTTP con la lista de libros en formato JSON.
     */
	@GetMapping(value = "libros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarLibros() {
		try {
			List<Libro> listaLibros = libroService.mostrarlibros();

			return ResponseEntity.ok(listaLibros);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LIBRO_NOT_FOUND);
		}
	}

	
	/**
     * Obtiene un libro por su número ISBN único.
     *
     * @param isbn El número ISBN del libro que se desea obtener.
     * @return Una respuesta HTTP con el libro en formato JSON.
     */
	@GetMapping(value = "libro/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarLibroByIsbn(@PathVariable("isbn") String isbn) {

		try {
			Libro libro = libroService.mostrarLibroPorIsbn(isbn);
			return ResponseEntity.ok(libro);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LIBRO_NOT_FOUND);
		}

	}

	
	/**
     * Obtiene una lista de libros que coinciden con un título específico.
     *
     * @param titulo El título de los libros que se desean obtener.
     * @return Una respuesta HTTP con la lista de libros en formato JSON.
     */
	@GetMapping(value = "libros/titulo/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarLibroByTitulo(@PathVariable("titulo") String titulo) {
		try {
			List<Libro> listaLibrosPorTitulo = libroService.mostrarLibrosPorTitulo(titulo);
			return ResponseEntity.ok(listaLibrosPorTitulo);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LIBRO_NOT_FOUND);
		}

	}

	
	/**
     * Obtiene una lista de libros escritos por un autor específico.
     *
     * @param autor El nombre del autor de los libros que se desean obtener.
     * @return Una respuesta HTTP con la lista de libros en formato JSON.
     */
	@GetMapping(value = "libros/autor/{autor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarLibrosByAutor(@PathVariable("autor") String autor) {
		try {
			List<Libro> listaLibrosPorAutor = libroService.mostrarLibrosPorAutor(autor);
			return ResponseEntity.ok(listaLibrosPorAutor);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LIBRO_NOT_FOUND);
		}

	}

	
	 /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param libroParaCrear El libro que se va a crear.
     * @return Una respuesta HTTP con el libro creado en formato JSON.
     */
	@PostMapping(value = "libro", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createLibro(@RequestBody Libro libroParaCrear) {
		try {
			Libro libroCreado = libroService.crearLibro(libroParaCrear);
			return ResponseEntity.ok(libroCreado);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_PRECIO);
		}
	}
	
	/**
     * Crea varios libros en la base de datos.
     *
     * @param listaLibroParaCrear Una lista de libros que se van a crear.
     * @return Una respuesta HTTP con la lista de libros creados en formato JSON.
     */
	@PostMapping(value = "libros", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createVariosLibro(@RequestBody List<Libro> listaLibroParaCrear) {
	    try {
	        List<Libro> listalibrosCreados = libroService.crearVariosLibros(listaLibroParaCrear);
	        return ResponseEntity.ok(listalibrosCreados);
	    } catch (NumberFormatException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_PRECIO);
	    }
	}
	
	
	/**
     * Actualiza la información de un libro en la base de datos.
     *
     * @param libroParaActualizar El libro con la información actualizada.
     * @return Una respuesta HTTP con el libro actualizado en formato JSON.
     */
	@PutMapping(value = "libro", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateLibro(@RequestBody Libro libroParaActualizar) {
	    try {
	        Libro libroActualizado = libroService.actualizarLibro(libroParaActualizar);
	        return ResponseEntity.ok(libroActualizado);
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LIBRO_NOT_FOUND);
	    } catch (NumberFormatException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_PRECIO);
	    }
	}
	
	 /**
     * Borra un libro de la base de datos por su número ISBN único.
     *
     * @param isbn El número ISBN del libro que se va a borrar.
     * @return Una respuesta HTTP sin contenido (204) si el libro se borra con éxito.
     * @throws EntityNotFoundException Si no se encuentra ningún libro con el ISBN dado.
     */
	@DeleteMapping(value = "libro/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteLibro(@PathVariable("isbn") String isbn) {

		try {
			libroService.borrarLibroPorIsbn(isbn);

			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(LIBRO_NOT_FOUND);
		} 

	}

}
