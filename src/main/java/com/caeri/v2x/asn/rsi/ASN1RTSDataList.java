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
 * ASN1RTSDataList
 * @author 15504
 *
 */
public class ASN1RTSDataList extends ASN1Object{

	
	private List<ASN1RTSData> rtsdatas;
	
	
	public ASN1RTSDataList() {
		this.rtsdatas=new ArrayList<ASN1RTSData>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<rtsdatas.size();i++) {
			vector.add(rtsdatas.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1RTSDataList parse(byte[] data) throws IOException {
		ASN1RTSDataList obj=new ASN1RTSDataList();
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
					ASN1RTSData e=ASN1RTSData.parse(primitive.getEncoded());
					obj.getRtsdatas().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1RTSData> getRtsdatas() {
		return rtsdatas;
	}

	public void setRtsdatas(List<ASN1RTSData> rtsdatas) {
		this.rtsdatas = rtsdatas;
	}



	

	
}
