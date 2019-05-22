package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class VoleyTournamentTest {
	
	private VoleyTournament vt;
	
	public void setupScenary1() {
		vt= new VoleyTournament();
		
	}
	
	public void setupScenary2() {
		
	}
	
	
	
	@Test
	public void TestLoadFileAndAddToTree() {
		setupScenary1();
		String path = "data\\information.csv";
		try {
			vt.LoadFileAndAddToTree(path);
		} catch (IOException e) {
			fail("The game wasn't loaded");
		}
		assertTrue("The tree doesn't have a size greater than 0", vt.getRoot()!= null);
	}
	

	@Test
	void testAddParticipantIntoTree() {
		setupScenary1();
		int id = 1;
		String firstName = "Ana";
		String lastName = "Jime";
		String email = "am@mail.es";
		String gender = "Female";
		String country = "Japan";

		Participant p = new Participant(id, firstName, lastName, email, gender, country);
		vt.addSpectatorIntoTree(p);
		assertTrue("The method do not add correct",vt.getRoot()!=null);
		assertTrue("The method do not add correct id",vt.getRoot().getId()==id);
		assertTrue("The method do not add correct first name",vt.getRoot().getFirstName().equals(firstName));
		assertTrue("The method do not add correct last name",vt.getRoot().getLastName().equals(lastName));
		assertTrue("The method do not add correct email",vt.getRoot().getEmail().equals(email));
		assertTrue("The method do not add correct gender",vt.getRoot().getGender().equals(gender));
		assertTrue("The method do not add correct country",vt.getRoot().getCountry().equals(country));

	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
