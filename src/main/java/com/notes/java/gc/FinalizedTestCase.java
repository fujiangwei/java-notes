package com.notes.java.gc;

public class FinalizedTestCase {

    public static FinalizedTestCase caseForEscape = null;

    @Override

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("咿呀呀！");
        caseForEscape = this;
    }

}