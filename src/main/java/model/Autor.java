package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor {
	@Id
	private int cod_autor;
	private String nom_autor;
	private int edad_autor;
	
	
	
	public Autor() {
	}
	public Autor(int cod_autor, String nom_autor, int edad_autor) {
		super();
		this.cod_autor = cod_autor;
		this.nom_autor = nom_autor;
		this.edad_autor = edad_autor;
	}
	@Override
	public String toString() {
		return "Autor [cod_autor=" + cod_autor + ", nom_autor=" + nom_autor + ", edad_autor=" + edad_autor + "]";
	}
	public int getCod_autor() {
		return cod_autor;
	}
	public void setCod_autor(int cod_autor) {
		this.cod_autor = cod_autor;
	}
	public String getNom_autor() {
		return nom_autor;
	}
	public void setNom_autor(String nom_autor) {
		this.nom_autor = nom_autor;
	}
	public int getEdad_autor() {
		return edad_autor;
	}
	public void setEdad_autor(int edad_autor) {
		this.edad_autor = edad_autor;
	}
	
}
