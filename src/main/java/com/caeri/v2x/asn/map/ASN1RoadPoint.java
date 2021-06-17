package com.caeri.v2x.asn.map;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;


/**
 * RoadPoint ::= SEQUENCE {
		posOffset PositionOffsetLLV,
		-- Position offset to the reference position
		--pointAttribute PointAttributeSet OPTIONAL,
		-- Definition of special attribute of road-point
		--segmentAttribute SegmentAttributeSet OPTIONAL,
		-- Definition of special attribute of road segment from this road-point to the next
		-- if this node is the last node of this nodelist, then this segment means null
		...
	}
 * @author 15504
 *
 */
public class ASN1RoadPoint extends ASN1Object{

	
	//todo，属性待定
	
	
	public ASN1RoadPoint() {
		
	}
	
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		
		return new DERSequence(vector);
	}

	public static ASN1RoadPoint parse(byte[] data) throws IOException {
		ASN1RoadPoint obj=new ASN1RoadPoint();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
//						obj.setMsgCnt((ASN1Integer) primitive);
					}
				}
				
				
			}
		}
		ais.close();
		return obj;
	}
	
}
