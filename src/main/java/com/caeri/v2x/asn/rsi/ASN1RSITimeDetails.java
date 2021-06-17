package com.caeri.v2x.asn.rsi;

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
 * RSITimeDetails ::= SEQUENCE {
		startTime MinuteOfTheYear OPTIONAL,
		endTime MinuteOfTheYear OPTIONAL,
		-- Exact or estimated end time
		endTimeConfidence TimeConfidence OPTIONAL
	}
 * @author 15504
 *
 */
public class ASN1RSITimeDetails extends ASN1Object{

	
	private ASN1Integer startTime;
	
	private ASN1Integer endTime;
	
	private ASN1Enumerated endTimeConfidence;
	
	
	public ASN1RSITimeDetails() {
		this.startTime=new ASN1Integer(0);
		this.endTime=new ASN1Integer(0);
		this.endTimeConfidence=new ASN1Enumerated(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		
		vector.add(this.startTime);
		vector.add(this.endTime);
		vector.add(this.endTimeConfidence);
		
		return new DERSequence(vector);
	}
	
	public static ASN1RSITimeDetails parse(byte[] data) throws IOException {
		ASN1RSITimeDetails obj=new ASN1RSITimeDetails();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//startTime
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setStartTime((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//endTime
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setEndTime((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//endTimeConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setEndTimeConfidence((ASN1Enumerated) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}

	public ASN1Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(ASN1Integer startTime) {
		this.startTime = startTime;
	}

	public ASN1Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(ASN1Integer endTime) {
		this.endTime = endTime;
	}

	public ASN1Enumerated getEndTimeConfidence() {
		return endTimeConfidence;
	}

	public void setEndTimeConfidence(ASN1Enumerated endTimeConfidence) {
		this.endTimeConfidence = endTimeConfidence;
	}
	
	

}
