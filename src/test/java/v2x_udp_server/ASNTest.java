package v2x_udp_server;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Integer;

import com.caeri.v2x.asn.bsm.BSM;
import com.caeri.v2x.asn.map.MapData;
import com.caeri.v2x.asn.rsi.RSI;
import com.caeri.v2x.asn.rsm.RSM;
import com.caeri.v2x.asn.spat.SPAT;

public class ASNTest {

	public static void main(String[] args) throws IOException {

		testBSM();
//		testSPAT();
//		testRSM();
//		testMap();
//		testRSI();
	}
	
	

	public static void testBSM() throws IOException {
		BSM obj =new BSM();
		obj.setHead(new ASN1Integer(10));
		BSM obj2=BSM.parse(obj.getEncoded());
		System.out.println(obj.equals(obj2));
		System.out.println(obj2.getHead().getValue());
	}
	
	public static void testSPAT() throws IOException {
		SPAT obj =new SPAT();
		SPAT obj2=SPAT.parse(obj.getEncoded());
		System.out.println(obj.equals(obj2));
	}
	

	
	public static void testRSM() throws IOException {
		RSM obj =new RSM();
		RSM obj2=RSM.parse(obj.getEncoded());
		System.out.println(obj.equals(obj2));
	}
	
	public static void testRSI() throws IOException {
		RSI obj =new RSI();
		RSI obj2=RSI.parse(obj.getEncoded());
		System.out.println(obj.equals(obj2));
	}
	
	public static void testMap() throws IOException {
		MapData obj =new MapData();
		MapData obj2=MapData.parse(obj.getEncoded());
		System.out.println(obj.equals(obj2));
	}
	

}
