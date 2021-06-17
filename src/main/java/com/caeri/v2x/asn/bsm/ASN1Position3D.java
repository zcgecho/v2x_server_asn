package com.caeri.v2x.asn.bsm;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;


/**
 * 
 * 
 * lat Latitude, 
	-- in 1/10th micro degrees
	long Longitude, 
	-- in 1/10th micro degrees
	elevation Elevation OPTIONAL
	-- in 10 cm units
	
	
 * @author 15504
 *
 */
public class ASN1Position3D extends ASN1Object{
	
	private ASN1Integer lat;
	
	private ASN1Integer lon;
	
	private ASN1Enumerated elevation;
	
	public ASN1Position3D() {
		this.lat=new ASN1Integer(-1);
		this.lon=new ASN1Integer(-1);
		this.elevation=new ASN1Enumerated(0);
	}

	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(lat);
		vector.add(lon);
		vector.add(elevation);
		return new DERSequence(vector);
	}
	
	public static ASN1Position3D parse(byte[] data) throws IOException {
		ASN1Position3D obj=new ASN1Position3D();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//lat
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLat((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//lon
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLon((ASN1Integer) primitive);
					}
				}


				encodable = parser.readObject();//elevation
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setElevation((ASN1Enumerated) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1Integer getLat() {
		return lat;
	}

	public void setLat(ASN1Integer lat) {
		this.lat = lat;
	}

	public ASN1Integer getLon() {
		return lon;
	}

	public void setLon(ASN1Integer lon) {
		this.lon = lon;
	}

	public ASN1Enumerated getElevation() {
		return elevation;
	}

	public void setElevation(ASN1Enumerated elevation) {
		this.elevation = elevation;
	}
	
}