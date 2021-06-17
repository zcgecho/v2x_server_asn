package com.caeri.v2x.asn.rsi;

import java.io.IOException;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;

import com.caeri.v2x.asn.bsm.ASN1PositionOffsetLLV;
import com.caeri.v2x.asn.map.ASN1NodeReferenceID;

/**
 * ReferenceLink ::= SEQUENCE {
		-- this Link is from Node of upstreamNodeId to Node of downstreamNodeId
		upstreamNodeId NodeReferenceID,
		downstreamNodeId NodeReferenceID,
		referenceLanes ReferenceLanes OPTIONAL
		-- Provide reference lanes if is necessary
		-- Refer to all lanes if this data is not given
	}
 * @author 15504
 *
 */
public class ASN1ReferenceLink extends ASN1Object{

	private ASN1NodeReferenceID upstreamNodeId;
	
	private ASN1NodeReferenceID downstreamNodeId;
	
	private ASN1BitString referenceLanes;
	
	
	public ASN1ReferenceLink() {
		this.upstreamNodeId=new ASN1NodeReferenceID();
		this.downstreamNodeId=new ASN1NodeReferenceID();
		this.referenceLanes=new DERBitString("".getBytes());
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(upstreamNodeId);
		vector.add(downstreamNodeId);
		vector.add(referenceLanes);
		return new DERSequence(vector);
	}
	
	public static ASN1ReferenceLink parse(byte[] data) throws IOException {
		ASN1ReferenceLink obj=new ASN1ReferenceLink();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				
				encodable = parser.readObject();//upstreamNodeId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeReferenceID upstreamNodeId=ASN1NodeReferenceID.parse(primitive.getEncoded());
					obj.setUpstreamNodeId(upstreamNodeId);
				}

				
				encodable = parser.readObject();//downstreamNodeId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeReferenceID downstreamNodeId=ASN1NodeReferenceID.parse(primitive.getEncoded());
					obj.setDownstreamNodeId(downstreamNodeId);
				}
				
				encodable = parser.readObject();//referenceLanes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setReferenceLanes((DERBitString) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1NodeReferenceID getUpstreamNodeId() {
		return upstreamNodeId;
	}

	public void setUpstreamNodeId(ASN1NodeReferenceID upstreamNodeId) {
		this.upstreamNodeId = upstreamNodeId;
	}

	public ASN1NodeReferenceID getDownstreamNodeId() {
		return downstreamNodeId;
	}

	public void setDownstreamNodeId(ASN1NodeReferenceID downstreamNodeId) {
		this.downstreamNodeId = downstreamNodeId;
	}

	public ASN1BitString getReferenceLanes() {
		return referenceLanes;
	}

	public void setReferenceLanes(ASN1BitString referenceLanes) {
		this.referenceLanes = referenceLanes;
	}
	
	
	

}
