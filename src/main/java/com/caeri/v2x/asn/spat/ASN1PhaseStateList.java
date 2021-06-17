package com.caeri.v2x.asn.spat;

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
 * ASN1PhaseStateList
 * @author 15504
 *
 */
public class ASN1PhaseStateList extends ASN1Object{

	
	private List<ASN1PhaseState> phaseStates;
	
	
	public ASN1PhaseStateList() {
		this.phaseStates=new ArrayList<ASN1PhaseState>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<phaseStates.size();i++) {
			vector.add(phaseStates.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1PhaseStateList parse(byte[] data) throws IOException {
		ASN1PhaseStateList obj=new ASN1PhaseStateList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//participants
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PhaseState e=ASN1PhaseState.parse(primitive.getEncoded());
					obj.getPhaseStates().add(e);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1PhaseState> getPhaseStates() {
		return phaseStates;
	}

	public void setPhaseStates(List<ASN1PhaseState> phaseStates) {
		this.phaseStates = phaseStates;
	}

	
	
}
