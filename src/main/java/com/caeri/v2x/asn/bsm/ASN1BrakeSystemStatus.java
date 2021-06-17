package com.caeri.v2x.asn.bsm;

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

/**
 * BrakeSystemStatus ::= SEQUENCE {
	brakePadel BrakePedalStatus OPTIONAL,
	wheelBrakes BrakeAppliedStatus OPTIONAL,
	traction TractionControlStatus OPTIONAL,
	abs AntiLockBrakeStatus OPTIONAL,
	scs StabilityControlStatus OPTIONAL,
	brakeBoost BrakeBoostApplied OPTIONAL,
	auxBrakes AuxiliaryBrakeStatus OPTIONAL
}
 */
public class ASN1BrakeSystemStatus extends ASN1Object{
	
	private ASN1Enumerated brakePadel;
	
	private ASN1BitString wheelBrakes;
	
	private ASN1Enumerated traction;
	
	private ASN1Enumerated abs;
	
	private ASN1Enumerated scs;
	
	private ASN1Enumerated brakeBoost;
	
	private ASN1Enumerated auxBrakes;
	
	public ASN1BrakeSystemStatus(){
		this.brakePadel=new ASN1Enumerated(0);
		this.wheelBrakes=new DERBitString("".getBytes());
		this.traction=new ASN1Enumerated(0);
		this.abs=new ASN1Enumerated(0);
		this.scs=new ASN1Enumerated(0);
		this.brakeBoost=new ASN1Enumerated(0);
		this.auxBrakes=new ASN1Enumerated(0);
	}
	
	
	public ASN1Enumerated getBrakePadel() {
		return brakePadel;
	}




	public void setBrakePadel(ASN1Enumerated brakePadel) {
		this.brakePadel = brakePadel;
	}




	public ASN1BitString getWheelBrakes() {
		return wheelBrakes;
	}




	public void setWheelBrakes(ASN1BitString wheelBrakes) {
		this.wheelBrakes = wheelBrakes;
	}




	public ASN1Enumerated getTraction() {
		return traction;
	}




	public void setTraction(ASN1Enumerated traction) {
		this.traction = traction;
	}




	public ASN1Enumerated getAbs() {
		return abs;
	}




	public void setAbs(ASN1Enumerated abs) {
		this.abs = abs;
	}




	public ASN1Enumerated getScs() {
		return scs;
	}




	public void setScs(ASN1Enumerated scs) {
		this.scs = scs;
	}




	public ASN1Enumerated getBrakeBoost() {
		return brakeBoost;
	}




	public void setBrakeBoost(ASN1Enumerated brakeBoost) {
		this.brakeBoost = brakeBoost;
	}




	public ASN1Enumerated getAuxBrakes() {
		return auxBrakes;
	}




	public void setAuxBrakes(ASN1Enumerated auxBrakes) {
		this.auxBrakes = auxBrakes;
	}




	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(brakePadel);
		vector.add(wheelBrakes);
		vector.add(traction);
		vector.add(abs);
		vector.add(scs);
		vector.add(brakeBoost);
		vector.add(auxBrakes);
		return new DERSequence(vector);
	}
	
	public static ASN1BrakeSystemStatus parse(byte[] data) throws IOException {
		ASN1BrakeSystemStatus obj=new ASN1BrakeSystemStatus();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//brakePadel
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setBrakeBoost((ASN1Enumerated) primitive);
					}
				}
				
				
				encodable = parser.readObject();//wheelBrakes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setWheelBrakes((DERBitString) primitive);
					}
				}

				encodable = parser.readObject();//traction
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setTraction((ASN1Enumerated) primitive);
					}
				}

				encodable = parser.readObject();//abs
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setAbs((ASN1Enumerated) primitive);
					}
				}
				
				encodable = parser.readObject();//scs
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setScs((ASN1Enumerated) primitive);
					}
				}

				encodable = parser.readObject();//brakeBoost
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setBrakeBoost((ASN1Enumerated) primitive);
					}
				}

				encodable = parser.readObject();//auxBrakes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setAuxBrakes((ASN1Enumerated) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}
	
}