package com.caeri.v2x.asn.map;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;


/**
 * ConnectingLane ::= SEQUENCE {
		lane LaneID, 
		-- Index of the connecting lane
		maneuver AllowedManeuvers OPTIONAL
		-- The Maneuver between
		-- the enclosing lane and this lane
		-- at the stop line to connect them
		}
 * @author 15504
 *
 */
public class ASN1ConnectingLane extends ASN1Object{

	private ASN1Integer lane;
	
	private ASN1BitString maneuver;
	
	public ASN1ConnectingLane() {
		this.lane=new ASN1Integer(0);
		this.maneuver=new DERBitString("".getBytes());
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(lane);
		vector.add(maneuver);
		return new DERSequence(vector);
	}
	
	public static ASN1ConnectingLane parse(byte[] data) throws IOException {
		ASN1ConnectingLane obj=new ASN1ConnectingLane();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//lane
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLane((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//maneuver
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setManeuver((DERBitString) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1Integer getLane() {
		return lane;
	}

	public void setLane(ASN1Integer lane) {
		this.lane = lane;
	}

	public ASN1BitString getManeuver() {
		return maneuver;
	}

	public void setManeuver(ASN1BitString maneuver) {
		this.maneuver = maneuver;
	}
	
	

}
