package com.caeri.v2x.asn.rsm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERSequence;

/**
 * ASN1ParticipantDataList
 * @author 15504
 *
 */
public class ASN1ParticipantDataList extends ASN1Object{

	
	private List<ASN1ParticipantData> participants;
	
	
	public ASN1ParticipantDataList() {
		this.participants=new ArrayList<ASN1ParticipantData>();
	}
	
	@Override
	public ASN1Primitive toASN1Primitive() {
		ASN1EncodableVector vector = new ASN1EncodableVector();
		for(int i=0;i<participants.size();i++) {
			vector.add(participants.get(i));
		}
		return new DERSequence(vector);
	}
	
	public static ASN1ParticipantDataList parse(byte[] data) throws IOException {
		ASN1ParticipantDataList obj=new ASN1ParticipantDataList();
		ASN1InputStream ais = new ASN1InputStream(data);
		ASN1Primitive primitive = null;
		while ((primitive = ais.readObject()) != null) {
			System.out.println("sequence->" + primitive);
			if (primitive instanceof ASN1Sequence) {
				ASN1Sequence sequence = (ASN1Sequence) primitive;
				ASN1SequenceParser parser = sequence.parser();
				ASN1Encodable encodable = null;

				encodable = parser.readObject();//participants
				if (encodable != null) {
					primitive = encodable.toASN1Primitive();
					ASN1ParticipantData participantData=ASN1ParticipantData.parse(primitive.getEncoded());
					obj.getParticipants().add(participantData);
				}
				
				
			}
		}
		ais.close();
		return obj;
	}

	public List<ASN1ParticipantData> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ASN1ParticipantData> participants) {
		this.participants = participants;
	}
	
	public static void main(String[] args) throws IOException {
		ASN1ParticipantDataList list=new ASN1ParticipantDataList();
		list.getParticipants().add(new ASN1ParticipantData());
		ASN1ParticipantDataList list2=ASN1ParticipantDataList.parse(list.getEncoded());
		System.out.println(list2.getParticipants().size());
		
	}
	

}
