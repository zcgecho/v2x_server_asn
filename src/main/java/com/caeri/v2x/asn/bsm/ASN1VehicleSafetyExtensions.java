package com.caeri.v2x.asn.bsm;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;

import com.caeri.v2x.asn.bsm.ASN1PathHistory;
import com.caeri.v2x.asn.bsm.ASN1PathPrediction;

/**
 * VehicleSafetyExtensions ::= SEQUENCE {
	events VehicleEventFlags OPTIONAL,
	pathHistory PathHistory OPTIONAL,
	pathPrediction PathPrediction OPTIONAL,
	lights ExteriorLights OPTIONAL,
	...
	}
 */
public class ASN1VehicleSafetyExtensions extends ASN1Object{
	
	private ASN1BitString events;
	
	private ASN1PathHistory pathHistory;
	
	private ASN1PathPrediction pathPrediction;
	
	private ASN1BitString lights;
	
	
	public ASN1VehicleSafetyExtensions() {
		this.events=new DERBitString("".getBytes());
		this.pathHistory=new ASN1PathHistory();
		this.pathPrediction=new ASN1PathPrediction();
		this.lights=new DERBitString("".getBytes());
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(events);
		vector.add(pathHistory);
		vector.add(pathPrediction);
		vector.add(lights);
		return new DERSequence(vector);
	}
	
	public static ASN1VehicleSafetyExtensions parse(byte[] data) throws IOException {
		ASN1VehicleSafetyExtensions obj=new ASN1VehicleSafetyExtensions();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;
				
				
				encodable = parser.readObject();//events
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1BitString) {
						obj.setEvents ((ASN1BitString) primitive);
					}
				}
				
				encodable = parser.readObject();//pathHistory
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PathHistory pathHistory=ASN1PathHistory.parse(primitive.getEncoded());
					obj.setPathHistory(pathHistory);
				}
				

				encodable = parser.readObject();//pathPrediction
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PathPrediction pathPrediction=ASN1PathPrediction.parse(primitive.getEncoded());
					obj.setPathPrediction(pathPrediction);
				}

				
				encodable = parser.readObject();//lights
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1BitString) {
						obj.setLights ((ASN1BitString) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1BitString getEvents() {
		return events;
	}

	public void setEvents(ASN1BitString events) {
		this.events = events;
	}

	public ASN1PathHistory getPathHistory() {
		return pathHistory;
	}

	public void setPathHistory(ASN1PathHistory pathHistory) {
		this.pathHistory = pathHistory;
	}

	public ASN1PathPrediction getPathPrediction() {
		return pathPrediction;
	}

	public void setPathPrediction(ASN1PathPrediction pathPrediction) {
		this.pathPrediction = pathPrediction;
	}

	public ASN1BitString getLights() {
		return lights;
	}

	public void setLights(ASN1BitString lights) {
		this.lights = lights;
	}
	
}