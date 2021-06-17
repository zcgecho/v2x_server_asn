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
import com.caeri.v2x.asn.map.ASN1LaneList;
import com.caeri.v2x.asn.map.ASN1Link;
import com.caeri.v2x.asn.map.ASN1MovementList;
import com.caeri.v2x.asn.map.ASN1NodeReferenceID;
import com.caeri.v2x.asn.map.ASN1RegulatorySpeedLimitList;
import com.caeri.v2x.asn.map.ASN1RoadPointList;

/**
 * RTEData ::= SEQUENCE {
		rteId INTEGER (0..255),
		-- local ID of this rte information set by RSU
		eventType EventType,
		-- Type of event, according to China GB/T 29100-2012
		eventSource EventSource,
		eventPos PositionOffsetLLV OPTIONAL,
		-- Position of this event, if exists
		eventRadius Radius OPTIONAL,
		-- Radius of this event, if exists
		description Description OPTIONAL,
		-- Additional description to this event
		timeDetails RSITimeDetails OPTIONAL,
		-- Start time or end time when this event is active
		priority RSIPriority OPTIONAL,
		-- the urgency of this RSI data, a relative
		-- degree of merit compared with other RSI data
		referencePaths ReferencePathList OPTIONAL,
		-- Related paths of this traffic event
		referenceLinks ReferenceLinkList OPTIONAL,
		-- Related links of this traffic event
		eventConfidence Confidence OPTIONAL,
		-- indicate the event confidence set by event source
		-- the probability/confidence of the detected event
		-- being truly extent at a certain place, 
		-- to help vehicle determine whether to trust the received information.
		...
	}
 * @author 15504
 *
 */
public class ASN1RTEData extends ASN1Object{

	private ASN1Integer rteId;
	
	private ASN1Integer eventType;
	
	private ASN1Enumerated eventSource;
	
	private ASN1PositionOffsetLLV eventPos;
	
	private ASN1Integer eventRadius;
	
	private ASN1OctetString description;
	
	private ASN1RSITimeDetails timeDetails;
	
	private ASN1OctetString priority;
	
	private ASN1ReferencePathList referencePaths;
	
	private ASN1ReferenceLinkList referenceLinks;
	
	private ASN1Integer eventConfidence;
	
	
	
	public ASN1RTEData() {
		this.rteId=new ASN1Integer(0);
		this.eventType=new ASN1Integer(0);
		this.eventSource=new ASN1Enumerated(0);
		this.eventPos=new ASN1PositionOffsetLLV();
		this.eventRadius=new ASN1Integer(0);
		this.description=new DEROctetString("".getBytes());
		this.timeDetails=new ASN1RSITimeDetails();
		this.priority=new DEROctetString("".getBytes());
		this.referencePaths=new ASN1ReferencePathList();
		this.referenceLinks=new ASN1ReferenceLinkList();
		this.eventConfidence=new ASN1Integer(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector v = new ASN1EncodableVector();
		v.add(this.rteId);
		v.add(this.eventType);
		v.add(this.eventSource);
		v.add(this.eventPos);
		v.add(eventRadius);
		v.add(description);
		v.add(timeDetails);
		v.add(priority);
		v.add(referencePaths);
		v.add(referenceLinks);
		v.add(eventConfidence);
		
		return new DERSequence(v);
	}
	
	
	public static ASN1RTEData parse(byte[] data) throws IOException {
		ASN1RTEData obj=new ASN1RTEData();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;


				encodable = parser.readObject();//rteId
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setRteId((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//eventType
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setEventType((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//eventSource
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Enumerated) {
						obj.setEventSource((ASN1Enumerated) primitive);
					}
				}
				
				
				
				encodable = parser.readObject();//eventPos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionOffsetLLV e=ASN1PositionOffsetLLV.parse(primitive.getEncoded());
					obj.setEventPos(e);
				}

				
				encodable = parser.readObject();//eventRadius
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setEventRadius((ASN1Integer) primitive);
					}
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

				

				encodable = parser.readObject();//eventConfidence
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setEventConfidence((ASN1Integer) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}
	
	

	public ASN1Integer getRteId() {
		return rteId;
	}

	public void setRteId(ASN1Integer rteId) {
		this.rteId = rteId;
	}

	public ASN1Integer getEventType() {
		return eventType;
	}

	public void setEventType(ASN1Integer eventType) {
		this.eventType = eventType;
	}

	public ASN1Enumerated getEventSource() {
		return eventSource;
	}

	public void setEventSource(ASN1Enumerated eventSource) {
		this.eventSource = eventSource;
	}

	public ASN1PositionOffsetLLV getEventPos() {
		return eventPos;
	}

	public void setEventPos(ASN1PositionOffsetLLV eventPos) {
		this.eventPos = eventPos;
	}

	public ASN1Integer getEventRadius() {
		return eventRadius;
	}

	public void setEventRadius(ASN1Integer eventRadius) {
		this.eventRadius = eventRadius;
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

	public ASN1Integer getEventConfidence() {
		return eventConfidence;
	}

	public void setEventConfidence(ASN1Integer eventConfidence) {
		this.eventConfidence = eventConfidence;
	}
	
	
	

}
