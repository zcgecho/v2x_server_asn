package com.caeri.v2x.asn.map;

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
 * RegulatorySpeedLimit ::= SEQUENCE {
		type SpeedLimitType,
		-- The type of regulatory speed which follows
		speed Speed
		-- The speed in units of 0.02 m/s
		-- See Section 11 for converting and translating
		-- speed expressed in mph into units of m/s
		}
 * @author 15504
 *
 */
public class ASN1RegulatorySpeedLimit extends ASN1Object{

	private ASN1Enumerated type;
	
	private ASN1Integer speed;
	
	
	
	public ASN1RegulatorySpeedLimit() {
		this.type=new ASN1Enumerated(0);
		this.speed=new ASN1Integer(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(type);
		vector.add(speed);
		return new DERSequence(vector);
	}
	
	public static ASN1RegulatorySpeedLimit parse(byte[] data) throws IOException {
		ASN1RegulatorySpeedLimit obj=new ASN1RegulatorySpeedLimit();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//type
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setType((ASN1Enumerated) primitive);
					}
				}

				encodable = parser.readObject();//speed
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSpeed((ASN1Integer) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1Enumerated getType() {
		return type;
	}

	public void setType(ASN1Enumerated type) {
		this.type = type;
	}

	public ASN1Integer getSpeed() {
		return speed;
	}

	public void setSpeed(ASN1Integer speed) {
		this.speed = speed;
	}
	
	

}
