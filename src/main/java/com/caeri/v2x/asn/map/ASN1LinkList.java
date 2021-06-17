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
 * ASN1RoadPointList
 * @author 15504
 *
 */
public class ASN1LinkList extends ASN1Object{

	
	private List<ASN1Link> links;
	
	
	public ASN1LinkList() {
		this.links=new ArrayList<ASN1Link>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<links.size();i++) {
			vector.add(links.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1LinkList parse(byte[] data) throws IOException {
		ASN1LinkList obj=new ASN1LinkList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//links
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Link e=ASN1Link.parse(primitive.getEncoded());
					obj.getLinks().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1Link> getLinks() {
		return links;
	}

	public void setLinks(List<ASN1Link> links) {
		this.links = links;
	}

	

}
