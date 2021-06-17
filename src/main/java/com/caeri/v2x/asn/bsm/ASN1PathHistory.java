package com.caeri.v2x.asn.bsm;


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

import com.caeri.v2x.asn.bsm.ASN1FullPositionVector;

/**
 * PathHistory ::= SEQUENCE {
	initialPosition FullPositionVector OPTIONAL,
	currGNSSstatus GNSSstatus OPTIONAL,
	crumbData PathHistoryPointList,
	...
	}
 */
public class ASN1PathHistory extends ASN1Object{

	private ASN1FullPositionVector initialPosition;
	
	private ASN1BitString currGNSSstatus;
	
	private ASN1PathHistoryPointList crumbData;
	
	
	public ASN1PathHistory() {
		this.initialPosition=new ASN1FullPositionVector();
		this.currGNSSstatus=new DERBitString("".getBytes());
		this.crumbData=new ASN1PathHistoryPointList();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(initialPosition);
		vector.add(currGNSSstatus);
		vector.add(crumbData);
		return new DERSequence(vector);
	}
	
	public static ASN1PathHistory parse(byte[] data) throws IOException {
		ASN1PathHistory obj=new ASN1PathHistory();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//initialPosition
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1FullPositionVector initialPosition=ASN1FullPositionVector.parse(primitive.getEncoded());
					obj.setInitialPosition(initialPosition);
				}

				encodable = parser.readObject();// currGNSSstatus
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof DERBitString) {
						obj.setCurrGNSSstatus((DERBitString) primitive);
					}
				}
				
				encodable = parser.readObject();//crumbData
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PathHistoryPointList crumbData=ASN1PathHistoryPointList.parse(primitive.getEncoded());
					obj.setCrumbData(crumbData);
				}
				
				
				
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1FullPositionVector getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(ASN1FullPositionVector initialPosition) {
		this.initialPosition = initialPosition;
	}

	public ASN1BitString getCurrGNSSstatus() {
		return currGNSSstatus;
	}

	public void setCurrGNSSstatus(ASN1BitString currGNSSstatus) {
		this.currGNSSstatus = currGNSSstatus;
	}

	public ASN1PathHistoryPointList getCrumbData() {
		return crumbData;
	}

	public void setCrumbData(ASN1PathHistoryPointList crumbData) {
		this.crumbData = crumbData;
	}

	
	
}