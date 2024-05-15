package libreriaV2;

import java.util.ArrayList;
import java.util.List;

public class generic<T,S> {
	
	private T atributo1,atributo2;
	private S atributo3,atributo4;
	private T[]atributo5;
	private List<T> atributo6=new ArrayList();
	public generic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public generic(T atributo1) {
		super();
		this.atributo1 = atributo1;
	}
	public generic(T atributo1, S atributo3) {
		super();
		this.atributo1 = atributo1;
		this.atributo3 = atributo3;
	}
	public generic(T atributo1, T atributo2, S atributo3) {
		super();
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
		this.atributo3 = atributo3;
	}
	public generic(T atributo1, T atributo2, S atributo3, S atributo4) {
		super();
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
		this.atributo3 = atributo3;
		this.atributo4 = atributo4;
	}
	public T getAtributo1() {
		return atributo1;
	}
	public void setAtributo1(T atributo1) {
		this.atributo1 = atributo1;
	}
	public T getAtributo2() {
		return atributo2;
	}
	public void setAtributo2(T atributo2) {
		this.atributo2 = atributo2;
	}
	public S getAtributo3() {
		return atributo3;
	}
	public void setAtributo3(S atributo3) {
		this.atributo3 = atributo3;
	}
	public S getAtributo4() {
		return atributo4;
	}
	public void setAtributo4(S atributo4) {
		this.atributo4 = atributo4;
	}
	public T[] getAtributo5() {
		return atributo5;
	}
	public void setAtributo5(T[]atributo5) {
		this.atributo5=atributo5;
	}
	public void loadElements (T...elements) {
		atributo5=elements.clone();
	}
	public List<T> getAtributo6(){
		return atributo6;
	}
	public void setAtributo6(List<T> atributo6) {
		this.atributo6=atributo6;
	}
	public void addElements(T elements ) {
		atributo6.add(elements);
	}
	
}
