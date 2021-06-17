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
 * Lane ::= SEQUENCE {
		laneID LaneID,
		-- The unique ID number assigned
		-- to this lane object
		laneWidth LaneWidth OPTIONAL,
		laneAttributes LaneAttributes OPTIONAL,
		-- Define basic attribute of lane
		maneuvers AllowedManeuvers OPTIONAL,
		-- the permitted maneuvers for this lane
		connectsTo ConnectsToList OPTIONAL,	
		-- connection to downsteam lanes
		speedLimits SpeedLimitList OPTIONAL,
		-- List all the speed limits
		points PointList OPTIONAL,
		-- Define road points and segments
		...
	}
 * @author 15504
 *
 */
public class ASN1Lane extends ASN1Object{

	private ASN1Integer laneID;
	
	private ASN1Integer laneWidth;
	
	private ASN1LaneAttributes laneAttributes;
	
	private ASN1BitString maneuvers;
	
	private ASN1ConnectionList connectsTo;
	
	private ASN1RegulatorySpeedLimitList speedLimits;
	
	private ASN1RoadPointList points;
	
	public ASN1Lane() {
		this.laneID=new ASN1Integer(0);
		this.laneWidth=new ASN1Integer(0);
		this.laneAttributes=new ASN1LaneAttributes();
		this.maneuvers=new DERBitString("".getBytes());
		this.connectsTo=new ASN1ConnectionList();
		this.speedLimits=new ASN1RegulatorySpeedLimitList();
		this.points=new ASN1RoadPointList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(laneID);
		vector.add(laneWidth);
		vector.add(laneAttributes);
		vector.add(maneuvers);
		vector.add(connectsTo);
		vector.add(speedLimits);
		vector.add(points);
		return new DERSequence(vector);
	}

	
	public static ASN1Lane parse(byte[] data) throws IOException {
		ASN1Lane obj=new ASN1Lane();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;
				
				
				encodable = parser.readObject();//laneID
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLaneID((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//laneWidth
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLaneWidth((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//laneAttributes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1LaneAttributes pathPrediction=ASN1LaneAttributes.parse(primitive.getEncoded());
					obj.setLaneAttributes(pathPrediction);
				}

				
				encodable = parser.readObject();//maneuvers
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1BitString) {
						obj.setManeuvers ((ASN1BitString) primitive);
					}
				}

				encodable = parser.readObject();//connectsTo
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1ConnectionList connectsTo=ASN1ConnectionList.parse(primitive.getEncoded());
					obj.setConnectsTo(connectsTo);
				}
				
				encodable = parser.readObject();//speedLimits
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RegulatorySpeedLimitList speedLimits=ASN1RegulatorySpeedLimitList.parse(primitive.getEncoded());
					obj.setSpeedLimits(speedLimits);
				}
				
				encodable = parser.readObject();//points
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RoadPointList speedLimits=ASN1RoadPointList.parse(primitive.getEncoded());
					obj.setPoints(speedLimits);
				}
			}
		}
		ais.close();
		return obj;
	}
	
	public ASN1Integer getLaneID() {
		return laneID;
	}

	public void setLaneID(ASN1Integer laneID) {
		this.laneID = laneID;
	}

	public ASN1Integer getLaneWidth() {
		return laneWidth;
	}

	public void setLaneWidth(ASN1Integer laneWidth) {
		this.laneWidth = laneWidth;
	}

	public ASN1LaneAttributes getLaneAttributes() {
		return laneAttributes;
	}

	public void setLaneAttributes(ASN1LaneAttributes laneAttributes) {
		this.laneAttributes = laneAttributes;
	}

	public ASN1BitString getManeuvers() {
		return maneuvers;
	}

	public void setManeuvers(ASN1BitString maneuvers) {
		this.maneuvers = maneuvers;
	}

	public ASN1ConnectionList getConnectsTo() {
		return connectsTo;
	}

	public void setConnectsTo(ASN1ConnectionList connectsTo) {
		this.connectsTo = connectsTo;
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

}
