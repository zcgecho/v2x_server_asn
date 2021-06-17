package com.caeri.v2x.asn.rsi;

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
import com.caeri.v2x.asn.spat.ASN1IntersectionStateList;
import com.caeri.v2x.asn.spat.SPAT;

/**
 * 	RoadSideInformation ::= SEQUENCE {
		msgCnt MsgCount,
		moy MinuteOfTheYear OPTIONAL,
		id OCTET STRING (SIZE(8)),
		-- RSU ID
		refPos Position3D,
		-- Reference position of this RSI message
		rtes RTEList OPTIONAL,
		-- All the rte data packed in this message
		rtss RTSList OPTIONAL,
		-- All the rts data packed in this message
		...
	}
 * @author 15504
 *
 */
public class RSI extends ASN1Object{
	
	private String nonce="";

	private ASN1Integer msgCnt;
	
	private ASN1Integer moy;
	
	private ASN1OctetString id;
	
	private ASN1Position3D refPos;
	
	private ASN1RTEDataList rtes;
	
	private ASN1RTSDataList rtss;
	
	
	public RSI() {
		this.msgCnt=new ASN1Integer(0);
		this.moy=new ASN1Integer(0);
		this.id=new DEROctetString("".getBytes());
		this.refPos=new ASN1Position3D();
		this.rtes=new ASN1RTEDataList();
		this.rtss=new ASN1RTSDataList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(msgCnt);
		vector.add(moy);
		vector.add(id);
		vector.add(refPos);
		vector.add(rtes);
		vector.add(rtss);
		return new DERSequence(vector);
	}

	
	public static RSI parse(byte[] data) throws IOException {
		RSI obj=new RSI();
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
				
				encodable = parser.readObject();//moy
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setMoy((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//id
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setId((DEROctetString) primitive);
					}
				}

				encodable = parser.readObject();//refPos
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1Position3D refPos=ASN1Position3D.parse(primitive.getEncoded());
					obj.setRefPos(refPos);
				}
			

				encodable = parser.readObject();//rtes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RTEDataList rtes=ASN1RTEDataList.parse(primitive.getEncoded());
					obj.setRtes(rtes);
				}

				encodable = parser.readObject();//rtss
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1RTSDataList rtss=ASN1RTSDataList.parse(primitive.getEncoded());
					obj.setRtss(rtss);
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

	public ASN1Integer getMoy() {
		return moy;
	}

	public void setMoy(ASN1Integer moy) {
		this.moy = moy;
	}

	public ASN1OctetString getId() {
		return id;
	}

	public void setId(ASN1OctetString id) {
		this.id = id;
	}

	public ASN1Position3D getRefPos() {
		return refPos;
	}

	public void setRefPos(ASN1Position3D refPos) {
		this.refPos = refPos;
	}

	public ASN1RTEDataList getRtes() {
		return rtes;
	}

	public void setRtes(ASN1RTEDataList rtes) {
		this.rtes = rtes;
	}

	public ASN1RTSDataList getRtss() {
		return rtss;
	}

	public void setRtss(ASN1RTSDataList rtss) {
		this.rtss = rtss;
	}

	
	

}
