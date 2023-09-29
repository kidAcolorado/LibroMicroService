package com.viewnext.kidaprojects.libromicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewnext.kidaprojects.libromicroservice.model.Libro;
import com.viewnext.kidaprojects.libromicroservice.repository.LibroRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * La clase {@code LibroServiceImpl} implementa la interfaz {@code LibroService} y proporciona
 * servicios relacionados con la gestión de libros en la base de datos. Ofrece métodos para buscar
 * libros por varios criterios, crear libros, borrar libros y actualizar información de libros.
 *
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 29 de septiembre de 2023
 */
@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;

	// ###########################################################
	// ################### MÉTODOS DE BÚSQUEDA ###################
	// ###########################################################

	/**
     * Recupera una lista de todos los libros almacenados en la base de datos.
     *
     * @return Una lista de libros.
     * @throws EntityNotFoundException Si no se encuentra ningún libro en la base de datos.
     */
	@Override
	public List<Libro> mostrarlibros() throws EntityNotFoundException {
		List<Libro> listaLibros = libroRepository.findAll();

		if (listaLibros.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return listaLibros;
	}

	/**
     * Busca libros por título en la base de datos.
     *
     * @param titulo El título del libro a buscar.
     * @return Una lista de libros que coinciden con el título especificado.
     * @throws EntityNotFoundException Si no se encuentra ningún libro con el título dado.
     */
	@Override
	public List<Libro> mostrarLibrosPorTitulo(String titulo) throws EntityNotFoundException {
		List<Libro> listaLibrosPorTitulo = libroRepository.findLibroByTitulo(titulo);
		if (listaLibrosPorTitulo.isEmpty()) {
			throw new EntityNotFoundException();
		}

		return listaLibrosPorTitulo;
	}

	/**
     * Busca libros por autor en la base de datos.
     *
     * @param autor El nombre del autor del libro a buscar.
     * @return Una lista de libros escritos por el autor especificado.
     * @throws EntityNotFoundException Si no se encuentra ningún libro del autor dado.
     */
	@Override
	public List<Libro> mostrarLibrosPorAutor(String autor) throws EntityNotFoundException {
		List<Libro> listaLibrosPorAutor = libroRepository.findLibroByAutor(autor);
		if (listaLibrosPorAutor.isEmpty()) {
			throw new EntityNotFoundException();
		}

		return listaLibrosPorAutor;
	}

	/**
     * Recupera un libro por su número ISBN único.
     *
     * @param isbn El número ISBN del libro a buscar.
     * @return El libro correspondiente al ISBN especificado.
     * @throws EntityNotFoundException Si no se encuentra ningún libro con el ISBN dado.
     */
	@Override
	public Libro mostrarLibroPorIsbn(String isbn) throws EntityNotFoundException {
		Optional<Libro> optionalLibro = libroRepository.findById(isbn);

		if (optionalLibro.isPresent()) {
			return optionalLibro.get();
		} else {
			throw new EntityNotFoundException();
		}

	}

	// ###########################################################
	// ################### MÉTODOS DE CREACIÓN ###################
	// ###########################################################

	/**
     * Crea un nuevo libro en la base de datos.
     *
     * @param libroParaCrear El libro que se va a crear.
     * @return El libro recién creado.
     */
	@Override
	public Libro crearLibro(Libro libroParaCrear) {

		libroRepository.save(libroParaCrear);

		return libroParaCrear;
	}

	
	/**
     * Crea varios libros en la base de datos.
     *
     * @param librosParaCrear Una lista de libros que se van a crear.
     * @return La lista de libros recién creados.
     */
	@Override
	public List<Libro> crearVariosLibros(List<Libro> librosParaCrear) {

		libroRepository.saveAll(librosParaCrear);

		return librosParaCrear;
	}
	
	

	// ###########################################################
	// ################### MÉTODOS DE BORRADO ####################
	// ###########################################################

	
	/**
     * Borra un libro de la base de datos por su número ISBN único.
     *
     * @param isbn El número ISBN del libro que se va a borrar.
     * @throws EntityNotFoundException Si no se encuentra ningún libro con el ISBN dado.
     */
	@Override
	public void borrarLibroPorIsbn(String isbn) throws EntityNotFoundException {
		if (!libroRepository.existsById(isbn)) {
			throw new EntityNotFoundException();
		}

		libroRepository.deleteById(isbn);
	}

	
	
	// ###########################################################
	// #################### MÉTODOS DE UPDATE ####################
	// ###########################################################
	

	/**
     * Actualiza la información de un libro en la base de datos.
     *
     * @param libroParaActualizar El libro con la información actualizada.
     * @return El libro actualizado.
     * @throws EntityNotFoundException Si no se encuentra ningún libro con el ISBN del libro a actualizar.
     */
	@Override
	public Libro actualizarLibro(Libro libroParaActualizar) {
		if (!libroRepository.existsById(libroParaActualizar.getIsbn())) {
			throw new EntityNotFoundException();
		}
		
		libroRepository.save(libroParaActualizar);
		
		return libroParaActualizar;
	}

}
