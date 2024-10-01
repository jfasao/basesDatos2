package ar.unrn.tp.api;


import java.util.List;

import ar.unrn.tp.modelo.Categoria;


public interface CategoriaService {
	
	// 
	void crearCategoria(Categoria categoria);
	//
	void modificarCategoria(Categoria categoria);
	
	void eliminarCategoria(long idCtagoria);
	
	List<Categoria> listarCategorias();


}
