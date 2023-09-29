package com.viewnext.kidaprojects.libromicroservice.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viewnext.kidaprojects.libromicroservice.model.Libro;


/**
 * El repositorio {@code LibroRepository} proporciona métodos para acceder a datos relacionados con
 * la entidad {@code Libro} en la base de datos.
 * 
 * <p>
 * Este repositorio permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en registros
 * de libros, así como consultas personalizadas definidas mediante anotaciones {@code @Query}.
 * </p>
 * 
 * <p>
 * El autor de esta clase es Víctor Colorado "Kid A".
 * </p>
 *
 * @version 1.0
 * @since 29 de septiembre de 2023
 */
public interface LibroRepository extends JpaRepository<Libro, String> {

    /**
     * Busca libros por autor.
     *
     * @param autor El autor de los libros a buscar.
     * @return Una lista de libros cuyo autor coincide con el autor proporcionado.
     */
    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> findLibroByAutor(@Param("autor") String autor);

    /**
     * Busca libros por título.
     *
     * @param titulo El título de los libros a buscar.
     * @return Una lista de libros cuyo título coincide con el título proporcionado.
     */
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    List<Libro> findLibroByTitulo(@Param("titulo") String titulo);
}
