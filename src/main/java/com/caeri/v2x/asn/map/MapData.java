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
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

/**
 *MapData ::=	SEQUENCE {
		msgCnt MsgCount,
		timeStamp MinuteOfTheYear OPTIONAL,
		nodes NodeList,
		-- intersections or road endpoints
		...
	}
 * @author 15504
 *
 */
public class MapData extends ASN1Object {

	private String nonce="";
	
	private ASN1Integer msgCnt;
	
	private ASN1Integer timeStamp;
	
	private ASN1NodeList nodes;
	
	
	
	
	public MapData() {
		this.msgCnt=new ASN1Integer(0);
		this.timeStamp=new ASN1Integer(0);
		this.nodes=new ASN1NodeList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(msgCnt);
		vector.add(timeStamp);
		vector.add(nodes);
		return new DERSequence(vector);
	}

	public static MapData parse(byte[] data) throws IOException {
		MapData obj=new MapData();
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

				encodable = parser.readObject();//timeStamp
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setTimeStamp((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//nodes
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1NodeList e=ASN1NodeList.parse(primitive.getEncoded());
					obj.setNodes(e);
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

	public ASN1Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(ASN1Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ASN1NodeList getNodes() {
		return nodes;
	}

	public void setNodes(ASN1NodeList nodes) {
		this.nodes = nodes;
	}
	
	

}
