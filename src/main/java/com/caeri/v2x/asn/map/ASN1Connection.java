package com.caeri.v2x.asn.map;

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
 * Connection::= SEQUENCE {
		remoteIntersection NodeReferenceID,
		-- This entry indicates the downstream intersection of the link this lane connects to. 
		-- This provides a means to create meshes of lanes
		connectingLane ConnectingLane OPTIONAL,
		-- The index of the connecting lane and also
		-- the maneuver from the current lane to it
		-- When we want to list the allowed lanes of the next link
		-- this lane can lead to, we use this entry
		phaseId PhaseID OPTIONAL
		-- The matching signal group send by
		-- the SPAT message for this lane/maneuver.
		-- Shall be present unless the connectingLane
		-- has no signal group (is un-signalized)
	}
 * @author 15504
 *
 */
public class ASN1Connection extends ASN1Object{

	private ASN1NodeReferenceID remoteIntersection;
	
	private ASN1ConnectingLane connectingLane;
	
	private ASN1Integer phaseId;
	
	
	public ASN1Connection() {
		this.remoteIntersection=new ASN1NodeReferenceID();
		this.connectingLane=new ASN1ConnectingLane();
		this.phaseId=new ASN1Integer(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(remoteIntersection);
		vector.add(connectingLane);
		vector.add(phaseId);
		return new DERSequence(vector);
	}
	
	public static ASN1Connection parse(byte[] data) throws IOException {
		ASN1Connection obj=new ASN1Connection();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;
				
				encodable = parser.readObject();//remoteIntersection
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeReferenceID remoteIntersection=ASN1NodeReferenceID.parse(primitive.getEncoded());
					obj.setRemoteIntersection(remoteIntersection);
				}

				encodable = parser.readObject();//connectingLane
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1ConnectingLane connectingLane=ASN1ConnectingLane.parse(primitive.getEncoded());
					obj.setConnectingLane(connectingLane);
				}

				encodable = parser.readObject();//phaseId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setPhaseId((ASN1Integer) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}

	public ASN1NodeReferenceID getRemoteIntersection() {
		return remoteIntersection;
	}

	public void setRemoteIntersection(ASN1NodeReferenceID remoteIntersection) {
		this.remoteIntersection = remoteIntersection;
	}

	public ASN1ConnectingLane getConnectingLane() {
		return connectingLane;
	}

	public void setConnectingLane(ASN1ConnectingLane connectingLane) {
		this.connectingLane = connectingLane;
	}

	public ASN1Integer getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(ASN1Integer phaseId) {
		this.phaseId = phaseId;
	}
	
	

}
