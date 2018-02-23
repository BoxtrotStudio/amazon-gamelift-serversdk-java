package com.boxtrotstudio.aws.model;

import com.amazon.whitewater.auxproxy.pbuffer.Sdk;

import java.util.HashMap;

public class AttributeValue {

    public enum AttrType {
        STRING(1),
        DOUBLE(2),
        STRING_LIST(3),
        STRING_DOUBLE_MAP(4);

        int id;
        AttrType(int id) { this.id = id; }

        public int getId() {
            return id;
        }
    }

    private AttrType attrType;
    private String S;
    private double N;
    private String[] SL;
    private HashMap<String, Double> SDM = new HashMap<>();

    public AttrType getAttrType() {
        return attrType;
    }

    public String getS() {
        return S;
    }

    public double getN() {
        return N;
    }

    public String[] getSL() {
        return SL;
    }

    public HashMap<String, Double> getSDM() {
        return SDM;
    }

    public AttributeValue(String s)
    {
        this.attrType = AttrType.STRING;
        this.S = s;
    }

    public AttributeValue(double n)
    {
        this.attrType = AttrType.DOUBLE;
        this.N = n;
    }

    public AttributeValue(String[] sl)
    {
        this.attrType = AttrType.STRING_LIST;
        this.SL = sl;
    }

    public AttributeValue(HashMap<String, Double> sdm)
    {
        this.attrType = AttrType.STRING_DOUBLE_MAP;
        this.SDM = sdm;
    }

    public Sdk.AttributeValue createBufferedAttributeValue() {
        Sdk.AttributeValue.Builder builder = Sdk.AttributeValue.newBuilder()
                .setType(attrType.getId());

        switch (attrType) {
            case STRING:
                builder.setS(S);
                break;
            case DOUBLE:
                builder.setN(N);
                break;
            case STRING_LIST:
                for (String s : SL) {
                    builder.addSL(s);
                }
                break;
            case STRING_DOUBLE_MAP:
                for (String key : SDM.keySet()) {
                    builder.putSDM(key, SDM.get(key));
                }
                break;
        }

        return builder.build();
    }
}
