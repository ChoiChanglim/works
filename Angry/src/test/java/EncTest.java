import com.nhn.base.constant.ServerKey.ServerKeyEnum;
import com.nhn.common.util.AesCipher;


public class EncTest {

    public static void main(String[] args) {
        AesCipher cipher  = new AesCipher(ServerKeyEnum.PRE_REGIST.getKey());
        String enc = cipher.encryptString("카카오 메세지");
        System.err.println("enc:"+enc);
        String msg_enc = "C5436A01E966BB156CEC36985949BBBB3A9C01478EF35864A6A69447EB5F8434";
        String dec = cipher.decryptString(msg_enc);
        System.err.println("dec:"+dec);
    }

}
