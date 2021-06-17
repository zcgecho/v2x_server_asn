package com.caeri.v2x.asn.rsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

import com.caeri.v2x.asn.bsm.ASN1PositionOffsetLLV;

/**
 * RTSData ::= SEQUENCE {
		rtsId INTEGER (0..255),
		-- local ID of this rts information set by RSU
		signType SignType,
		-- Type of sign, according to China GB 5768.2
		signPos PositionOffsetLLV OPTIONAL,
		-- Position of the traffic sign, if exists
		description Description OPTIONAL,
		-- Additional description to the sign
		timeDetails RSITimeDetails OPTIONAL,
		-- start time or end time when this sign is active, if existed
		priority RSIPriority OPTIONAL,
		-- the urgency of this RSI data, a relative
		-- degree of merit compared with other RSI data
		referencePaths ReferencePathList OPTIONAL,
		-- Related paths of this traffic sign
		referenceLinks ReferenceLinkList OPTIONAL,
		-- Related links of this traffic sign
		...
	}
 * @author 15504
 *
 */
public class ASN1RTSData extends ASN1Object{

	private ASN1Integer rtsId;
	
	private ASN1Integer signType;
	
	private ASN1PositionOffsetLLV signPos;
	
	private ASN1OctetString description;
	
	private ASN1RSITimeDetails timeDetails;
	
	private ASN1OctetString priority;
	
	private ASN1ReferencePathList referencePaths;
	
	private ASN1ReferenceLinkList referenceLinks;
	
	
	public ASN1RTSData() {
		this.rtsId=new ASN1Integer(0);
		this.signType=new ASN1Integer(0);
		this.signPos=new ASN1PositionOffsetLLV();
		this.description=new DEROctetString("".getBytes());
		this.timeDetails=new ASN1RSITimeDetails();
		this.priority=new DEROctetString("".getBytes());
		this.referencePaths=new ASN1ReferencePathList();
		this.referenceLinks=new ASN1ReferenceLinkList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(rtsId);
		vector.add(signType);
		vector.add(signPos);
		vector.add(description);
		vector.add(timeDetails);
		vector.add(priority);
		vector.add(referencePaths);
		vector.add(referenceLinks);

		return new DERSequence(vector);
	}
	
	public static ASN1RTSData parse(byte[] data) throws IOException {
		ASN1RTSData obj=new ASN1RTSData();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;


				encodable = parser.readObject();//rtsId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setRtsId((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//signType
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSignType((ASN1Integer) primitive);
					}
				}
				

				encodable = parser.readObject();//signPos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionOffsetLLV e=ASN1PositionOffsetLLV.parse(primitive.getEncoded());
					obj.setSignPos(e);
				}
				
				
				
				encodable = parser.readObject();//description
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setDescription((DEROctetString) primitive);
					}
				}

				
				encodable = parser.readObject();//timeDetails
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RSITimeDetails e=ASN1RSITimeDetails.parse(primitive.getEncoded());
					obj.setTimeDetails(e);
				}

				encodable = parser.readObject();//priority
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setPriority((DEROctetString) primitive);
					}
				}
				
				

				encodable = parser.readObject();//referencePaths
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1ReferencePathList e=ASN1ReferencePathList.parse(primitive.getEncoded());
					obj.setReferencePaths(e);
				}


				encodable = parser.readObject();//referenceLinks
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1ReferenceLinkList e=ASN1ReferenceLinkList.parse(primitive.getEncoded());
					obj.setReferenceLinks(e);
				}
			}
		}
		ais.close();
		return obj;
	}
	
	

	public ASN1Integer getRtsId() {
		return rtsId;
	}

	public void setRtsId(ASN1Integer rtsId) {
		this.rtsId = rtsId;
	}

	public ASN1Integer getSignType() {
		return signType;
	}

	public void setSignType(ASN1Integer signType) {
		this.signType = signType;
	}

	public ASN1PositionOffsetLLV getSignPos() {
		return signPos;
	}

	public void setSignPos(ASN1PositionOffsetLLV signPos) {
		this.signPos = signPos;
	}

	public ASN1OctetString getDescription() {
		return description;
	}

	public void setDescription(ASN1OctetString description) {
		this.description = description;
	}

	public ASN1RSITimeDetails getTimeDetails() {
		return timeDetails;
	}

	public void setTimeDetails(ASN1RSITimeDetails timeDetails) {
		this.timeDetails = timeDetails;
	}

	public ASN1OctetString getPriority() {
		return priority;
	}

	public void setPriority(ASN1OctetString priority) {
		this.priority = priority;
	}

	public ASN1ReferencePathList getReferencePaths() {
		return referencePaths;
	}

	public void setReferencePaths(ASN1ReferencePathList referencePaths) {
		this.referencePaths = referencePaths;
	}

	public ASN1ReferenceLinkList getReferenceLinks() {
		return referenceLinks;
	}

	public void setReferenceLinks(ASN1ReferenceLinkList referenceLinks) {
		this.referenceLinks = referenceLinks;
	}


	
	
	

}
