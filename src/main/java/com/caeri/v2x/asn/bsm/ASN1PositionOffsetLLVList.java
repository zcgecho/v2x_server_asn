package com.caeri.v2x.asn.bsm;

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
public class ASN1PositionOffsetLLVList extends ASN1Object{

	
	private List<ASN1PositionOffsetLLV> positionOffsetLLVs;
	
	
	public ASN1PositionOffsetLLVList() {
		this.positionOffsetLLVs=new ArrayList<ASN1PositionOffsetLLV>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<positionOffsetLLVs.size();i++) {
			vector.add(positionOffsetLLVs.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1PositionOffsetLLVList parse(byte[] data) throws IOException {
		ASN1PositionOffsetLLVList obj=new ASN1PositionOffsetLLVList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//positionOffsetLLVs
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionOffsetLLV e=ASN1PositionOffsetLLV.parse(primitive.getEncoded());
					obj.getPositionOffsetLLVs().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1PositionOffsetLLV> getPositionOffsetLLVs() {
		return positionOffsetLLVs;
	}

	public void setPositionOffsetLLVs(List<ASN1PositionOffsetLLV> positionOffsetLLVs) {
		this.positionOffsetLLVs = positionOffsetLLVs;
	}

	

	
	

}
