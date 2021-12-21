package com.okay.util;

import com.okay.enm.EnumRequestType;
import com.okay.model.RequestDto;
import com.okay.model.RequestHistoryDto;
import com.okay.service.RequestHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Date;

public class RequestUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);

    private static final String PROTOCOL_SEPARATOR = "://";
    private static final String COLON_SEPARATOR = ":";

    public static boolean sendRequest(RequestHistoryService requestHistoryService, RequestDto request) {
        boolean error = false;

        HttpURLConnection connection = null;

        RequestHistoryDto requestHistory = createRequestHistory(request);

        try {
            // disable SSL verification
            disableSslVerification();

            URL url = prepareURI(request);

            connection = (HttpURLConnection) url.openConnection();

            EnumRequestType requestType = request.getRequestType();
            switch (requestType) {
                case GET:
                    connection.setRequestMethod("GET");
                    break;
                case POST:
                    connection.setRequestMethod("POST");
                    break;
                case PUT:
                    break;
                case DELETE:
                    break;
                default:
            }

            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            if (request.getBody() != null) {
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = request.getBody().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
//                requestHistory.setBody(response.toString());
            }
        } catch (Exception e) {
            error = true;

            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    requestHistory.setResponseCode(connection.getResponseCode());
                } else {
                    requestHistory.setResponseCode(-1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            requestHistoryService.save(requestHistory);
        }

        return error;
    }

    private static URL prepareURI(RequestDto request) throws MalformedURLException {
        String protocol = request.getProtocol().name().toLowerCase();
        String url = request.getUrl();
        String port = request.getPort();

        return new URL(protocol + PROTOCOL_SEPARATOR + url + COLON_SEPARATOR + port);
    }

    private static RequestHistoryDto createRequestHistory(RequestDto request) {
        RequestHistoryDto requestHistory = new RequestHistoryDto();
        requestHistory.setRequest(request);
        requestHistory.setRequestDate(new Date());
        return requestHistory;
    }


    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (KeyManagementException e) {
            LOGGER.error(e.getMessage());
        }
    }
}