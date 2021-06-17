package com.caeri.v2x.asn.spat;

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

import com.caeri.v2x.asn.rsm.ASN1ParticipantDataList;

/**
 * Phase ::= SEQUENCE{
		id PhaseID,
		-- the group id is used to map to lists
		-- of lanes (and their descriptions)
		-- which this MovementState data applies to
		-- see comments in the Remarks for usage details
		phaseStates PhaseStateList
		-- Consisting of sets of movement data with:
		-- a) SignalPhaseState
		-- b) TimeChangeDetails, and
		-- c) AdvisorySpeeds (optional )
		-- Note one or more of the movement events may be for
		-- a future time and that this allows conveying multiple
		-- predictive phase and movement timing for various uses
		-- for the current signal group
	}
 * @author 15504
 *
 */
public class ASN1Phase extends ASN1Object{

	private ASN1Integer id;
	
	private ASN1PhaseStateList phaseStates;
	
	public ASN1Phase() {
		this.id=new ASN1Integer(-1);
		this.phaseStates=new ASN1PhaseStateList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(id);
		vector.add(phaseStates);
		return new DERSequence(vector);
	}
	
	public static ASN1Phase parse(byte[] data) throws IOException {
		ASN1Phase obj=new ASN1Phase();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setId((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//phaseStates
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PhaseStateList e=ASN1PhaseStateList.parse(primitive.getEncoded());
					obj.setPhaseStates(e);
				}
				
			}
		}
		return obj;
	}

	public ASN1Integer getId() {
		return id;
	}

	public void setId(ASN1Integer id) {
		this.id = id;
	}

	public ASN1PhaseStateList getPhaseStates() {
		return phaseStates;
	}

	public void setPhaseStates(ASN1PhaseStateList phaseStates) {
		this.phaseStates = phaseStates;
	}
	

	
}
