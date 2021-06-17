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
 * ASN1ParticipantDataList
 * @author 15504
 *
 */
public class ASN1ConnectionList extends ASN1Object{

	private List<ASN1Connection> connections;
	
	public ASN1ConnectionList() {
		this.connections=new ArrayList<ASN1Connection>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<connections.size();i++) {
			vector.add(connections.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1ConnectionList parse(byte[] data) throws IOException {
		ASN1ConnectionList obj=new ASN1ConnectionList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//connections
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Connection e=ASN1Connection.parse(primitive.getEncoded());
					obj.getConnections().add(e);
				}
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1Connection> getConnections() {
		return connections;
	}

	public void setConnections(List<ASN1Connection> connections) {
		this.connections = connections;
	}

	
	

	
	

}
