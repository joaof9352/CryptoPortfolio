import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Conta {
    private String api_key = "aeba7d95-78c0-4f76-8f88-85c422562baf";
    private String tickers_list;
    private List<Crypto> crypto_list;

    public Conta(List<Crypto> crypto_list){
        this.crypto_list = crypto_list;
        updateTickers();
        updatePrices();
    }

    public void updatePrices(){
        updateTickers();
        Connection c = new Connection("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol="
                + tickers_list + "&CMC_PRO_API_KEY=" + api_key);
        JSONObject obj = new JSONObject(c.response());
        for(int i = 0; i < crypto_list.size(); i++)
            crypto_list.get(i).setPrice(crypto_list.get(i).discoverPrice(obj));
    }

    private void updateTickers(){
        List<String> str;
        String s = "";
        str = crypto_list.stream().map(Crypto::getTicker).collect(Collectors.toList());
        int tamanho = str.size();
        int i = 0;
        for(String sInStr : str){
            s += str.get(i);
            if(i++ < tamanho-1) s += ",";
        }
        this.tickers_list = s;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Crypto c : crypto_list){
            sb.append(c.getTicker());
            sb.append("\t");
            sb.append(new DecimalFormat("#.##").format(c.getPrice()));
            sb.append("$\n");
        }
        return sb.toString();
    }
}