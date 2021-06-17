package com.caeri.v2x.asn.bsm;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;

/**
 * PositionConfidenceSet ::= SEQUENCE {
	pos PositionConfidence, -- for both horizontal directions
	elevation ElevationConfidence OPTIONAL
}
 */
public class ASN1PositionConfidenceSet extends ASN1Object{
	
	private ASN1Enumerated posConfidence;
	
	private ASN1Enumerated elevation;
	
	public ASN1PositionConfidenceSet() {
		this.posConfidence=new ASN1Enumerated(0);
		this.elevation=new ASN1Enumerated(0);
		
	}

	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(posConfidence);
		vector.add(elevation);
		return new DERSequence(vector);
	}
	
	public static ASN1PositionConfidenceSet parse(byte[] data) throws IOException {
		ASN1PositionConfidenceSet obj=new ASN1PositionConfidenceSet();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//posConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setPosConfidence((ASN1Enumerated) primitive);
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

	public ASN1Enumerated getPosConfidence() {
		return posConfidence;
	}

	public void setPosConfidence(ASN1Enumerated posConfidence) {
		this.posConfidence = posConfidence;
	}

	public ASN1Enumerated getElevation() {
		return elevation;
	}

	public void setElevation(ASN1Enumerated elevation) {
		this.elevation = elevation;
	}
}