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
 * LaneAttributes ::= SEQUENCE {
		shareWith LaneSharing OPTIONAL,
		laneType LaneTypeAttributes
	}
	LaneSharing ::= BIT STRING {
		-- With bits as defined:
		overlappingLaneDescriptionProvided (0),
		-- Assert when another lane object is present to describe the
		-- path of the overlapping shared lane
		-- this construct is not used for lane objects which simply cross
		multipleLanesTreatedAsOneLane (1),
		-- Assert if the lane object path and width details represents
		-- multiple lanes within it that are not further described
		-- Various modes and type of traffic that may share this lane:
		otherNonMotorizedTrafficTypes (2), -- horse drawn etc.
		individualMotorizedVehicleTraffic (3),
		busVehicleTraffic (4),
		taxiVehicleTraffic (5),
		pedestriansTraffic (6),
		cyclistVehicleTraffic (7),
		trackedVehicleTraffic (8),
		pedestrianTraffic (9)
		} (SIZE (10))
		-- All zeros would indicate 'not shared' and 'not overlapping'
		
	LaneTypeAttributes ::= CHOICE {
		vehicle LaneAttributes-Vehicle, -- motor vehicle lanes
		crosswalk LaneAttributes-Crosswalk, -- pedestrian crosswalks
		bikeLane LaneAttributes-Bike, -- bike lanes
		sidewalk LaneAttributes-Sidewalk, -- pedestrian sidewalk paths
		median LaneAttributes-Barrier, -- medians & channelization
		striping LaneAttributes-Striping, -- roadway markings
		trackedVehicle LaneAttributes-TrackedVehicle, -- trains and trolleys
		parking LaneAttributes-Parking, -- parking and stopping lanes
		...
		}
 * @author 15504
 *
 */
public class ASN1LaneAttributes extends ASN1Object{

	private ASN1BitString shareWith;
	
	private ASN1BitString laneType;
	
	public ASN1LaneAttributes() {
		this.shareWith=new DERBitString("".getBytes());
		this.laneType=new DERBitString("".getBytes());
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(shareWith);
		vector.add(laneType);
		return new DERSequence(vector);
	}

	
	public static ASN1LaneAttributes parse(byte[] data) throws IOException {
		ASN1LaneAttributes obj=new ASN1LaneAttributes();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//shareWith
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setShareWith((DERBitString) primitive);
					}
				}

				encodable = parser.readObject();//laneType
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setLaneType((DERBitString) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}
	
	
	public ASN1BitString getShareWith() {
		return shareWith;
	}

	public void setShareWith(ASN1BitString shareWith) {
		this.shareWith = shareWith;
	}

	public ASN1BitString getLaneType() {
		return laneType;
	}

	public void setLaneType(ASN1BitString laneType) {
		this.laneType = laneType;
	}
	
	
	

}
