package com.caeri.v2x.asn.rsm;

import java.io.IOException;

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
 *RoadsideSafetyMessage ::= SEQUENCE {
		msgCnt MsgCount,
		id OCTET STRING (SIZE(8)),
		-- RSU ID
		refPos Position3D,
		-- Reference position of this RSM message
		participants ParticipantList,
		-- All or part of the participants 
		-- detected by RSU
		...
	}
 * @author 15504
 *
 */
public class RSM extends ASN1Object {

	private String nonce="";
	
	private ASN1Integer msgCnt;
	
	private ASN1OctetString id;
	
	private ASN1Position3D pos;
	
	private ASN1ParticipantDataList participants;
	
	
	
	public RSM() {
		this.msgCnt=new ASN1Integer(0);
		this.id=new DEROctetString("".getBytes());
		this.pos=new ASN1Position3D();
		this.participants=new ASN1ParticipantDataList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(msgCnt);
		vector.add(id);
		vector.add(pos);
		vector.add(participants);
		return new DERSequence(vector);
	}

	public static RSM parse(byte[] data) throws IOException {
		RSM obj=new RSM();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//msgCnt
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setMsgCnt((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1OctetString) {
						obj.setId((ASN1OctetString) primitive);
					}
				}
				
				
				encodable = parser.readObject();//pos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Position3D pos=ASN1Position3D.parse(primitive.getEncoded());
					obj.setPos(pos);
				}
				
				encodable = parser.readObject();//participants
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1ParticipantDataList participants=ASN1ParticipantDataList.parse(primitive.getEncoded());
					obj.setParticipants(participants);
				}
				
			}
		}
		ais.close();
		return obj;
	}
	
	
	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public ASN1Integer getMsgCnt() {
		return msgCnt;
	}

	public void setMsgCnt(ASN1Integer msgCnt) {
		this.msgCnt = msgCnt;
	}

	public ASN1OctetString getId() {
		return id;
	}

	public void setId(ASN1OctetString id) {
		this.id = id;
	}

	public ASN1Position3D getPos() {
		return pos;
	}

	public void setPos(ASN1Position3D pos) {
		this.pos = pos;
	}

	
	public ASN1ParticipantDataList getParticipants() {
		return participants;
	}

	public void setParticipants(ASN1ParticipantDataList participants) {
		this.participants = participants;
	}

	public static void main(String[] args) throws IOException {
		RSM rsm1=new RSM();
		rsm1.setId(new DEROctetString("test".getBytes()));
		rsm1.getParticipants().getParticipants().add(new ASN1ParticipantData());
		rsm1.getParticipants().getParticipants().add(new ASN1ParticipantData());
		RSM rsm2=RSM.parse(rsm1.getEncoded());
		System.out.println(new String(rsm2.getId().getOctets()));
		
	}
	


}
