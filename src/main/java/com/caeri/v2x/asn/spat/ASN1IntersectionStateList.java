package com.caeri.v2x.asn.spat;

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
public class ASN1IntersectionStateList extends ASN1Object{

	
	private List<ASN1IntersectionState> intersections;
	
	
	public ASN1IntersectionStateList() {
		this.intersections=new ArrayList<ASN1IntersectionState>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<intersections.size();i++) {
			vector.add(intersections.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1IntersectionStateList parse(byte[] data) throws IOException {
		ASN1IntersectionStateList obj=new ASN1IntersectionStateList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//participants
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1IntersectionState e=ASN1IntersectionState.parse(primitive.getEncoded());
					obj.getIntersections().add(e);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1IntersectionState> getIntersections() {
		return intersections;
	}

	public void setIntersections(List<ASN1IntersectionState> intersections) {
		this.intersections = intersections;
	}

	
	
	
	

}
