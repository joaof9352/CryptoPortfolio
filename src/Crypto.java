

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Crypto {
    private String api_key = "b54bcf4d-1bca-4e8e-9a24-22ff2c3d462c";
    private String ticker;
    private Map<Double,Double> purchases; //Quantidade, preço
    private Double quantity;
    private Double avg_bprice;
    private Double price;


    private JSONObject obj;

    public Crypto(String ticker){
        this.ticker = ticker;
        this.purchases = new HashMap<>();
    }

    public void buy(Double quantity){
        //tenta fazer a compra
        purchases.put(quantity,10.0);//preço
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Double getPrice(){
        return this.price;
    }

    public Double discoverPrice(JSONObject obj){

        return obj.getJSONObject("data")
                .getJSONObject(ticker)
                .getJSONObject("quote")
                .getJSONObject("USD")
                .getDouble("price");
    }

    public String getTicker(){
        return this.ticker;
    }
}
