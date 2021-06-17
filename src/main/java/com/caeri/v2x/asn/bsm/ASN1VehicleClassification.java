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
 * 
VehicleClassification ::= SEQUENCE {
	classification BasicVehicleClass,
	fuelType FuelType OPTIONAL,
	...
}
 */
public class ASN1VehicleClassification extends ASN1Object{
	
	
	private ASN1Integer classification;
	
	private ASN1Integer fuelType;

	public ASN1VehicleClassification() {
		this.classification=new ASN1Integer(0);
		this.fuelType=new ASN1Integer(0);
	}

	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(classification);
		vector.add(fuelType);
		return new DERSequence(vector);
	}

	public static ASN1VehicleClassification parse(byte[] data) throws IOException {
		ASN1VehicleClassification obj=new ASN1VehicleClassification();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//classification
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setClassification((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//fuelType
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setFuelType((ASN1Integer) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1Integer getClassification() {
		return classification;
	}



	public void setClassification(ASN1Integer classification) {
		this.classification = classification;
	}



	public ASN1Integer getFuelType() {
		return fuelType;
	}



	public void setFuelType(ASN1Integer fuelType) {
		this.fuelType = fuelType;
	}
	
}