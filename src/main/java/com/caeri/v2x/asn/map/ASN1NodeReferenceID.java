package com.caeri.v2x.asn.map;

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


/**
 * NodeReferenceID ::= SEQUENCE {
		region RoadRegulatorID OPTIONAL,
		-- a globally unique regional assignment value
		-- typical assigned to a regional DOT authority
		-- the value zero shall be used for testing needs
		id NodeID
		-- a unique mapping to the node
		-- in question within the above region of use		
		}
	
	RoadRegulatorID ::= INTEGER (0..65535)
	-- The value zero shall be used for testing only
	
	NodeID ::= INTEGER (0..65535)
	-- The values zero through 255 are allocated for testing purposes 
	-- Note that the value assigned to a node will be 
	-- unique within a given regional ID only
 * @author 15504
 *
 */
public class ASN1NodeReferenceID extends ASN1Object{
	
	private ASN1Integer region;
	
	private ASN1Integer id;
	
	public ASN1NodeReferenceID() {
		this.region=new ASN1Integer(0);
		this.id=new ASN1Integer(0);
	}

	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(region);
		vector.add(id);
		return new DERSequence(vector);
	}
	
	public static ASN1NodeReferenceID parse(byte[] data) throws IOException {
		ASN1NodeReferenceID obj=new ASN1NodeReferenceID();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//region
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setRegion((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setId((ASN1Integer) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}

	public ASN1Integer getRegion() {
		return region;
	}

	public void setRegion(ASN1Integer region) {
		this.region = region;
	}

	public ASN1Integer getId() {
		return id;
	}

	public void setId(ASN1Integer id) {
		this.id = id;
	}
	
	

}
