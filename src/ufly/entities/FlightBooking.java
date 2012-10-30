package ufly.entities;


import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class FlightBooking {
	
	/*------------ CONSTRUCTORS ------------*/
	/**
	 * Create a FlightBooking object
	 * @param bookedBy			: The FlightBooking's bookedBy Customer
	 * @param bookedFlight 		: The FlightBooking's bookedFlight
	 * @param bookedFlightClass	: The FlightBooking's flight class
	 * @param bookedSeat		: The FlightBooking's booked seat
	 * @param mealChoice		: The FlightBooking's selected meal
	 */
	public FlightBooking(Customer bookedBy, Flight bookedFlight, FlightClass bookedFlightClass, Seat bookedSeat, Meal mealChoice)
	{
		this.bookedBy = bookedBy;
		this.bookedFlight = bookedFlight;
		this.bookedFlightClass = bookedFlightClass; // TODO is this enough for storing an enum in the datastore?
		this.bookedSeat = bookedSeat;
		this.mealChoice = mealChoice; // TODO enum. similar to bookedFlightClass's TODO
	}
	
	/*------------MODIFIERS--------------*/
	/**
	 * @param newConfirmationNumber	: new confirmation number to change to
	 */
	public void changeConfirmationNumber(Key newConfirmationNumber)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.confirmationNumber=newConfirmationNumber;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	/**
	 * @param newCustomer	: new customer to change to
	 */
	public void changeCustomer(Customer newCustomer)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedBy=newCustomer;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	/**
	 * @param newFlight	: new flight booked to change to
	 */
	public void changeFlight(Flight newFlight)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedFlight=newFlight;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newFlightClass	: new fight class to change to
	 */
	public void changeFlightClass(FlightClass newFlightClass)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedFlightClass=newFlightClass;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	/**
	 * @param newSeat	: new seat booking to change to
	 */
	public void changeSeat(Seat newSeat)
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.bookedSeat=newSeat;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	/**
	 * @param newMeal	: new meal choice to change to
	 */
	public void changeMealChoice(Meal newMeal)
	{
		
		PersistenceManager pm= PMF.get().getPersistenceManager();
		try
		{
			this.mealChoice=newMeal;
			pm.makePersistent(this);
		
		}finally
		{
			pm.close();
		}
	}
	
	
	/*------------ACCESSORS--------------*/
	/**
	 * @return the confirmation number of booking
	 */
	public Key getConfirmationNumber()
	{
		return this.confirmationNumber;
	}
	
	/**
	 * @return the customer object
	 */
	public Customer getBookedBy()
	{
		return this.bookedBy;
	}
	
	/**
	 * @return the flight booked by customer
	 */
	public Flight getBookedFlight()
	{
		return this.bookedFlight;
	}
	
	/**
	 * @return the flight class of booked flight
	 */
	public FlightClass getBookedFlightClass()
	{
		return this.bookedFlightClass;
	}
	
	/**
	 * @return the booked seat
	 */
	public Seat getBookedSeat()
	{
		return this.bookedSeat;
	}
	
	/**
	 * @return the meal choice
	 */
	public Meal getMealChoice()
	{
		return this.mealChoice;
	}
	
	/*------------ VARIABLES ------------*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) // automatically generate a numeric ID
	private Key confirmationNumber;			// The FlightBooking's confirmation number. Note that since FlightBooking is a child class of entity relationships, its key must either be a Key or a Key value encoded as a string.
	@Persistent
	private Customer bookedBy;				// The FlightBooking's owner
	@Persistent
	private Flight bookedFlight;			// The FlightBooking's flight
	@Persistent
	private FlightClass bookedFlightClass;	// The FlightBooking's flight class
	@Persistent(defaultFetchGroup = "true") // Always load the Seat when parent (this) is loaded
	private Seat bookedSeat;				// The FlightBooking's booked seat
	@Persistent
	private Meal mealChoice;				// The FlightBooking's meal choice
	
}