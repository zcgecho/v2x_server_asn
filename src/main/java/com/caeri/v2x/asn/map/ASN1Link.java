package com.caeri.v2x.asn.map;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
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
 * Link ::= SEQUENCE {
		name DescriptiveName OPTIONAL,
		-- Link name
		
		upstreamNodeId NodeReferenceID,
		-- this link is from upstreamNode to the Node it belongs to
		
		speedLimits SpeedLimitList OPTIONAL,
		-- List all the speed limits
		
		linkWidth LaneWidth OPTIONAL,
		-- Width of this link
		
		points PointList OPTIONAL,
		-- Define road points along the center of this link
		
		movements MovementList OPTIONAL,
		-- Define movements at intersection
		
		lanes LaneList,
		-- Lanes belong to this link
		...
	}
 * @author 15504
 *
 */
public class ASN1Link extends ASN1Object{
	
	private ASN1OctetString name;
	
	private ASN1NodeReferenceID upstreamNodeId;
	
	private ASN1RegulatorySpeedLimitList speedLimits;
	
	private ASN1Integer linkWidth;
	
	private ASN1RoadPointList points;
	
	private ASN1MovementList movements;
	
	private ASN1LaneList lanes;
	
	public ASN1Link() {
		this.name=new DEROctetString("".getBytes());
		this.upstreamNodeId=new ASN1NodeReferenceID();
		this.speedLimits=new ASN1RegulatorySpeedLimitList();
		this.linkWidth=new ASN1Integer(0);
		this.points=new ASN1RoadPointList();
		this.movements=new ASN1MovementList();
		this.lanes=new ASN1LaneList();
	}
	

	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(name);
		vector.add(upstreamNodeId);
		vector.add(speedLimits);
		vector.add(linkWidth);
		vector.add(points);
		vector.add(movements);
		vector.add(lanes);
		return new DERSequence(vector);
	}

	public static ASN1Link parse(byte[] data) throws IOException {
		ASN1Link obj=new ASN1Link();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;


				encodable = parser.readObject();//name
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setName((DEROctetString) primitive);
					}
				}
				
				encodable = parser.readObject();//upstreamNodeId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeReferenceID e=ASN1NodeReferenceID.parse(primitive.getEncoded());
					obj.setUpstreamNodeId(e);
				}
				
				encodable = parser.readObject();//speedLimits
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RegulatorySpeedLimitList e=ASN1RegulatorySpeedLimitList.parse(primitive.getEncoded());
					obj.setSpeedLimits(e);
				}


				encodable = parser.readObject();//linkWidth
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLinkWidth((ASN1Integer) primitive);
					}
				}



				encodable = parser.readObject();//points
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RoadPointList e=ASN1RoadPointList.parse(primitive.getEncoded());
					obj.setPoints(e);
				}

				encodable = parser.readObject();//movements
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1MovementList e=ASN1MovementList.parse(primitive.getEncoded());
					obj.setMovements(e);
				}

				encodable = parser.readObject();//lanes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1LaneList e=ASN1LaneList.parse(primitive.getEncoded());
					obj.setLanes(e);
				}
				
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1OctetString getName() {
		return name;
	}


	public void setName(ASN1OctetString name) {
		this.name = name;
	}


	public ASN1NodeReferenceID getUpstreamNodeId() {
		return upstreamNodeId;
	}


	public void setUpstreamNodeId(ASN1NodeReferenceID upstreamNodeId) {
		this.upstreamNodeId = upstreamNodeId;
	}




	public ASN1Integer getLinkWidth() {
		return linkWidth;
	}


	public void setLinkWidth(ASN1Integer linkWidth) {
		this.linkWidth = linkWidth;
	}


	public ASN1RegulatorySpeedLimitList getSpeedLimits() {
		return speedLimits;
	}


	public void setSpeedLimits(ASN1RegulatorySpeedLimitList speedLimits) {
		this.speedLimits = speedLimits;
	}


	public ASN1RoadPointList getPoints() {
		return points;
	}


	public void setPoints(ASN1RoadPointList points) {
		this.points = points;
	}


	public ASN1MovementList getMovements() {
		return movements;
	}


	public void setMovements(ASN1MovementList movements) {
		this.movements = movements;
	}


	public ASN1LaneList getLanes() {
		return lanes;
	}


	public void setLanes(ASN1LaneList lanes) {
		this.lanes = lanes;
	}


	


	

	
}
