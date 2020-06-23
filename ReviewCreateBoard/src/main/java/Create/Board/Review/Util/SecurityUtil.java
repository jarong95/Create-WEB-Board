package Create.Board.Review.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Create.Board.Review.VO.MemberVO;


public class SecurityUtil {
	public static MemberVO encryptSHA256(MemberVO ID){
		try{
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			sha.update(ID.getPassword().getBytes());
			byte[] password = sha.digest();
			StringBuffer stringB = new StringBuffer();
			for(int i = 0; i < password.length; i++){
				stringB.append(Integer.toString((password[i]&0xff)+0x100,16).substring(1));
			}
			ID.setPassword(stringB.toString());
		}
		catch(NoSuchAlgorithmException e){}
		return ID;
	}
}
