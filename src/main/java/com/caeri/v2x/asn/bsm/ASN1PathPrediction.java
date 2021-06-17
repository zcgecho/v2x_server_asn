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
 * PathPrediction ::= SEQUENCE {
	radiusOfCurve RadiusOfCurvature,
	-- Units of 10cm
	-- straight path to use value of 32767
	confidence Confidence,
	-- Units of 0.5 percent
	...
	}
 */
public class ASN1PathPrediction extends ASN1Object{

	private ASN1Integer radiusOfCurve;
	
	private ASN1Integer confidence;
	
	
	public ASN1PathPrediction() {
		this.radiusOfCurve=new ASN1Integer(0);
		this.confidence=new ASN1Integer(0);
	}
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(radiusOfCurve);
		vector.add(confidence);
		return new DERSequence(vector);
	}
	
	public static ASN1PathPrediction parse(byte[] data) throws IOException {
		ASN1PathPrediction obj=new ASN1PathPrediction();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//radiusOfCurve
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setRadiusOfCurve((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//confidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setConfidence((ASN1Integer) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}
	
	public ASN1Integer getRadiusOfCurve() {
		return radiusOfCurve;
	}
	public void setRadiusOfCurve(ASN1Integer radiusOfCurve) {
		this.radiusOfCurve = radiusOfCurve;
	}
	public ASN1Integer getConfidence() {
		return confidence;
	}
	public void setConfidence(ASN1Integer confidence) {
		this.confidence = confidence;
	}
	
}