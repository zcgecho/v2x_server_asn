package com.caeri.v2x.asn.rsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;

import com.caeri.v2x.asn.bsm.ASN1PositionOffsetLLV;
import com.caeri.v2x.asn.bsm.ASN1PositionOffsetLLVList;
import com.caeri.v2x.asn.map.ASN1NodeList;
import com.caeri.v2x.asn.map.MapData;

/**
 * ReferencePath ::= SEQUENCE {
		activePath PathPointList,
		-- RSI is active for vehicles within this path
		-- Points are listed from upstream to downstream
		-- along the vehicle drive direction.
		-- One path includes at least 1 points.
		-- A path with only 1 point means a round alert area
		pathRadius Radius
		-- The biggest distance away from the alert path
		-- within which the warning is active.
	}
 * @author 15504
 *
 */
public class ASN1ReferencePath extends ASN1Object{

	private ASN1PositionOffsetLLVList  activePath;
	
	private ASN1Integer pathRadius;
	
	
	public ASN1ReferencePath() {
		this.activePath=new ASN1PositionOffsetLLVList();
		this.pathRadius=new ASN1Integer(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(activePath);
		vector.add(pathRadius);
		return new DERSequence(vector);
	}

	public static ASN1ReferencePath parse(byte[] data) throws IOException {
		ASN1ReferencePath obj=new ASN1ReferencePath();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;
				
				encodable = parser.readObject();//activePath
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1PositionOffsetLLVList e=ASN1PositionOffsetLLVList.parse(primitive.getEncoded());
					obj.setActivePath(e);
				}
				
				encodable = parser.readObject();//pathRadius
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setPathRadius((ASN1Integer) primitive);
					}
				}
			}
		}
		ais.close();
		return obj;
	}
	
	
	public ASN1Integer getPathRadius() {
		return pathRadius;
	}

	public void setPathRadius(ASN1Integer pathRadius) {
		this.pathRadius = pathRadius;
	}

	public ASN1PositionOffsetLLVList getActivePath() {
		return activePath;
	}

	public void setActivePath(ASN1PositionOffsetLLVList activePath) {
		this.activePath = activePath;
	}
	
	

}
