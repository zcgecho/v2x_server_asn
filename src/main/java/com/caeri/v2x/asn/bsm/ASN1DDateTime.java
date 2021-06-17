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
 * DDateTime ::= SEQUENCE {
		year DYear OPTIONAL,
		month DMonth OPTIONAL,
		day DDay OPTIONAL,
		hour DHour OPTIONAL,
		minute DMinute OPTIONAL,
		second DSecond OPTIONAL,
		offset DTimeOffset OPTIONAL -- time zone
		}
 * @author 15504
 *
 */
public class ASN1DDateTime extends ASN1Object {

	
	private ASN1Integer year;
	
	private ASN1Integer month;
	
	private ASN1Integer day;
	
	private ASN1Integer hour;
	
	private ASN1Integer minute;
	
	private ASN1Integer second;
	
	private ASN1Integer offset;
	
	
	public ASN1DDateTime() {
		this.year=new ASN1Integer(0);
		this.month=new ASN1Integer(0);
		this.day=new ASN1Integer(0);
		this.hour=new ASN1Integer(0);
		this.minute=new ASN1Integer(0);
		this.second=new ASN1Integer(0);
		this.offset=new ASN1Integer(0);
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		vector.add(year);
		vector.add(month);
		vector.add(day);
		vector.add(hour);
		vector.add(minute);
		vector.add(second);
		vector.add(offset);
		return new DERSequence(vector);
	}
	
	public static ASN1DDateTime parse(byte[] data) throws IOException {
		ASN1DDateTime obj=new ASN1DDateTime();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//year
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setYear((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//month
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setMonth((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//day
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setDay((ASN1Integer) primitive);
					}
				}


				encodable = parser.readObject();//hour
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setHour((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//minute
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setMinute((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//second
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setSecond((ASN1Integer) primitive);
					}
				}

				encodable = parser.readObject();//offset
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					if (primitive instanceof ASN1Integer) {
						obj.setOffset((ASN1Integer) primitive);
					}
				}
				
			}
		}
		ais.close();
		return obj;
	}
	


	public ASN1Integer getYear() {
		return year;
	}


	public void setYear(ASN1Integer year) {
		this.year = year;
	}


	public ASN1Integer getMonth() {
		return month;
	}


	public void setMonth(ASN1Integer month) {
		this.month = month;
	}


	public ASN1Integer getDay() {
		return day;
	}


	public void setDay(ASN1Integer day) {
		this.day = day;
	}


	public ASN1Integer getHour() {
		return hour;
	}


	public void setHour(ASN1Integer hour) {
		this.hour = hour;
	}


	public ASN1Integer getMinute() {
		return minute;
	}


	public void setMinute(ASN1Integer minute) {
		this.minute = minute;
	}


	public ASN1Integer getSecond() {
		return second;
	}


	public void setSecond(ASN1Integer second) {
		this.second = second;
	}


	public ASN1Integer getOffset() {
		return offset;
	}


	public void setOffset(ASN1Integer offset) {
		this.offset = offset;
	}

}
