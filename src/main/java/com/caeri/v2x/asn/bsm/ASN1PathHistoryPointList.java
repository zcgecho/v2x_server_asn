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
public class ASN1PathHistoryPointList extends ASN1Object{

	
	private List<ASN1PathHistoryPoint> points;
	
	
	public ASN1PathHistoryPointList() {
		this.points=new ArrayList<ASN1PathHistoryPoint>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<points.size();i++) {
			vector.add(points.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1PathHistoryPointList parse(byte[] data) throws IOException {
		ASN1PathHistoryPointList obj=new ASN1PathHistoryPointList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//points
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PathHistoryPoint e=ASN1PathHistoryPoint.parse(primitive.getEncoded());
					obj.getPoints().add(e);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1PathHistoryPoint> getPoints() {
		return points;
	}

	public void setPoints(List<ASN1PathHistoryPoint> points) {
		this.points = points;
	}

	
	

}
