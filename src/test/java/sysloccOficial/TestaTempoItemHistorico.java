package sysloccOficial;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import org.junit.Test;

import br.com.sysloccOficial.model.CriacaoItemHistorico;

public class TestaTempoItemHistorico {

	@Test
	public void testTempo() throws UnknownHostException {
		
		long before = System.currentTimeMillis();

		InetAddress addr = InetAddress.getByName("31.220.104.63");

		System.out.println(addr.getHostName());

		long after = System.currentTimeMillis();

		System.out.println((after - before) + " ms");
		
		
	}

}
