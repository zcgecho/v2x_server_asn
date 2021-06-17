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
 * ASN1RoadPoint
 * @author 15504
 *
 */
public class ASN1LaneList extends ASN1Object{

	
	private List<ASN1Lane> lanes;
	
	
	public ASN1LaneList() {
		this.lanes=new ArrayList<ASN1Lane>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<lanes.size();i++) {
			vector.add(lanes.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1LaneList parse(byte[] data) throws IOException {
		ASN1LaneList obj=new ASN1LaneList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//lanes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Lane e=ASN1Lane.parse(primitive.getEncoded());
					obj.getLanes().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1Lane> getLanes() {
		return lanes;
	}

	public void setLanes(List<ASN1Lane> lanes) {
		this.lanes = lanes;
	}

	

}
