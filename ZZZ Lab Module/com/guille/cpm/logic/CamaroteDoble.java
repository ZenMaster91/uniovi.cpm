package com.guille.cpm.logic;

public class CamaroteDoble extends Camarote implements CanBeFull {
	
	public final static int N_PERSONS = 2;

	public CamaroteDoble() {
		super();
	}
	
	public boolean isFull() {
		int aux = 0;
		if(this.hasCamaSupletoria()) aux = 1;
		return ((this.getPasajeros().size() <= N_PERSONS+aux)&&(this.hasChilds()));
	}

}