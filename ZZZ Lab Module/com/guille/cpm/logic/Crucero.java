package com.guille.cpm.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guille.util.CollectionsCPM;

public class Crucero implements CanBeFull{
	
	private String crCode;
	private String area;
	private String denominacion;
	private String startPort;
	private String itinerario;
	private String descripcion;
	private boolean acceptUnder16;
	private int duration;
	private List<Date> salidas;
	private List<Viaje> viajes;
	private Barco barco;
	private boolean discount = false;
	
	private String picturePath;
	
	/**
	 * Main constructor.
	 * 
	 * @param crCode Code for the crucero.
	 * @param area where the crucero will take place.
	 * @param den, denomination of the crucero.
	 * @param stPort starting port for the crucero.
	 * @param it itinerario of the crucero.
	 * @param desc, decription for the crucero.
	 * @param u16, if thew crucero accepts child under 16.
	 * @param dur duration of the crucero.
	 * @param salidas dates where the crucero will take place.
	 * @param barco that will perform the trip.
	 */
	public Crucero(String crCode, String area, String den, String stPort, String it, String desc, boolean u16, int dur, List<Date> salidas, Barco barco) {
		this.crCode = crCode;
		this.area = area;
		this.denominacion = den;
		this.startPort = stPort;
		this.itinerario = it;
		this.descripcion = desc;
		this.acceptUnder16 = u16;
		this.duration = dur;
		this.salidas = salidas;
		this.barco = barco;
		this.picturePath = ("com/guille/cpm/img/"+crCode+".jpg");
		createTrips();
	}
	
	/**
	 * For each date in the dates array will create one trip corresponding to one ship and this "crucero".
	 */
	private void createTrips() {
		if(viajes == null)
			viajes = new ArrayList<Viaje>();
		
		for(Date d : salidas) {
			viajes.add(new Viaje(this, d));
		}
	}
	
	/**
	 * Return whether the trip has a discount or not.
	 * 
	 * @return true if the trip has a discount, false otherwise.
	 */
	public boolean isDiscounted() {
		return this.discount;
	}
	
	/**
	 * Will change the actual state of the trip to discounted.
	 */
	public void discount() {
		this.discount = true;
	}
	
	/**
	 * Return the code of the trip.
	 * 
	 * @return the code of the trip.
	 */
	public String getCodigoCrucero() {
		return this.crCode;
	}
	
	/**
	 * Returns the area where the trip will take place.
	 * 
	 * @return the area where the trip will take place.
	 */
	public String getArea() {
		return this.area;
	}
	
	/**
	 * Return the denomination of the trip.
	 * 
	 * @return the denomination of the trip.
	 */
	public String getDenominacion() {
		return this.denominacion;
	}
	
	/**
	 * Returns the starting port of the tree.
	 * 
	 * @return the starting port of the tree.
	 */
	public String getStartPort() {
		return this.startPort;
	}
	
	/**
	 * Return the itinerary of the trip.
	 * 
	 * @return the itinerary of the trip.
	 */
	public String getItinerario() {
		return this.itinerario;
	}
	
	/**
	 * Return the description of the tree.
	 * 
	 * @return the description of the tree.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * Return true if the trip accepts travelers under 16.
	 * 
	 * @return true if the trip accepts child under 16. False otherwise.
	 */
	public boolean getAcceptUnder16() {
		return this.acceptUnder16;
	}
	
	/**
	 * Return the duration of the hole trip.
	 * 
	 * @return the duration of the trip.
	 */
	public int getDuracion() {
		return this.duration;
	}
	
	/**
	 * Returns a list containing all the dates of the trip.
	 * 
	 * @return a list containing all the dates of the trip.
	 */
	public List<Date> getSalidas() {
		return this.salidas;
	}
	
	/**
	 * Returns the ship that will perform the trip.
	 * 
	 * @return the ship that will perform the trip.
	 */
	public Barco getBarco() {
		return this.barco;
	}
	
	/**
	 * Return the path of the picture file for the trip.
	 * 
	 * @return the path of the picture file for the trip.
	 */
	public String getPicturePath() {
		return this.picturePath;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getStartingPrice() {
		if(isDiscounted())
			return (duration*CollectionsCPM.getMinDoubleArray(getBarco().getPrices())*(1-Reserva.DISCOUNT));
		return duration*CollectionsCPM.getMinDoubleArray(getBarco().getPrices());
	}
	
	/**
	 * 
	 * @return
	 */
	public double getStartingPriceBD() {
		return duration*CollectionsCPM.getMinDoubleArray(getBarco().getPrices());
	}

	@Override
	public boolean isFull() {
		for(Viaje v : viajes)
			if(!v.isFull())
				return false;
		return true;
	}
}