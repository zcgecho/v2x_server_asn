package com.caeri.v2x.asn.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;

/**
 * ASN1ParticipantDataList
 * @author 15504
 *
 */
public class ASN1RegulatorySpeedLimitList extends ASN1Object{

	
	private List<ASN1RegulatorySpeedLimit> speedLimits;
	
	
	public ASN1RegulatorySpeedLimitList() {
		this.speedLimits=new ArrayList<ASN1RegulatorySpeedLimit>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<speedLimits.size();i++) {
			vector.add(speedLimits.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1RegulatorySpeedLimitList parse(byte[] data) throws IOException {
		ASN1RegulatorySpeedLimitList obj=new ASN1RegulatorySpeedLimitList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//speedLimits
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RegulatorySpeedLimit e=ASN1RegulatorySpeedLimit.parse(primitive.getEncoded());
					obj.getSpeedLimits().add(e);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1RegulatorySpeedLimit> getSpeedLimits() {
		return speedLimits;
	}

	public void setSpeedLimits(List<ASN1RegulatorySpeedLimit> speedLimits) {
		this.speedLimits = speedLimits;
	}

	

	
	

}
