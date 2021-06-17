package com.caeri.v2x.asn.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

import com.caeri.v2x.asn.bsm.ASN1Position3D;

/**
 * Node ::= SEQUENCE {
		-- intersection or road endpoint
		name DescriptiveName OPTIONAL,
		-- Node name
		id NodeReferenceID,
		-- A globally unique value set,
		-- consisting of a regionID and
		-- node ID assignment
		refPos Position3D, 
		-- 3D position of the center of this Node.
		-- This position is also the reference position for the elements inside
		inLinks LinkList OPTIONAL,
		-- all the links enter this Node
		...
	}
 * @author 15504
 *
 */
public class ASN1Node extends ASN1Object {

	
	private ASN1OctetString name;
	
	private ASN1NodeReferenceID id;
	
	private ASN1Position3D refPos;
	
	private ASN1LinkList inLinks;
	
	public ASN1Node() {
		this.name=new DEROctetString("".getBytes());
		this.id=new ASN1NodeReferenceID();
		this.refPos=new ASN1Position3D();
		this.inLinks=new ASN1LinkList();
		
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(name);
		vector.add(id);
		vector.add(refPos);
		vector.add(inLinks);
		
		return new DERSequence(vector);
	}
	
	public static ASN1Node parse(byte[] data) throws IOException {
		ASN1Node obj=new ASN1Node();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;
				
				
				encodable = parser.readObject();//name
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setName((DEROctetString) primitive);
					}
				}

			
				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeReferenceID id=ASN1NodeReferenceID.parse(primitive.getEncoded());
					obj.setId(id);
				}

				encodable = parser.readObject();//refpos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Position3D pos=ASN1Position3D.parse(primitive.getEncoded());
					obj.setRefPos(pos);
				}

				encodable = parser.readObject();//inLinks
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1LinkList inLinks=ASN1LinkList.parse(primitive.getEncoded());
					obj.setInLinks(inLinks);
				}
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1OctetString getName() {
		return name;
	}

	public void setName(ASN1OctetString name) {
		this.name = name;
	}

	public ASN1NodeReferenceID getId() {
		return id;
	}

	public void setId(ASN1NodeReferenceID id) {
		this.id = id;
	}

	public ASN1Position3D getRefPos() {
		return refPos;
	}

	public void setRefPos(ASN1Position3D refPos) {
		this.refPos = refPos;
	}

	public ASN1LinkList getInLinks() {
		return inLinks;
	}

	public void setInLinks(ASN1LinkList inLinks) {
		this.inLinks = inLinks;
	}

}
