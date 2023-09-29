package com.viewnext.kidaprojects.libromicroservice.service;

import java.util.List;

import com.viewnext.kidaprojects.libromicroservice.model.Libro;



/**
 * La interfaz {@code LibroService} define operaciones para gestionar libros.
 * Proporciona métodos para mostrar, crear, actualizar y eliminar libros, así como
 * para buscar libros por título, autor o ISBN.
 * 
 * <p>
 * El autor de esta interfaz es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 29 de septiembre de 2023
 */
public interface LibroService {

    /**
     * Muestra todos los libros disponibles.
     *
     * @return Una lista de todos los libros disponibles.
     */
    public List<Libro> mostrarlibros();

    /**
     * Busca libros por título.
     *
     * @param titulo El título de los libros a buscar.
     * @return Una lista de libros cuyos títulos coinciden con el título proporcionado.
     */
    public List<Libro> mostrarLibrosPorTitulo(String titulo);

    /**
     * Busca libros por autor.
     *
     * @param autor El autor de los libros a buscar.
     * @return Una lista de libros cuyos autores coinciden con el autor proporcionado.
     */
    public List<Libro> mostrarLibrosPorAutor(String autor);

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn El ISBN del libro a buscar.
     * @return El libro cuyo ISBN coincide con el ISBN proporcionado.
     */
    public Libro mostrarLibroPorIsbn(String isbn);

    /**
     * Crea un nuevo libro.
     *
     * @param libroParaCrear El libro que se va a crear.
     * @return El libro creado.
     */
    public Libro crearLibro(Libro libroParaCrear);

    /**
     * Crea varios libros al mismo tiempo.
     *
     * @param librosParaCrear Una lista de libros que se van a crear.
     * @return Una lista de libros creados.
     */
    public List<Libro> crearVariosLibros(List<Libro> librosParaCrear);

    /**
     * Actualiza un libro existente.
     *
     * @param libroParaActualizar El libro que se va a actualizar.
     * @return El libro actualizado.
     */
    public Libro actualizarLibro(Libro libroParaActualizar);

    /**
     * Borra un libro por su ISBN.
     *
     * @param isbn El ISBN del libro que se va a borrar.
     */
    public void borrarLibroPorIsbn(String isbn);
}
