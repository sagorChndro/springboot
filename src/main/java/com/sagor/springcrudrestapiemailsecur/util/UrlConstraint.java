package com.sagor.springcrudrestapiemailsecur.util;

public final class UrlConstraint {
    private UrlConstraint(){}
    private static final String API ="/api";
    private static final String VERSION="/v1";
    public static final class ProductManagement{
        public static final String ROOT=API+VERSION+"/products";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/{id}";
        public static final String DELETE = "/{id}";
        public static final String GET = "/{id}";
        public static final String GET_ALL = "/all";
    }

    public static final class AuthManagement{
        public static final String ROOT = API+"/auth";
        public static final String LOGIN ="/login";
    }
}
