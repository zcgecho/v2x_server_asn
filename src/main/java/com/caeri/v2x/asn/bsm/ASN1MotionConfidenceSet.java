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
 * MotionConfidenceSet ::= SEQUENCE {
	speedCfd SpeedConfidence OPTIONAL,
	headingCfd HeadingConfidence OPTIONAL,
	steerCfd SteeringWheelAngleConfidence OPTIONAL
}
 */

public class ASN1MotionConfidenceSet extends ASN1Object{

	
	private ASN1Enumerated speedCfd;
	
	private ASN1Enumerated headingCfd;

	private ASN1Enumerated steerCfd;
	
	public ASN1MotionConfidenceSet() {
		this.speedCfd=new ASN1Enumerated(0);
		this.headingCfd=new ASN1Enumerated(0);
		this.steerCfd=new ASN1Enumerated(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(speedCfd);
		vector.add(headingCfd);
		vector.add(steerCfd);
		return new DERSequence(vector);
	}
	
	public static ASN1MotionConfidenceSet parse(byte[] data) throws IOException {
		ASN1MotionConfidenceSet obj=new ASN1MotionConfidenceSet();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//speedCfd
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setSpeedCfd((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//headingCfd
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setHeadingCfd((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//steerCfd
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setSteerCfd((ASN1Enumerated) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1Enumerated getSpeedCfd() {
		return speedCfd;
	}

	public void setSpeedCfd(ASN1Enumerated speedCfd) {
		this.speedCfd = speedCfd;
	}

	public ASN1Enumerated getHeadingCfd() {
		return headingCfd;
	}

	public void setHeadingCfd(ASN1Enumerated headingCfd) {
		this.headingCfd = headingCfd;
	}

	public ASN1Enumerated getSteerCfd() {
		return steerCfd;
	}

	public void setSteerCfd(ASN1Enumerated steerCfd) {
		this.steerCfd = steerCfd;
	}
	
}
