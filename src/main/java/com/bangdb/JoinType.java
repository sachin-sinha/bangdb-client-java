package com.bangdb;

public enum JoinType {
    JO_INVALID, JO_AND, JO_OR;

    public static JoinType fromInt(int e) {
        switch(e) {
            case -1:
                return JO_INVALID;
            case 0:
                return JO_AND;
            case 1:
                return JO_OR;
        }
        return JO_INVALID;
    }

    public static int toInt(int ordinal)
    {
        switch(ordinal)
        {
            case 0:
                return -1;
            case 1:
                return 0;
            case 2:
                return 1;
        }
        return 0;
    } 
}
