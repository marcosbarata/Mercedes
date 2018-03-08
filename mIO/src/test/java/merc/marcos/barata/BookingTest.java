package merc.marcos.barata;


import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class BookingTest {
	private static final String FN ="Marcos";
	private static final String LN = "Barata";
	private static final String ID = "1c6bd910-12b1-45d6-b4d8-cdff2f37db9";
	private static final String VID = "3928f780-295b-4dd0-8cc9-28c0738398d9";
	private static final LocalDateTime PD= LocalDateTime.parse("2018-04-16T10:30");
	private LocalDateTime crtdAt = LocalDateTime.now();
	private Booking booking;
	
	@Before
	public void setUp() {
		this.booking = new Booking(ID,VID,FN,LN,PD,crtdAt,null,null);
	}
	
	
	
}
