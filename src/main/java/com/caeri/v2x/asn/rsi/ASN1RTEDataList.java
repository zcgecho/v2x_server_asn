package com.caeri.v2x.asn.rsi;

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
 * ASN1RoadPointList
 * @author 15504
 *
 */
public class ASN1RTEDataList extends ASN1Object{

	
	private List<ASN1RTEData> rtedatas;
	
	
	public ASN1RTEDataList() {
		this.rtedatas=new ArrayList<ASN1RTEData>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<rtedatas.size();i++) {
			vector.add(rtedatas.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1RTEDataList parse(byte[] data) throws IOException {
		ASN1RTEDataList obj=new ASN1RTEDataList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//referencePaths
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RTEData e=ASN1RTEData.parse(primitive.getEncoded());
					obj.getRtedatas().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1RTEData> getRtedatas() {
		return rtedatas;
	}

	public void setRtedatas(List<ASN1RTEData> rtedatas) {
		this.rtedatas = rtedatas;
	}

	

	
}
