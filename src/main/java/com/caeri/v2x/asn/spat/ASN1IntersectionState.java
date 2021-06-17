package com.caeri.v2x.asn.spat;

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

import com.caeri.v2x.asn.map.ASN1NodeReferenceID;

/**
 * IntersectionState ::= SEQUENCE {
		intersectionId NodeReferenceID,
		-- A globally unique value set, consisting of a
		-- regionID and intersection ID assignment
		-- provides a unique mapping to the MAP Node
		status IntersectionStatusObject,
		-- general status of the controller(s)
		moy MinuteOfTheYear OPTIONAL,
		-- Minute of current UTC year
		-- used only with messages to be archived
		timeStamp DSecond OPTIONAL,
		-- the mSec point in the current UTC minute that
		-- this message was constructed
		timeConfidence TimeConfidence OPTIONAL,
		-- indicate the time confidence of the above UTC time
		phases PhaseList,
		-- Each Movement is given in turn
		-- and contains its signal phase state,
		-- mapping to the lanes it applies to, and
		-- point in time it will end, and it
		-- may contain both active and future states
		...
	}
 * @author 15504
 *
 */
public class ASN1IntersectionState extends ASN1Object{

	private ASN1NodeReferenceID intersectionId;
	
	private ASN1BitString status;
	
	private ASN1Integer moy;
	
	private ASN1Integer timeStamp;
	
	private ASN1Enumerated timeConfidence;
	
	private ASN1PhaseList phases;
	
	public ASN1IntersectionState() {
		this.intersectionId=new ASN1NodeReferenceID();
		this.status=new DERBitString("".getBytes());
		this.moy=new ASN1Integer(0);
		this.timeStamp=new ASN1Integer(0);
		this.timeConfidence=new ASN1Enumerated(0);
		this.phases=new ASN1PhaseList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(intersectionId);
		vector.add(status);
		vector.add(moy);
		vector.add(timeStamp);
		vector.add(timeConfidence);
		vector.add(phases);
		return new DERSequence(vector);
	}

	public static ASN1IntersectionState parse(byte[] data) throws IOException {
		ASN1IntersectionState obj=new ASN1IntersectionState();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;


				encodable = parser.readObject();//intersectionId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeReferenceID intersectionId=ASN1NodeReferenceID.parse(primitive.getEncoded());
					obj.setIntersectionId(intersectionId);
				}
				
				
				encodable = parser.readObject();//status
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setStatus((DERBitString) primitive);
					}
				}
				
				encodable = parser.readObject();//moy
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setMoy((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//timeStamp
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setTimeStamp((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//timeConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setTimeConfidence((ASN1Enumerated) primitive);
					}
				}
				


				encodable = parser.readObject();//phases
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PhaseList phases=ASN1PhaseList.parse(primitive.getEncoded());
					obj.setPhases(phases);
				}
				
				
				
			}
		}
		ais.close();
		return obj;
	}
	
	public ASN1NodeReferenceID getIntersectionId() {
		return intersectionId;
	}

	public void setIntersectionId(ASN1NodeReferenceID intersectionId) {
		this.intersectionId = intersectionId;
	}

	public ASN1BitString getStatus() {
		return status;
	}

	public void setStatus(ASN1BitString status) {
		this.status = status;
	}

	public ASN1Integer getMoy() {
		return moy;
	}

	public void setMoy(ASN1Integer moy) {
		this.moy = moy;
	}

	public ASN1Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(ASN1Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ASN1Enumerated getTimeConfidence() {
		return timeConfidence;
	}

	public void setTimeConfidence(ASN1Enumerated timeConfidence) {
		this.timeConfidence = timeConfidence;
	}

	public ASN1PhaseList getPhases() {
		return phases;
	}

	public void setPhases(ASN1PhaseList phases) {
		this.phases = phases;
	}

	
	
	

}
