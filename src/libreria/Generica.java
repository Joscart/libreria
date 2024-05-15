package libreria;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class Generica<T,S> {
	/**
	 * @autor Jose Guallasamin
	 * @since 2024-05
	 */

	private T atributo1, atributo2;
	private S atributo3, atributo4;
	private T[] atributo5;
	private List<T> atributo6 = new ArrayList<T>();
	
	/**
	 * Constructor sin argumentos
	 */
	public Generica() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con un argumento
	 * 
	 * @param atributo1 del tipo T
	 */
	public Generica(T atributo1) {
		super();
		this.atributo1 = atributo1;
	}

	/**
	 * Constructor con dos argumentos
	 * 
	 * @param atributo1 del tipo T
	 * @param atributo2 del tipo S
	 */
	public Generica(T atributo1, S atributo3) {
		super();
		this.atributo1 = atributo1;
		this.atributo3 = atributo3;
	}

	/**
	 * Constructor con tres argumentos
	 * 
	 * @param atributo1 del tipo T
	 * @param atributo2 del tipo T
	 * @param atributo3 del tipo S
	 */
	public Generica(T atributo1, T atributo2, S atributo3) {
		super();
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
		this.atributo3 = atributo3;
	}

	/**
	 * Constructor con cuatro argumentos
	 * 
	 * @param atributo1 del tipo T
	 * @param atributo2 del tipo T
	 * @param atributo3 del tipo S
	 * @param atributo4 del tipo S
	 * @param atributo5 variable args del tipo T
	 */
	public Generica(T atributo1, T atributo2, S atributo3, S atributo4, T... atributo5) {
		super();
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
		this.atributo3 = atributo3;
		this.atributo4 = atributo4;
		this.atributo5 = atributo5.clone();
	}

	/**
	 * Getter Atributo1
	 * 
	 * @return atributo1 del tipo T
     */
	public T getAtributo1() {
		return atributo1;
	}

	/**
	 * Setter Atributo1
	 * 
	 * @param atributo1 del tipo T
	 */
	public void setAtributo1(T atributo1) {
		this.atributo1 = atributo1;
	}

	/**
	 * Getter Atributo2
	 * 
	 * @return atributo2 del tipo T
	 */
	public T getAtributo2() {
		return atributo2;
	}

	/**
	 * Setter Atributo2
	 * 
	 * @param atributo2 del tipo T
	 */
	public void setAtributo2(T atributo2) {
		this.atributo2 = atributo2;
	}

	/**
	 * Getter Atributo3
	 * 
	 * @return atributo3 del tipo S
	 */
	public S getAtributo3() {
		return atributo3;
	}

	/**
	 * Setter Atributo3
	 * 
	 * @param atributo3 del tipo S
	 */
	public void setAtributo3(S atributo3) {
		this.atributo3 = atributo3;
	}

	/**
	 * Getter Atributo4
	 * 
	 * @return atributo4 del tipo S
	 */
	public S getAtributo4() {
		return atributo4;
	}

	/**
	 * Setter Atributo4
	 * 
	 * @param atributo4 del tipo S
	 */
	public void setAtributo4(S atributo4) {
		this.atributo4 = atributo4;
	}

	/**
	 * Getter Atributo5
	 * 
	 * @return atributo5 del tipo T[]
	 */
	public T[] getAtributo5() {
		return atributo5;
	}

	/**
	 * Setter Atributo5
	 * 
	 * @param atributo5 del tipo T[]
	 */
	public void setAtributo5(T[] atributo5) {
		this.atributo5 = atributo5;
	}
	
	/**
	 * Load de Atributo5 por variable args
	 * 
	 * @param atributos5 variable args del tipo T
	 */
	public void loadAtributos5(T... atributos5) {
		this.atributo5 = atributos5.clone();
	}
	
	/**
	 * Add de Atributo5 por variable args
	 * 
	 * @param newAtributos5 variable args del tipo T
	 */
	public void addAtributos5(T... newAtributos5) {
		T[] ob = (T[]) Array.newInstance(newAtributos5.getClass().getComponentType(),
				this.atributo5.length + newAtributos5.length);

		System.arraycopy(this.atributo5, 0, ob, 0, this.atributo5.length);
		System.arraycopy(newAtributos5, 0, ob, this.atributo5.length, newAtributos5.length);
		
		this.loadAtributos5(ob);
	}

	/**
	 * Getter Atributo6
	 * 
	 * @return atributo6 del tipo List<T>
     */
	public List<T> getAtributo6() {
		return atributo6;
	}

	/**
	 * Setter Atributo6
	 * 
	 * @param atributo6 del tipo List<T>
	 */
	public void setAtributo6(List<T> atributo6) {
		this.atributo6 = atributo6;
	}
	
	/**
	 * Add de Atributo6 a la lista por variable args
	 * 
	 * @param newAtributos6 variable args del tipo T
	 */
	public void addAtributos6(T... newAtributos6) {
		for (T e : newAtributos6) {
			atributo6.add(e);
		}
	}
	
}
