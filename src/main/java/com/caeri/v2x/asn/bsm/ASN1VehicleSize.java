package com.caeri.v2x.asn.bsm;

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
 * VehicleSize ::= SEQUENCE {
	width VehicleWidth,
	length VehicleLength,
	height VehicleHeight OPTIONAL
}
 */
public class ASN1VehicleSize extends ASN1Object{
	
	
	private ASN1Integer width;
	
	private ASN1Integer length;

	private ASN1Integer height;

	
	public ASN1VehicleSize() {
		this.width=new ASN1Integer(0);
		this.length=new ASN1Integer(0);
		this.height=new ASN1Integer(0);
	}
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(width);
		vector.add(length);
		vector.add(height);
		return new DERSequence(vector);
	}

	
	public static ASN1VehicleSize parse(byte[] data) throws IOException {
		ASN1VehicleSize obj=new ASN1VehicleSize();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//width
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setWidth((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//length
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setLength((ASN1Integer) primitive);
					}
				}
				
				encodable = parser.readObject();//height
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setHeight((ASN1Integer) primitive);
					}
				}
				
				
			}
		}
		ais.close();
		return obj;
	}
	

	public ASN1Integer getWidth() {
		return width;
	}


	public void setWidth(ASN1Integer width) {
		this.width = width;
	}


	public ASN1Integer getLength() {
		return length;
	}


	public void setLength(ASN1Integer length) {
		this.length = length;
	}


	public ASN1Integer getHeight() {
		return height;
	}


	public void setHeight(ASN1Integer height) {
		this.height = height;
	}
	
}