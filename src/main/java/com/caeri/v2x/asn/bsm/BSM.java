package com.caeri.v2x.asn.bsm;

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


/**
 * 
 * 
 * 
 * 
 * BasicSafetyMessage ::= SEQUENCE { msgCnt MsgCount, id OCTET STRING (SIZE(8)),
 * -- temperary vehicle ID secMark DSecond, timeConfidence TimeConfidence
 * OPTIONAL, pos Position3D, posAccuracy PositionalAccuracy OPTIONAL, --
 * Accuracy for GNSS system posConfidence PositionConfidenceSet OPTIONAL, --
 * Realtime position confidence transmission TransmissionState, speed Speed,
 * heading Heading, angle SteeringWheelAngle OPTIONAL, motionCfd
 * MotionConfidenceSet OPTIONAL, accelSet AccelerationSet4Way, brakes
 * BrakeSystemStatus, size VehicleSize, vehicleClass VehicleClassification, --
 * VehicleClassification includes BasicVehicleClass and other extendible type
 * safetyExt VehicleSafetyExtensions OPTIONAL, emergencyExt
 * VehicleEmergencyExtensions OPTIONAL, token OCTET STRING (SIZE(4)) OPTIONAL,
 * ... }
 * 
 * @author 15504
 *
 */
public class BSM extends ASN1Object {
	
	private String nonce="";//nonce use for AES IV. It is not asn encoded, so is SPAT/Map/RSI/RSM

	private ASN1Integer msgCnt;

	private ASN1OctetString id;

	private ASN1Integer secMark;

	private ASN1Enumerated timeConfidence;

	private ASN1Position3D pos;

	private ASN1PositionalAccuracy posAccuracy;

	private ASN1PositionConfidenceSet posConfidence;

	private ASN1Enumerated transmission;

	private ASN1Integer speed;

	private ASN1Integer head;

	private ASN1Integer angle;

	private ASN1MotionConfidenceSet motionCfd;

	private ASN1AccelerationSet4Way accelSet;

	private ASN1BrakeSystemStatus brakes;

	private ASN1VehicleSize size;

	private ASN1VehicleClassification vehicleClass;

	private ASN1VehicleSafetyExtensions safetyExt;

	private ASN1OctetString token;
	
	private ASN1VehicleEmergencyExtensions emergencyExt;
	
	public BSM() {
		this.setMsgCnt(new ASN1Integer(-1));
		this.setId(new DEROctetString("".getBytes()));
		this.setSecMark(new ASN1Integer(-1));
		this.setTimeConfidence(new ASN1Enumerated(0));
		this.setPos(new ASN1Position3D());
		this.setPosAccuracy(new ASN1PositionalAccuracy());
		this.setPosConfidence(new ASN1PositionConfidenceSet());
		this.setTransmission(new ASN1Enumerated(1));
		this.setSpeed(new ASN1Integer(0));
		this.setHead(new ASN1Integer(0));
		this.setAngle(new ASN1Integer(0));
		this.setMotionCfd(new ASN1MotionConfidenceSet());
		this.setAccelSet(new ASN1AccelerationSet4Way());
		this.setBrakes(new ASN1BrakeSystemStatus());
		this.setSize(new ASN1VehicleSize());
		this.setVehicleClass(new ASN1VehicleClassification());
		this.setSafetyExt(new ASN1VehicleSafetyExtensions());
		this.setToken(new DEROctetString("".getBytes()));
		this.setEmergencyExt(new ASN1VehicleEmergencyExtensions());
		
		
	}
	
	

	public String getNonce() {
		return nonce;
	}



	public void setNonce(String nonce) {
		this.nonce = nonce;
	}



	public ASN1Integer getMsgCnt() {
		return msgCnt;
	}

	public void setMsgCnt(ASN1Integer msgCnt) {
		this.msgCnt = msgCnt;
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

	public ASN1Enumerated getTimeConfidence() {
		return timeConfidence;
	}

	public void setTimeConfidence(ASN1Enumerated timeConfidence) {
		this.timeConfidence = timeConfidence;
	}

	public ASN1Position3D getPos() {
		return pos;
	}

	public void setPos(ASN1Position3D pos) {
		this.pos = pos;
	}

	public ASN1PositionalAccuracy getPosAccuracy() {
		return posAccuracy;
	}

	public void setPosAccuracy(ASN1PositionalAccuracy posAccuracy) {
		this.posAccuracy = posAccuracy;
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

	public ASN1Integer getHead() {
		return head;
	}

	public void setHead(ASN1Integer head) {
		this.head = head;
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

	public ASN1BrakeSystemStatus getBrakes() {
		return brakes;
	}

	public void setBrakes(ASN1BrakeSystemStatus brakes) {
		this.brakes = brakes;
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

	public ASN1VehicleSafetyExtensions getSafetyExt() {
		return safetyExt;
	}

	public void setSafetyExt(ASN1VehicleSafetyExtensions safetyExt) {
		this.safetyExt = safetyExt;
	}

	public ASN1OctetString getToken() {
		return token;
	}

	public void setToken(ASN1OctetString token) {
		this.token = token;
	}
	
	

	public ASN1VehicleEmergencyExtensions getEmergencyExt() {
		return emergencyExt;
	}



	public void setEmergencyExt(ASN1VehicleEmergencyExtensions emergencyExt) {
		this.emergencyExt = emergencyExt;
	}



	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(token);
		vector.add(msgCnt);
		vector.add(id);
		vector.add(secMark);
		vector.add(timeConfidence);
		vector.add(pos);
		vector.add(posAccuracy);
		vector.add(posConfidence);
		vector.add(transmission);
		vector.add(speed);
		vector.add(head);
		vector.add(angle);
		vector.add(motionCfd);
		vector.add(accelSet);
		vector.add(brakes);
		vector.add(size);
		vector.add(vehicleClass);
		vector.add(safetyExt);
		vector.add(emergencyExt);
		return new DERSequence(vector);

	}

	/**
	 * 解析转化为BSM,顺序必须与toASN1Primitive一致
	 * @param data
	 * @throws IOException
	 */
	public static BSM parse(byte[] data) throws IOException {
		BSM bsm=new BSM();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//token
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1OctetString) {
						bsm.setToken((ASN1OctetString) primitive);
					}
				}
				
				encodable = parser.readObject();//获取msgcnt
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						bsm.setMsgCnt((ASN1Integer) primitive);
					}
				}
				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1OctetString) {
						bsm.setId((ASN1OctetString) primitive);
					}
				}
				
				encodable = parser.readObject();//secMark
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						bsm.setSecMark((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//timeConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						bsm.setTimeConfidence((ASN1Enumerated) primitive);
					}
				}
				
				

				encodable = parser.readObject();//pos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Position3D pos=ASN1Position3D.parse(primitive.getEncoded());
					bsm.setPos(pos);
				}
				
				

				encodable = parser.readObject();//posAccuracy
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionalAccuracy posAccuracy=ASN1PositionalAccuracy.parse(primitive.getEncoded());
					bsm.setPosAccuracy(posAccuracy);
				}
				
				

				encodable = parser.readObject();//posConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionConfidenceSet posConfidence=ASN1PositionConfidenceSet.parse(primitive.getEncoded());
					bsm.setPosConfidence(posConfidence);
				}
				
				
				encodable = parser.readObject();//transmission
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated ) {
						bsm.setTransmission((ASN1Enumerated) primitive);
					}
				}
				
				
				encodable = parser.readObject();//speed
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer ) {
						bsm.setSpeed((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//head
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer ) {
						bsm.setHead((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//angle
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer ) {
						bsm.setAngle((ASN1Integer) primitive);
					}
				}
				
				

				encodable = parser.readObject();//motionCfd
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1MotionConfidenceSet motionCfd=ASN1MotionConfidenceSet.parse(primitive.getEncoded());
					bsm.setMotionCfd(motionCfd);
				}
				
				
				

				encodable = parser.readObject();//accelSet
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1AccelerationSet4Way accelSet=ASN1AccelerationSet4Way.parse(primitive.getEncoded());
					bsm.setAccelSet(accelSet);
				}
				
				


				encodable = parser.readObject();//brakes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1BrakeSystemStatus brakes=ASN1BrakeSystemStatus.parse(primitive.getEncoded());
					bsm.setBrakes(brakes);
				}
				
				
				
//				
				

				encodable = parser.readObject();//size
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1VehicleSize size=ASN1VehicleSize.parse(primitive.getEncoded());
					bsm.setSize(size);
				}
				
				
//				
				


				encodable = parser.readObject();//vehicleClass
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1VehicleClassification vehicleClass=ASN1VehicleClassification.parse(primitive.getEncoded());
					bsm.setVehicleClass(vehicleClass);
				}

				encodable = parser.readObject();//safetyExt
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1VehicleSafetyExtensions safetyExt=ASN1VehicleSafetyExtensions.parse(primitive.getEncoded());
					bsm.setSafetyExt(safetyExt);
				}
				
				encodable = parser.readObject();//emergencyExt
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1VehicleEmergencyExtensions emergencyExt=ASN1VehicleEmergencyExtensions.parse(primitive.getEncoded());
					bsm.setEmergencyExt(emergencyExt);
				}
				
			}
		}
		ais.close();
		return bsm;
	}

}
