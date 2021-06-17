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
 * ASN1MovementList
 * @author 15504
 *
 */
public class ASN1MovementList extends ASN1Object{
	private List<ASN1Movement> movements;
	
	public ASN1MovementList() {
		this.movements=new ArrayList<ASN1Movement>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<movements.size();i++) {
			vector.add(movements.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1MovementList parse(byte[] data) throws IOException {
		ASN1MovementList obj=new ASN1MovementList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//movements
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Movement e=ASN1Movement.parse(primitive.getEncoded());
					obj.getMovements().add(e);
				}
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<ASN1Movement> movements) {
		this.movements = movements;
	}
}
