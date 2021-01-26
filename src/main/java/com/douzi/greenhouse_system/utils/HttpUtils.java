package com.douzi.greenhouse_system.utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HttpUtils {


    public static void main(String[] args) {

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("key",1);
        map.put("value","小明");
        list.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("key",1);
        map1.put("value","小明");
        list.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("key",3);
        map2.put("value","小明");
        list.add(map2);

        System.out.println("--------List<Map<String,Object>> = " + list);


        //Set<Map<String,Object>> set = list.stream().collect(Collectors.toSet());
        //Set<Map<String,Object>> set = list.stream().sorted(a -> Comparator.comparing(a.get("value"))).collect(Collectors.toSet());


        //System.out.println("--------Set<Map<String,Object>> = " + set);





//        String all = "1,2,3,4,5";
//        String finish = "1,3";
//
//        String [] allArr = all.split(",");
//        String [] finishArr = finish.split(",");
//
//        List<String> allList = new ArrayList(Arrays.asList(allArr));
//        List<String> finishList = new ArrayList(Arrays.asList(finishArr));
//
//        List<String> notFinishList = new ArrayList<>();
////        for(String tem : allList){
////            boolean flag = finishList.stream().anyMatch(f -> f.equalsIgnoreCase(tem));
////            if(!flag){
////                notFinishList.add(tem);
////            }
////        }
//
//
//        for(String tem : allList){
//            Optional<String> optional = finishList.stream().filter(f -> f.equalsIgnoreCase(tem) ).findAny();
//            if(!optional.isPresent()){
//                notFinishList.add(tem);
//            }
//        }
//        System.out.println("--------optional notFinishList = " + notFinishList);

//        allList.removeAll(finishList);
//        System.out.println("--------all = " + allList);
//        System.out.println("--------finishList = " + finishList);



//        String urlAddress = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
//        String soapXml = getMobileXml("13781695457","");
//        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";
//
//        String response = doSoapWsdl(urlAddress,soapXml,soapAction);
//        System.out.println("======response=" + response);


//        String urlAddress = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
//        String soapXml = getWeatherXml("2015","");
//        String soapAction = "http://WebXml.com.cn/getWeather";
//
//        String response = doSoapWsdl(urlAddress,soapXml,soapAction);
//        System.out.println("======response=" + response);
    }






    public static String doSoapWsdl(String urlAddress,String xml,String soapAction){
        URL url;
        HttpURLConnection uRLConnection;
        try {
            url = new URL(urlAddress);
            uRLConnection = (HttpURLConnection)url.openConnection();
            uRLConnection.setDoInput(true);
            uRLConnection.setDoOutput(true);
            uRLConnection.setRequestMethod("POST");
            uRLConnection.setUseCaches(false);
            uRLConnection.setInstanceFollowRedirects(false);
            uRLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            uRLConnection.setRequestProperty("SOAPAction", soapAction);
            uRLConnection.connect();

            DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());
            byte[] content = xml.getBytes("UTF-8");
            out.write(content);
            out.flush();
            out.close();


            int responseCode = uRLConnection.getResponseCode();
            System.out.println("----responseCode=" + responseCode);

            InputStream is = uRLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String response = "";
            String readLine = null;
            while((readLine =br.readLine()) != null){
                //response = br.readLine();
                response = response + readLine;
            }

//            SAXReader reader  = new SAXReader();
//            String result = "";
//            Document doc = reader.read(in);
//            result = doc.getRootElement().element("Body").element("statusWriteBackResponse").element("return").getText();
//            logger.debug("result:"+result);

            is.close();
            br.close();
            uRLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }





    public static String getMobileXml(String phone,String userId){
        String xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <mobileCode>"+ phone +"</mobileCode>\n" +
                "      <userID>"+ userId +"</userID>\n" +
                "    </getMobileCodeInfo>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return xml;
    }


    public static String getWeatherXml(String cityCode,String userId){
        String xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getWeather xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <theCityCode>"+ cityCode +"</theCityCode>\n" +
                "      <theUserID>"+ userId +"</theUserID>\n" +
                "    </getWeather>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return xml;
    }






}
