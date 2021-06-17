package com.caeri.v2x.asn.spat;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;


/**
 * PhaseState ::= SEQUENCE {
		light LightState,
		-- Consisting of:
		-- Phase state (the basic 11 states)
		-- Directional, protected, or permissive state
		timing TimeChangeDetails OPTIONAL,
		-- Timing Data in UTC time stamps for event
		-- includes start and min/max end times of phase
		-- confidence and estimated next occurrence
		...
		}
 * @author 15504
 *
 */
public class ASN1PhaseState extends ASN1Object{

	private ASN1Enumerated light;
	
	private ASN1OctetString timing;
	
	public ASN1PhaseState() {
		this.light=new ASN1Enumerated(0);
		this.timing=new DEROctetString("".getBytes());
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		
		vector.add(light);
		vector.add(timing);
		return new DERSequence(vector);
	}
	

	public static ASN1PhaseState parse(byte[] data) throws IOException {
		ASN1PhaseState obj=new ASN1PhaseState();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//light
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setLight((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//timing
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setTiming((DEROctetString) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1Enumerated getLight() {
		return light;
	}

	public void setLight(ASN1Enumerated light) {
		this.light = light;
	}

	public ASN1OctetString getTiming() {
		return timing;
	}

	public void setTiming(ASN1OctetString timing) {
		this.timing = timing;
	}
	
	

}
