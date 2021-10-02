package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="libro")
public class Libro {
	@Id
	private int id_libro;
	private String descri_libro;
	private int cod_autor;
	private String genero_libro;
	private int stock_libro;
	private double precio;
}
