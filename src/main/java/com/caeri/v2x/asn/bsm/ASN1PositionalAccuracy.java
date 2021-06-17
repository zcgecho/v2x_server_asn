package com.caeri.v2x.asn.bsm;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;


/**
 * PositionalAccuracy ::= SEQUENCE {
	-- NMEA-183 values expressed in strict ASN form
	semiMajor SemiMajorAxisAccuracy,
	semiMinor SemiMinorAxisAccuracy,
	orientation SemiMajorAxisOrientation
}


SemiMajorAxisAccuracy ::= INTEGER (0..255)
-- semi-major axis accuracy at one standard dev
-- range 0-12.7 meter, LSB = .05m
-- 254 = any value equal or greater than 12.70 meter
-- 255 = unavailable semi-major axis value

SemiMinorAxisAccuracy ::= INTEGER (0..255)
-- semi-minor axis accuracy at one standard dev
-- range 0-12.7 meter, LSB = .05m
-- 254 = any value equal or greater than 12.70 meter
-- 255 = unavailable semi-minor axis value

SemiMajorAxisOrientation ::= INTEGER (0..65535)
-- orientation of semi-major axis
-- relative to true north (0~359.9945078786 degrees)
-- Units of 360/65535 deg = 0.0054932479
-- a value of 0 shall be 0 degrees
-- a value of 1 shall be 0.0054932479 degrees
-- a value of 65534 shall be 359.9945078786 deg
-- a value of 65535 shall be used for orientation unavailable



 */
public class ASN1PositionalAccuracy extends ASN1Object{

	private ASN1Integer semiMajor;
	
	private ASN1Integer semiMinor;
	
	private ASN1Integer orientation;
	
	public ASN1PositionalAccuracy() {
		this.semiMajor=new ASN1Integer(0);
		this.semiMinor=new ASN1Integer(0);
		this.orientation=new ASN1Integer(0);
		
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(semiMajor);
		vector.add(semiMinor);
		vector.add(orientation);
		return new DERSequence(vector);
	}
	
	public static ASN1PositionalAccuracy parse(byte[] data) throws IOException {
		ASN1PositionalAccuracy obj=new ASN1PositionalAccuracy();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//semiMajor
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSemiMajor((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//semiMinor
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSemiMinor((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//orientation
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setOrientation((ASN1Integer) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}


	public ASN1Integer getSemiMajor() {
		return semiMajor;
	}



	public void setSemiMajor(ASN1Integer semiMajor) {
		this.semiMajor = semiMajor;
	}



	public ASN1Integer getSemiMinor() {
		return semiMinor;
	}



	public void setSemiMinor(ASN1Integer semiMinor) {
		this.semiMinor = semiMinor;
	}



	public ASN1Integer getOrientation() {
		return orientation;
	}



	public void setOrientation(ASN1Integer orientation) {
		this.orientation = orientation;
	}
}
