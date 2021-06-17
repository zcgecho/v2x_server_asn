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
public class ASN1ReferencePathList extends ASN1Object{

	
	private List<ASN1ReferencePath> referencePaths;
	
	
	public ASN1ReferencePathList() {
		this.referencePaths=new ArrayList<ASN1ReferencePath>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<referencePaths.size();i++) {
			vector.add(referencePaths.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1ReferencePathList parse(byte[] data) throws IOException {
		ASN1ReferencePathList obj=new ASN1ReferencePathList();
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
					ASN1ReferencePath e=ASN1ReferencePath.parse(primitive.getEncoded());
					obj.getReferencePaths().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1ReferencePath> getReferencePaths() {
		return referencePaths;
	}

	public void setReferencePaths(List<ASN1ReferencePath> referencePaths) {
		this.referencePaths = referencePaths;
	}


}
