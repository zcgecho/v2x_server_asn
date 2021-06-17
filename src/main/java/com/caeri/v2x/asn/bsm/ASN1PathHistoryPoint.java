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

import com.caeri.v2x.asn.bsm.ASN1PositionOffsetLLV;

/**
 * PathHistoryPoint ::= SEQUENCE {
	llvOffset PositionOffsetLLV,
	timeOffset TimeOffset,
	-- Offset backwards in time
	speed Speed OPTIONAL,
	-- Speed over the reported period
	posAccuracy PositionConfidenceSet OPTIONAL,
	-- The accuracy of this value
	heading CoarseHeading OPTIONAL,
	-- overall heading
	...
	}
 */
public class ASN1PathHistoryPoint extends ASN1Object{

	private ASN1PositionOffsetLLV llvOffset;
	
	private ASN1Integer timeOffset;
	
	private ASN1Integer speed;
	
	private ASN1PositionConfidenceSet posAccuracy;
	
	private ASN1Integer heading;
	
	
	public ASN1PathHistoryPoint() {
		this.llvOffset=new ASN1PositionOffsetLLV();
		this.timeOffset=new ASN1Integer(-1);
		this.speed=new ASN1Integer(0);
		this.posAccuracy=new ASN1PositionConfidenceSet();
		this.heading=new ASN1Integer(0);
	}
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(llvOffset);
		vector.add(timeOffset);
		vector.add(speed);
		vector.add(posAccuracy);
		vector.add(heading);
		return new DERSequence(vector);
	}
	
	public static ASN1PathHistoryPoint parse(byte[] data) throws IOException {
		ASN1PathHistoryPoint obj=new ASN1PathHistoryPoint();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;
				
				encodable = parser.readObject();//llvOffset
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionOffsetLLV llvOffset=ASN1PositionOffsetLLV.parse(primitive.getEncoded());
					obj.setLlvOffset(llvOffset);
				}
				

				encodable = parser.readObject();//timeOffset
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setTimeOffset((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//speed
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSpeed((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//posAccuracy
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionConfidenceSet posAccuracy=ASN1PositionConfidenceSet.parse(primitive.getEncoded());
					obj.setPosAccuracy(posAccuracy);
				}

				encodable = parser.readObject();//heading
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setHeading((ASN1Integer) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1PositionOffsetLLV getLlvOffset() {
		return llvOffset;
	}


	public void setLlvOffset(ASN1PositionOffsetLLV llvOffset) {
		this.llvOffset = llvOffset;
	}


	public ASN1Integer getTimeOffset() {
		return timeOffset;
	}


	public void setTimeOffset(ASN1Integer timeOffset) {
		this.timeOffset = timeOffset;
	}


	public ASN1Integer getSpeed() {
		return speed;
	}


	public void setSpeed(ASN1Integer speed) {
		this.speed = speed;
	}


	public ASN1PositionConfidenceSet getPosAccuracy() {
		return posAccuracy;
	}


	public void setPosAccuracy(ASN1PositionConfidenceSet posAccuracy) {
		this.posAccuracy = posAccuracy;
	}


	public ASN1Integer getHeading() {
		return heading;
	}


	public void setHeading(ASN1Integer heading) {
		this.heading = heading;
	}
	
}