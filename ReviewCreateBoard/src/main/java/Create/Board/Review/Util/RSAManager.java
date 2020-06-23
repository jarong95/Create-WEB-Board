package Create.Board.Review.Util;

import java.util.Base64;
//import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import Create.Board.Review.VO.MemberVO;



public class RSAManager {

	public static MemberVO encode(MemberVO member) {
		String text = member.getPassword(); /* base64 encoding */ 
		Encoder encoder = Base64.getEncoder();
		byte[] encodedBytes = encoder.encode(text.getBytes()); /* base64 decoding */ 
		member.setPassword(new String(encodedBytes));
		return member;
	}
}
