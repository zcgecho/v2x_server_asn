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
 * ASN1NodeList
 * @author 15504
 *
 */
public class ASN1NodeList extends ASN1Object{

	
	private List<ASN1Node> nodes;
	
	
	public ASN1NodeList() {
		this.nodes=new ArrayList<ASN1Node>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<nodes.size();i++) {
			vector.add(nodes.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1NodeList parse(byte[] data) throws IOException {
		ASN1NodeList obj=new ASN1NodeList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//nodes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Node e=ASN1Node.parse(primitive.getEncoded());
					obj.getNodes().add(e);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<ASN1Node> nodes) {
		this.nodes = nodes;
	}

	
	

	
	

}
