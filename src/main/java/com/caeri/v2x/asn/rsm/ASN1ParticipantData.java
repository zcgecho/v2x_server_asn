package com.caeri.v2x.asn.rsm;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

import com.caeri.v2x.asn.bsm.ASN1AccelerationSet4Way;
import com.caeri.v2x.asn.bsm.ASN1MotionConfidenceSet;
import com.caeri.v2x.asn.bsm.ASN1PositionConfidenceSet;
import com.caeri.v2x.asn.bsm.ASN1PositionOffsetLLV;
import com.caeri.v2x.asn.bsm.ASN1VehicleClassification;
import com.caeri.v2x.asn.bsm.ASN1VehicleSize;

/**
 * ParticipantData ::= SEQUENCE {
		ptcType ParticipantType,
		ptcId INTEGER (0..65535),
		-- temporary ID set by RSU
		-- 0 is RSU itself
		-- 1..255 represent participants detected by RSU
		-- ptcId of different participant needs to be unique in RSU
		source SourceType,
		id OCTET STRING (SIZE(8)) OPTIONAL,
		-- temperary vehicle ID from BSM
		secMark DSecond,
		pos PositionOffsetLLV,
		posConfidence PositionConfidenceSet,
		transmission TransmissionState OPTIONAL,
		speed Speed,
		heading Heading,
		angle SteeringWheelAngle OPTIONAL,
		motionCfd MotionConfidenceSet OPTIONAL,
		accelSet AccelerationSet4Way OPTIONAL,
		size VehicleSize,
		-- Size of participant including motor/non-motor/pedestrian/rsu
		-- is represented by DE_VehilceSize
		vehicleClass VehicleClassification OPTIONAL,
		...
	}
 * @author 15504
 *
 */
public class ASN1ParticipantData  extends ASN1Object{

	private ASN1Enumerated ptcType;
	
	private ASN1Integer ptcId;
	
	private ASN1Enumerated source;
	
	private ASN1OctetString id;
	
	private ASN1Integer secMark;
	
	private ASN1PositionOffsetLLV pos;
	
	private ASN1PositionConfidenceSet posConfidence;
	
	private ASN1Enumerated transmission;
	
	private ASN1Integer speed;
	
	private ASN1Integer heading;
	
	private ASN1Integer angle;
	
	private ASN1MotionConfidenceSet motionCfd;
	
	private ASN1AccelerationSet4Way accelSet;
	
	private ASN1VehicleSize size;
	
	private ASN1VehicleClassification vehicleClass;
	
	
	public ASN1ParticipantData() {
		this.ptcType=new ASN1Enumerated(0);
		this.ptcId=new ASN1Integer(0);
		this.source=new ASN1Enumerated(0);
		this.id=new DEROctetString("".getBytes());
		this.secMark=new ASN1Integer(0);
		this.pos=new ASN1PositionOffsetLLV();
		this.posConfidence=new ASN1PositionConfidenceSet();
		this.transmission=new ASN1Enumerated(0);
		this.speed=new ASN1Integer(0);
		this.heading=new ASN1Integer(0);
		this.angle=new ASN1Integer(0);
		this.motionCfd=new ASN1MotionConfidenceSet();
		this.accelSet=new ASN1AccelerationSet4Way();
		this.size=new ASN1VehicleSize();
		this.vehicleClass=new ASN1VehicleClassification();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(ptcType);
		vector.add(ptcId);
		vector.add(source);
		vector.add(id);
		vector.add(secMark);
		vector.add(pos);
		vector.add(posConfidence);
		vector.add(transmission);
		vector.add(speed);
		vector.add(heading);
		vector.add(angle);
		vector.add(motionCfd);
		vector.add(accelSet);
		vector.add(size);
		vector.add(vehicleClass);
		return new DERSequence(vector);
	}
	
	public static ASN1ParticipantData parse(byte[] data) throws IOException {
		ASN1ParticipantData obj=new ASN1ParticipantData();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//ptcType
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setPtcType((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//ptcId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setPtcId((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//source
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setSource((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setId((DEROctetString) primitive);
					}
				}
				
				encodable = parser.readObject();//secMark
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSecMark((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//secMark
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSecMark((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//pos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionOffsetLLV pos=ASN1PositionOffsetLLV.parse(primitive.getEncoded());
					obj.setPos(pos);
				}
				
				encodable = parser.readObject();//posConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionConfidenceSet posConfidence=ASN1PositionConfidenceSet.parse(primitive.getEncoded());
					obj.setPosConfidence(posConfidence);
				}

				encodable = parser.readObject();//transmission
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
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
				
				encodable = parser.readObject();//heading
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setHeading((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//angle
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setAngle((ASN1Integer) primitive);
					}
				}

				
				encodable = parser.readObject();//motionCfd
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1MotionConfidenceSet motionCfd=ASN1MotionConfidenceSet.parse(primitive.getEncoded());
					obj.setMotionCfd(motionCfd);
				}
				
				encodable = parser.readObject();//accelSet
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1AccelerationSet4Way accelSet=ASN1AccelerationSet4Way.parse(primitive.getEncoded());
					obj.setAccelSet(accelSet);
				}
				
				encodable = parser.readObject();//size
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1VehicleSize size=ASN1VehicleSize.parse(primitive.getEncoded());
					obj.setSize(size);
				}

				
				encodable = parser.readObject();//vehicleClass
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1VehicleClassification vehicleClass=ASN1VehicleClassification.parse(primitive.getEncoded());
					obj.setVehicleClass(vehicleClass);
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1Enumerated getPtcType() {
		return ptcType;
	}

	public void setPtcType(ASN1Enumerated ptcType) {
		this.ptcType = ptcType;
	}

	public ASN1Integer getPtcId() {
		return ptcId;
	}

	public void setPtcId(ASN1Integer ptcId) {
		this.ptcId = ptcId;
	}

	public ASN1Enumerated getSource() {
		return source;
	}

	public void setSource(ASN1Enumerated source) {
		this.source = source;
	}

	public ASN1OctetString getId() {
		return id;
	}

	public void setId(ASN1OctetString id) {
		this.id = id;
	}

	public ASN1Integer getSecMark() {
		return secMark;
	}

	public void setSecMark(ASN1Integer secMark) {
		this.secMark = secMark;
	}

	public ASN1PositionOffsetLLV getPos() {
		return pos;
	}

	public void setPos(ASN1PositionOffsetLLV pos) {
		this.pos = pos;
	}

	public ASN1PositionConfidenceSet getPosConfidence() {
		return posConfidence;
	}

	public void setPosConfidence(ASN1PositionConfidenceSet posConfidence) {
		this.posConfidence = posConfidence;
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

	public ASN1Integer getHeading() {
		return heading;
	}

	public void setHeading(ASN1Integer heading) {
		this.heading = heading;
	}

	public ASN1Integer getAngle() {
		return angle;
	}

	public void setAngle(ASN1Integer angle) {
		this.angle = angle;
	}

	public ASN1MotionConfidenceSet getMotionCfd() {
		return motionCfd;
	}

	public void setMotionCfd(ASN1MotionConfidenceSet motionCfd) {
		this.motionCfd = motionCfd;
	}

	public ASN1AccelerationSet4Way getAccelSet() {
		return accelSet;
	}

	public void setAccelSet(ASN1AccelerationSet4Way accelSet) {
		this.accelSet = accelSet;
	}

	public ASN1VehicleSize getSize() {
		return size;
	}

	public void setSize(ASN1VehicleSize size) {
		this.size = size;
	}

	public ASN1VehicleClassification getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(ASN1VehicleClassification vehicleClass) {
		this.vehicleClass = vehicleClass;
	}
	
	
	public static void main(String[] args) throws IOException {
		ASN1ParticipantData obj=new ASN1ParticipantData();
		ASN1ParticipantData d=ASN1ParticipantData.parse(obj.getEncoded());
		System.out.println(d.getPtcId().getValue());
	}

}
