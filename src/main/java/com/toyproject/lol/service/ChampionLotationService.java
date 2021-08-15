package com.toyproject.lol.service;

import com.toyproject.lol.controller.ChampionLotationController;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;

@Service
public class ChampionLotationService {

    private static final String LOL_ENDPOINT = "https://kr.api.riotgames.com/lol/platform";

    private  HttpGet setRequestAddHeader(HttpGet request){
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        request.addHeader("Accept-Language","ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        request.addHeader("Accept-Charset","application/x-www-form-urlencoded; charset=UTF-8");
        request.addHeader("Origin","https://developer.riotgames.com");
        request.addHeader("X-Riot-Token","RGAPI-1d83fd16-5855-4d51-90a7-2d80b9f3b573");

        return request;
    }
    //"champion-rotations"
    public String getChampionRotationsObject(String urlPath) throws Exception {
        JSONObject result = new JSONObject();
        String stringResult = "";
        try {
//            String urlPath = LOL_ENDPOINT + "/v3/champion-rotations";
            HttpClient httpClient = HttpClientBuilder.create().build();
            URI uri = new URI(urlPath);
            HttpGet request = new HttpGet(uri);
            request = setRequestAddHeader(request);

            HttpResponse response = httpClient.execute(request);
            int i;

            InputStream is = response.getEntity().getContent();
            System.out.println("> Result : " + is);
            StringBuffer buffer = new StringBuffer();
            byte[] b = new byte[4096];
            while ((i = is.read(b)) != -1) {
                buffer.append(new String(b, 0, i));
            }
            System.out.println("buffer :: " + buffer.getClass().getName());

            Object objList = JSONValue.parse(buffer.toString());
            result = (JSONObject)objList;

        } catch (Exception e){
            System.out.println(e);
        }
        if(result != null){
            stringResult = "데이터 수집완료";
            //dao랑 연결해서 result insert하기
        }else{
            stringResult = "데이터 수집실패";
            //dao 연결해서 에러 메세지 db적재
        }
        return stringResult;
    }

}
