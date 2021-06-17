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
 * 	VehicleEmergencyExtensions ::= SEQUENCE {
		responseType ResponseType OPTIONAL,
		sirenUse SirenInUse OPTIONAL,
		lightsUse LightbarInUse OPTIONAL,
		...
	}
 */
public class ASN1VehicleEmergencyExtensions extends ASN1Object{
	
	
	private ASN1Enumerated responseType;
	
	private ASN1Enumerated sirenUse;

	private ASN1Enumerated lightsUse;

	
	public ASN1VehicleEmergencyExtensions() {
		this.responseType=new ASN1Enumerated(0);
		this.sirenUse=new ASN1Enumerated(0);
		this.lightsUse=new ASN1Enumerated(0);
	}
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(responseType);
		vector.add(sirenUse);
		vector.add(lightsUse);
		return new DERSequence(vector);
	}

	
	public static ASN1VehicleEmergencyExtensions parse(byte[] data) throws IOException {
		ASN1VehicleEmergencyExtensions obj=new ASN1VehicleEmergencyExtensions();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//responseType
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setResponseType((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//sirenUse
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setSirenUse((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//lightsUse
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setLightsUse((ASN1Enumerated) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}
	public ASN1Enumerated getResponseType() {
		return responseType;
	}
	public void setResponseType(ASN1Enumerated responseType) {
		this.responseType = responseType;
	}
	public ASN1Enumerated getSirenUse() {
		return sirenUse;
	}
	public void setSirenUse(ASN1Enumerated sirenUse) {
		this.sirenUse = sirenUse;
	}
	public ASN1Enumerated getLightsUse() {
		return lightsUse;
	}
	public void setLightsUse(ASN1Enumerated lightsUse) {
		this.lightsUse = lightsUse;
	}
	

	
}