package dhbw.datamodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WaiterModel
{
	private final SimpleIntegerProperty waiterID;
	private final SimpleStringProperty prename;
	private final SimpleStringProperty lastname;
	private final SimpleBooleanProperty employed;

	public WaiterModel(int waiterID, String prename, String lastname, boolean employed)
	{
		this.waiterID = new SimpleIntegerProperty(waiterID);
		this.prename = new SimpleStringProperty(prename);
		this.lastname = new SimpleStringProperty(lastname);
		this.employed = new SimpleBooleanProperty(employed);
	}

	public int getWaiterID()
	{
		return waiterID.get();
	}

	public SimpleIntegerProperty waiterIDProperty()
	{
		return waiterID;
	}

	public void setWaiterID(int waiterID)
	{
		this.waiterID.set(waiterID);
	}

	public String getPrename()
	{
		return prename.get();
	}

	public SimpleStringProperty prenameProperty()
	{
		return prename;
	}

	public void setPrename(String prename)
	{
		this.prename.set(prename);
	}

	public String getLastname()
	{
		return lastname.get();
	}

	public SimpleStringProperty lastnameProperty()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname.set(lastname);
	}

	public boolean getEmployed()
	{
		return employed.get();
	}

	public SimpleBooleanProperty employedProperty()
	{
		return employed;
	}

	public void setEmployed(boolean employed)
	{
		this.employed.set(employed);
	}
}
