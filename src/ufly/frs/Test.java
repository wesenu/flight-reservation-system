package ufly.frs;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.Airport;
import ufly.entities.Customer;
import ufly.entities.Flight;
import ufly.entities.Meal;
import ufly.entities.PMF;
import ufly.entities.SeatingArrangement.AircraftModel;
@SuppressWarnings("serial")
public class Test extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException 
    {
		
		String test=request.getParameter("test");
		if( test == null )
		{
			response.setContentType("text/plain");
			response.getWriter().println("<h2>welcome to the test servlet</h2>");
		}
		else if( test.equalsIgnoreCase("Customer") == true )
		{
			testCustomer(request,response);
			return;
		}
		else if( test.equalsIgnoreCase("Airport") == true )
		{
			testAirport(request,response);
			return;
		}
		else if( test.equalsIgnoreCase("Flight") == true )
		{
			testFlight(request, response);
			return;
		}
	
    }
	private void testFlight(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("flightno")==null)
		{
			List<Flight> fl = (List<Flight>) Flight.getAllFlights();
			Iterator<Flight> it = fl.iterator();
			response.getWriter().println("<ul>");
			while (it.hasNext())
			{
				response.getWriter().println("<li>"+it.next().toString()+"</li>");
			}
			response.getWriter().println("</ul>");
		}
		else
		{
			
			String flightno = request.getParameter("flightno");
			String origin = request.getParameter("origin");
			String destination = request.getParameter("destination");
			String arrival = request.getParameter("arrival");
			String depature = request.getParameter("departure");
			String meals = request.getParameter("meals");
			String aircraft = request.getParameter("aircraft");
			
			if(flightno ==null || origin == null ||
					destination ==null || arrival == null ||
					meals ==null || aircraft == null)
			{
				response.getWriter().println("Missing entries");
				return;
			}
			
			
			Airport origin1;
			Airport destination1;
			List<Airport> la = Airport.getAllAirports();
			Iterator<Airport> it = la.iterator();
			response.getWriter().println("<ul>");
			
			// This is last ditch effor
			origin1 = it.next();
			destination1 = it.next();
			
			while (it.hasNext())
			{
				Airport temp = it.next();
				if (temp.getCallSign() == origin)
				{
					origin1 = temp;
				}
				else if (temp.getCallSign() == destination) 
				{
					destination1 = temp;
				}
			}
			// for testing
			Date a = new Date();
			Date b = new Date();
			Vector<Meal> c = new Vector<Meal>();
			c.add(Meal.chicken);
			
			
			
			new Flight(flightno, origin1, destination1,a, b, c, AircraftModel.BOEING_777 );
			response.sendRedirect("/entityTest");
		}
	}
	
	
	private void testAirport(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("callSign")==null)
		{
			
			List<Airport> la = Airport.getAllAirports();
			Iterator<Airport> it = la.iterator();
			response.getWriter().println("<ul>");
			while (it.hasNext())
			{
				response.getWriter().println("<li>"+it.next().toString()+"</li>");
			
			}
			response.getWriter().println("</ul>");
		}
		else
		{
			String city = request.getParameter("city");
			String csign = request.getParameter("callSign");
			if(city ==null || csign == null)
			{
				response.getWriter().println("Need both city or callsign");
				return;
			}
			new Airport(csign,city);
			response.sendRedirect("/entityTest");
		}
	}
	
	private void testCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(request.getParameter("emailAddr")==null)
		{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
	            // TODO make this a query a class method
	            Query q = pm.newQuery(Customer.class);
	            /**
	             * in order to check this we need to check every element to see if it 
	             * is of type User, too much work, plus we should not get any
	             * other type. We just suppress the warning
	             */
	            @SuppressWarnings("unchecked")
				List<Customer> results = (List<Customer>) q.execute();
	            Iterator<Customer> it = results.iterator();
	            // Print all Users in the datastore
	            response.getWriter().println("Customers registered: ");
	            response.getWriter().println("<ul>");
	            Customer c;
				while (it.hasNext())
				{
					c = it.next();
					response.getWriter().println("<li>"+c.getFirstName() + " " + c.getLastName() +"</li>");
				
				}
				response.getWriter().println("</ul>");
	        } finally {
	            pm.close();
	        }		
		}
		else
		{
			String emailAddr = request.getParameter("emailAddr");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			if(emailAddr == null || password == null || firstName == null || lastName == null)
			{
				response.getWriter().println("Missing parameters");
				return;
			}
			new Customer(emailAddr,password,firstName,lastName);
			response.sendRedirect("/entityTest?test=Customer");
		}
	}
}