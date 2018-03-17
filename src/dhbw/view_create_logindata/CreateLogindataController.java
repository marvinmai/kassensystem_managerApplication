package dhbw.view_create_logindata;

import dhbw.AlertBox;
import dhbw.datamodel.WaiterModel;
import dhbw.sa.kassensystem_rest.database.databaseservice.DatabaseService;
import dhbw.sa.kassensystem_rest.database.entity.Logindata;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateLogindataController
{
	private DatabaseService databaseService;
	private Stage window;
	private boolean update;

	private int waiterID;

	public Label waiterNameLabel;
	public TextField loginnameField;
	public PasswordField passwort1Field;
	public PasswordField passwort2Field;
	public Button createLogindataButton;

	public void initialize(WaiterModel waiterModel, boolean update, DatabaseService databaseService, Stage window)
	{
		this.databaseService = databaseService;
		this.update = update;
		this.window = window;
		this.waiterID = waiterModel.getWaiterID();

		if(update)
			window.setTitle("Logindaten bearbeiten");
		else
			window.setTitle("Logindaten erstellen");

		String prename = waiterModel.getPrename();
		String lastname = waiterModel.getLastname();
		waiterNameLabel.setText(prename + " " + lastname);
		loginnameField.setText(prename.toLowerCase() + "." + lastname.toLowerCase());
		passwort1Field.requestFocus();
	}

	public void createLogindata(ActionEvent actionEvent)
	{
		String loginname = loginnameField.getText();
		String passwort1 = passwort1Field.getText();
		String passwort2 = passwort2Field.getText();

		boolean passwordsMatch = passwort1.equals(passwort2);
		String finalPassword = passwordsMatch ? passwort1 : null;
		boolean loginnameAlreadyExists = databaseService.existsLogindataWithLoginname(loginname);
		String finalLoginname = loginnameAlreadyExists ? null : loginname;

		// passwörter sind gleich und nicht leer und loginname existiert noch nicht in der DB und
		if(passwordsMatch && !loginnameAlreadyExists && !passwort1.isEmpty())
		{
			// Passwort-Daten ausdrucken


			Logindata logindata = new Logindata(waiterID, finalLoginname, String.valueOf(finalPassword.hashCode()));

			// Neuen Login-Datensatz anlegen
			if(!update)
				databaseService.addLogindata(logindata);
			else
				databaseService.updateLogindata(logindata);

			window.close();
		}
		else
		{
			String error = null;

			if (!passwordsMatch)
			{
				error = "Die Passwörter stimmen nicht überein! Bitte erneut eingeben.";
				passwort1Field.clear();
				passwort2Field.clear();
				passwort1Field.requestFocus();
			}
			else if (loginnameAlreadyExists)
			{
				error = "Der Loginname existiert bereits in der Datenbank. Bitte wählen Sie einen anderen.";
				loginnameField.requestFocus();
			}
			else if (passwort1.isEmpty())
			{
				error = "Bitte ein Passwort eingeben!";
				passwort1Field.requestFocus();
			}
			else if (loginnameAlreadyExists)
			{
				error = "Der Loginname existiert bereits in der Datenbank! Bitte einen anderen wählen.";
				loginnameField.requestFocus();
			}

			AlertBox.display("Fehler", error);
		}
	}
}
