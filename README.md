## V2X Server ASN

### About Project 
1、BSM\SPAT\MAP\RSI\RSM message asn1 specification<GBT 31024.3-2019 Release20190724>;

2、A part of V2X Server;

### Compile

Apache Maven 3.6.3 above

java version "1.8.0_45" above

	mvn clean package
	cd target
	java -jar v2x_server_asn.jar

### ASN1 encode/decode


Provide parse() method for BSM/SPAT/BSM/BSI/Map Message to decode from asn encode bytes,and getEncoded() method to get asn code.

ASNTest.java

	public static void testBSM() throws IOException {
		BSM obj =new BSM();
		obj.setHead(new ASN1Integer(10));
		BSM obj2=BSM.parse(obj.getEncoded());
		System.out.println(obj.equals(obj2));
		System.out.println(obj2.getHead().getValue());
	}