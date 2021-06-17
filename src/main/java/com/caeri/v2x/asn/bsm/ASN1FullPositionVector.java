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
 * FullPositionVector ::= SEQUENCE {
	utcTime DDateTime OPTIONAL, -- time with mSec precision
	pos Position3D,
	heading Heading OPTIONAL,
	transmission TransmissionState OPTIONAL,
	speed Speed OPTIONAL,
	posAccuracy PositionalAccuracy OPTIONAL,
	posConficence PositionConfidenceSet OPTIONAL,
	timeConfidence TimeConfidence OPTIONAL,
	motionCfd MotionConfidenceSet OPTIONAL,
	...
	}
 */
public class ASN1FullPositionVector  extends ASN1Object{
	private ASN1DDateTime utcTime;
	
	private ASN1Position3D pos;
	
	private ASN1Integer heading;
	
	private ASN1Enumerated transmission;
	
	private ASN1Integer speed;
	
	private ASN1PositionalAccuracy posAccuracy;
	
	private ASN1PositionConfidenceSet posConficence;
	
	private ASN1Enumerated timeConfidence;
	
	private ASN1MotionConfidenceSet motionCfd;
	
	public ASN1FullPositionVector() {
		this.utcTime=new ASN1DDateTime();
		this.pos=new ASN1Position3D();
		this.heading=new ASN1Integer(0);
		this.transmission=new ASN1Enumerated(0);
		this.speed=new ASN1Integer(100);
		this.posAccuracy=new ASN1PositionalAccuracy();
		this.posConficence=new ASN1PositionConfidenceSet();
		this.timeConfidence=new ASN1Enumerated(0);
		this.motionCfd=new ASN1MotionConfidenceSet();
	}
	
	
	
	public ASN1DDateTime getUtcTime() {
		return utcTime;
	}



	public void setUtcTime(ASN1DDateTime utcTime) {
		this.utcTime = utcTime;
	}



	public ASN1Position3D getPos() {
		return pos;
	}



	public void setPos(ASN1Position3D pos) {
		this.pos = pos;
	}



	public ASN1Integer getHeading() {
		return heading;
	}



	public void setHeading(ASN1Integer heading) {
		this.heading = heading;
	}



	public ASN1Enumerated getTransmission() {
		return transmission;
	}



	public void setTransmission(ASN1Enumerated transmission) {
		this.transmission = transmission;
	}



	public ASN1Integer getSpeed() {
		return speed;
	}



	public void setSpeed(ASN1Integer speed) {
		this.speed = speed;
	}



	public ASN1PositionalAccuracy getPosAccuracy() {
		return posAccuracy;
	}



	public void setPosAccuracy(ASN1PositionalAccuracy posAccuracy) {
		this.posAccuracy = posAccuracy;
	}



	public ASN1PositionConfidenceSet getPosConficence() {
		return posConficence;
	}



	public void setPosConficence(ASN1PositionConfidenceSet posConficence) {
		this.posConficence = posConficence;
	}



	public ASN1Enumerated getTimeConfidence() {
		return timeConfidence;
	}



	public void setTimeConfidence(ASN1Enumerated timeConfidence) {
		this.timeConfidence = timeConfidence;
	}



	public ASN1MotionConfidenceSet getMotionCfd() {
		return motionCfd;
	}



	public void setMotionCfd(ASN1MotionConfidenceSet motionCfd) {
		this.motionCfd = motionCfd;
	}



	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(utcTime);
		vector.add(pos);
		vector.add(heading);
		vector.add(transmission);
		vector.add(speed);
		vector.add(posAccuracy);
		vector.add(posConficence);
		vector.add(timeConfidence);
		vector.add(motionCfd);
		return new DERSequence(vector);
	}
	
	public static ASN1FullPositionVector parse(byte[] data) throws IOException {
		ASN1FullPositionVector obj=new ASN1FullPositionVector();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				
				encodable = parser.readObject();//utcTime
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1DDateTime utcTime=ASN1DDateTime.parse(primitive.getEncoded());
					obj.setUtcTime(utcTime);
				}
				
				encodable = parser.readObject();//pos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Position3D pos=ASN1Position3D.parse(primitive.getEncoded());
					obj.setPos(pos);
				}
				
				encodable = parser.readObject();//heading
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setHeading((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//transmission
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setTransmission((ASN1Enumerated) primitive);
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
					ASN1PositionalAccuracy posAccuracy=ASN1PositionalAccuracy.parse(primitive.getEncoded());
					obj.setPosAccuracy(posAccuracy);
				}
				
				encodable = parser.readObject();//posConficence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionConfidenceSet posConficence=ASN1PositionConfidenceSet.parse(primitive.getEncoded());
					obj.setPosConficence(posConficence);
				}
				
				encodable = parser.readObject();//timeConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setTimeConfidence((ASN1Enumerated) primitive);
					}
				}

				encodable = parser.readObject();//motionCfd
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1MotionConfidenceSet motionCfd=ASN1MotionConfidenceSet.parse(primitive.getEncoded());
					obj.setMotionCfd(motionCfd);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}
	
}