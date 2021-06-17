package com.caeri.v2x.asn.bsm;

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
 * PositionOffsetLLV ::= SEQUENCE {
	offsetLL PositionOffsetLL,
	-- offset in lon/lat
	offsetV VerticalOffset OPTIONAL
	-- offset in elevation
}

 */
public class ASN1PositionOffsetLLV extends ASN1Object{

	private ASN1Integer offsetLL;
	
	
	private ASN1Integer offsetV;
	
	
	public ASN1PositionOffsetLLV() {
		this.offsetLL=new ASN1Integer(0);
		this.offsetV=new ASN1Integer(0);
	}
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(offsetLL);
		vector.add(offsetV);
		return new DERSequence(vector);
	}
	
	public static ASN1PositionOffsetLLV parse(byte[] data) throws IOException {
		ASN1PositionOffsetLLV obj=new ASN1PositionOffsetLLV();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//offsetLL
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setOffsetLL((ASN1Integer) primitive);
					}
				}
				encodable = parser.readObject();//offsetV
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setOffsetV((ASN1Integer) primitive);
					}
				}
				
				
			}
		}
		return obj;
	}
	


	public ASN1Integer getOffsetLL() {
		return offsetLL;
	}


	public void setOffsetLL(ASN1Integer offsetLL) {
		this.offsetLL = offsetLL;
	}


	public ASN1Integer getOffsetV() {
		return offsetV;
	}


	public void setOffsetV(ASN1Integer offsetV) {
		this.offsetV = offsetV;
	}
	
}