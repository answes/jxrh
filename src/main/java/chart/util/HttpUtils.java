package chart.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * Created by 京美电子 on 2018/1/8.
 */
public class HttpUtils {

    public static void main(String[] args){

        HttpUtils httpUtils = new HttpUtils();

        long time = 1515573900;
        int step = 1800;
        long jump = 1800000;

        for(int i =0 ;i < 1;i ++) {

            String url = "https://www.bitstamp.net/market/tradeview_data/?currencyPair=BTC/USD&step="+ step +"&end=" + time;
            System.out.println("第" + i + "次请求:" + url);
            httpUtils.sendRequest(url);
            time -= jump;
        }
//        String reUrl = "http://localhost:8080/main/data";
//        Map<String,String> params = new HashMap<>();
//        params.put("name","asd123");
//
//        String ret =  httpUtils.post(reUrl,params);
    }

    private String rootUrl = "http://cigtc.net";

    private String cookie = "";

    public String sendRequest(String requestUrl){
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            //返回头
//            connection.setRequestProperty("Content-type", "application/json");
//            connection.setRequestProperty("Content-Encoding", "gzip");
//            connection.setRequestProperty("Content-Language", "en");
//            connection.setRequestProperty("Date", "Wed, 10 Jan 2018 03:24:36 GM");
//            connection.setRequestProperty("Server", "Apache");
//            connection.setRequestProperty("Strict-Transport-Security", "max-age=63072000; includeSubDomains");
//            connection.setRequestProperty("transfer-encoding", "chunked");
//            connection.setRequestProperty("Vary", "Accept-Language,Cookie");
//            connection.setRequestProperty("X-CDN", "Incapsula");
//            connection.setRequestProperty("X-Frame-Options", "SAMEORIGIN");
//            connection.setRequestProperty("X-Iinfo", "8-223242270-223242614 SNNN RT(1515553462907 15249) q(0 0 0 -1) r(3 3) U10000");

            //请求头
//            connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//            connection.setRequestProperty("Connection", "keep-alive");
//            connection.setRequestProperty("Host", "www.bitstamp.net");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
//            connection.setRequestProperty("Referer", "https://www.bitstamp.net/market/tradeview/");
//            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            connection.setRequestProperty("Cookie", cookie);

            connection.connect();
            int code = connection.getResponseCode();

            System.out.println("返回码："+code);

            if(code == 404){
                return "404";
            }

            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream),"UTF-8"));

            String s = null;

            while((s = reader.readLine()) != null){
                sb.append(s);
            }

            System.out.println("请求返回数据："+sb.toString());

            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
                connection = null;
            }

        }

        return  sb.toString();
    }

    private void addHeader(HttpURLConnection connection,Map<String,String> headers){
        if(headers != null){
            Set<String> set = headers.keySet();
            Iterator<String> strings = set.iterator();

            while(strings.hasNext()){
                String key = strings.next();
                connection.setRequestProperty(key, headers.get(key));
            }

        }
    }

    public String get(String url){
        return  sendGet(rootUrl + url,null,false);
    }

    public String get(String url,boolean gzip){
        return  sendGet(url,null,gzip);
    }

    public String get(String url,Map<String,String> headers){
        return  sendGet(url,headers,false);
    }

    public String get(String url,Map<String,String> headers,boolean gzip){
        return  sendGet(url,headers,gzip);
    }

    private String sendGet(String requestUrl,Map<String,String> headers,boolean gzip){
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            addHeader(connection,headers);

            connection.connect();
            int code = connection.getResponseCode();

            System.out.println("RequestCode:"+code);

            if(code == 404){
                return "404";
            }

            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//            if(gzip){
//                new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream), "UTF-8"));
//            }else {

//            }

            String s = null;

            while((s = reader.readLine()) != null){
                sb.append(s);
            }

            System.out.println("ResponseData:"+sb.toString());

            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
                connection = null;
            }

        }

        return  sb.toString();

    }

    public String post(String requestUrl,Map<String,String> params){
        return sendPost(rootUrl+requestUrl,params,null,false);
    }

    public String post(String requestUrl,Map<String,String> params,boolean gzip){
        return sendPost(requestUrl,params,null,gzip);
    }

    public String post(String requestUrl,Map<String,String> params,Map<String,String> headers){return sendPost(requestUrl,params,headers,false);}

    private String sendPost(String requestUrl,Map<String,String> params,Map<String,String> headers,boolean gzip){
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("POST");

            addHeader(connection,headers);

            connection.connect();

            if(params != null){
                StringBuilder paramBuilder = new StringBuilder();
                Set<String> set = params.keySet();
                Iterator<String> iterator = set.iterator();

                int start = 0;
                while(iterator.hasNext()){
                    String key = iterator.next();

                    if(start != 0){
                        paramBuilder.append("&");
                    }
                    paramBuilder.append(key + "=" + params.get(key));
                    start ++;
                }

                PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
                printWriter.write(paramBuilder.toString());
                printWriter.flush();
                printWriter.close();

            }

            int code = connection.getResponseCode();

            System.out.println("返回码："+code);

            if(code == 404){
                return "404";
            }

            inputStream = connection.getInputStream();
            BufferedReader reader = null;

            if(gzip){
                new BufferedReader(new InputStreamReader(new GZIPInputStream(inputStream), "UTF-8"));
            }else {
                new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            }

            String s = null;

            while((s = reader.readLine()) != null){
                sb.append(s);
            }

            System.out.println("请求返回数据："+sb.toString());

            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
                connection = null;
            }

        }

        return  sb.toString();

    }

//    /**
//     * 获取访问IP
//     * @param request Http请求
//     * @return
//     */
//    public static String getRemortIP(HttpServletRequest request) {
////        String ip = request.getHeader("X-Real-IP");
////        if (!StringUtils.isEmpty(ip)) {
////            return ip;
////        }
////        if (request.getHeader("X-Forwarded-For") != null) {
////            for (String singleIP : request.getHeader("X-Forwarded-For").split(",")) {
////                if (singleIP != null && !singleIP.equals("unknown")) {
////                    return singleIP.trim();
////                }
////            }
////        }
//        return request.getRemoteAddr();
//    }
}
