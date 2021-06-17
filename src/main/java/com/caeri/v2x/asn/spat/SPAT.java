package com.caeri.v2x.asn.spat;

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


/**
 * SPAT ::= SEQUENCE {
		msgCnt MsgCount,
		moy MinuteOfTheYear OPTIONAL,
		timeStamp DSecond OPTIONAL,
		-- Time stamp when this message is formed
		name DescriptiveName OPTIONAL,
		-- human readable name for this collection
		-- to be used only in debug mode
		intersections IntersectionStateList,
		-- sets of SPAT data (one per intersection)
		...
	}
 * @author 15504
 *
 */
public class SPAT extends ASN1Object{
	private String nonce="";
	
	private ASN1Integer msgCnt;
	
	private ASN1Integer moy;

	private ASN1Integer timeStamp;
	
	private ASN1OctetString name;

	private ASN1IntersectionStateList intersections;
	
	public SPAT() {
		this.msgCnt=new ASN1Integer(0);
		this.moy=new ASN1Integer(0);
		this.timeStamp=new ASN1Integer(0);
		this.name=new DEROctetString("".getBytes());
		this.intersections=new ASN1IntersectionStateList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(msgCnt);
		vector.add(moy);
		vector.add(timeStamp);
		vector.add(name);
		vector.add(intersections);
		return new DERSequence(vector);
	}
	


	public static SPAT parse(byte[] data) throws IOException {
		SPAT obj=new SPAT();
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
				
				encodable = parser.readObject();//timeStamp
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setTimeStamp((ASN1Integer) primitive);
					}
				}

				
				encodable = parser.readObject();//name
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DEROctetString) {
						obj.setName((DEROctetString) primitive);
					}
				}
				
				
				encodable = parser.readObject();//intersections
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1IntersectionStateList intersections=ASN1IntersectionStateList.parse(primitive.getEncoded());
					obj.setIntersections(intersections);
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

	public ASN1Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(ASN1Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ASN1OctetString getName() {
		return name;
	}

	public void setName(ASN1OctetString name) {
		this.name = name;
	}

	public ASN1IntersectionStateList getIntersections() {
		return intersections;
	}

	public void setIntersections(ASN1IntersectionStateList intersections) {
		this.intersections = intersections;
	}

}
