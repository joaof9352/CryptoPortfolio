import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        Crypto c1 = new Crypto("BTC");
        Crypto c2 = new Crypto("ETH");
        Crypto c3 = new Crypto("TRX");
        //Crypto c4 = new Crypto("ALGO");
        List<Crypto> lC = new ArrayList<>();
        lC.add(c1); lC.add(c2); lC.add(c3); //lC.add(c4);
        Conta test = new Conta(lC);
        System.out.println(test.toString());
    }
}
