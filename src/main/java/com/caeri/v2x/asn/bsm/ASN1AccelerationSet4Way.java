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
 * AccelerationSet4Way ::= SEQUENCE {
	long Acceleration, 
	-- Along the Vehicle Longitudinal axis
	lat Acceleration, 
	-- Along the Vehicle Lateral axis
	vert VerticalAcceleration, 
	-- Along the Vehicle Vertical axis
	yaw YawRate
}
 */
public class ASN1AccelerationSet4Way extends ASN1Object{

		private ASN1Integer lon;
		
		private ASN1Integer lat;

		private ASN1Integer vert;

		private ASN1Integer yaw;
		
		
		public ASN1AccelerationSet4Way() {
			this.lat=new ASN1Integer(-1);
			this.lon=new ASN1Integer(-1);
			this.vert=new ASN1Integer(-1);
			this.yaw=new ASN1Integer(-1);
		}
		@Override
		public ASN1Primitive toASN1Primitive() {
			ASN1EncodableVector vector = new ASN1EncodableVector();
			vector.add(lon);
			vector.add(lat);
			vector.add(vert);
			vector.add(yaw);
			return new DERSequence(vector);
		}

		
		public static ASN1AccelerationSet4Way parse(byte[] data) throws IOException {
			ASN1AccelerationSet4Way obj=new ASN1AccelerationSet4Way();
			ASN1InputStream ais = new ASN1InputStream(data);
			ASN1Primitive primitive = null;
			while ((primitive = ais.readObject()) != null) {
				System.out.println("sequence->" + primitive);
				if (primitive instanceof ASN1Sequence) {
					ASN1Sequence sequence = (ASN1Sequence) primitive;
					ASN1SequenceParser parser = sequence.parser();
					ASN1Encodable encodable = null;

					encodable = parser.readObject();//lon
					if (encodable != null) {
						primitive = encodable.toASN1Primitive();
						if (primitive instanceof ASN1Integer) {
							obj.setLon((ASN1Integer) primitive);
						}
					}
					
					encodable = parser.readObject();//lat
					if (encodable != null) {
						primitive = encodable.toASN1Primitive();
						if (primitive instanceof ASN1Integer) {
							obj.setLat((ASN1Integer) primitive);
						}
					}
					
					encodable = parser.readObject();//vert
					if (encodable != null) {
						primitive = encodable.toASN1Primitive();
						if (primitive instanceof ASN1Integer) {
							obj.setVert((ASN1Integer) primitive);
						}
					}
					
					encodable = parser.readObject();//yaw
					if (encodable != null) {
						primitive = encodable.toASN1Primitive();
						if (primitive instanceof ASN1Integer) {
							obj.setYaw((ASN1Integer) primitive);
						}
					}
					
				}
			}
			ais.close();
			return obj;
		}
		
		
		public ASN1Integer getLon() {
			return lon;
		}

		public void setLon(ASN1Integer lon) {
			this.lon = lon;
		}

		public ASN1Integer getLat() {
			return lat;
		}

		public void setLat(ASN1Integer lat) {
			this.lat = lat;
		}

		public ASN1Integer getVert() {
			return vert;
		}

		public void setVert(ASN1Integer vert) {
			this.vert = vert;
		}

		public ASN1Integer getYaw() {
			return yaw;
		}

		public void setYaw(ASN1Integer yaw) {
			this.yaw = yaw;
		}
		
	}
