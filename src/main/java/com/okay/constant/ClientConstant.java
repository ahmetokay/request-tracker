package com.okay.constant;

public class ClientConstant {

    public static final String SECRET_KEY = "iptv";

    public static class IpTvClient {

        public static final String CLIENT_ID = "iptv-client";
        public static final String[] GRANT_TYPES = {"refresh_token", "password"};
        public static final String GRANT_TYPE = "password";
        public static final String SCOPE = "client";
        public static final String[] SCOPES = {"client"};

    }
}
