package com.murik.lite.ui.fragment.substances.tab;

public enum SubstancePage {
    SUBSTANCE_PAGE(0), OBJECT_PAGE(1), BIOASSAYS(2);

    private final int pageNumber;

    SubstancePage(int i) {
        pageNumber = i;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public static SubstancePage getByPageNumber(Integer pageNumber) {
        if (pageNumber == null) {
            return null;
        }

        for (SubstancePage e : SubstancePage.values()) {
            if (e.getPageNumber() == pageNumber) {
                return e;
            }
        }

        return null;
    }
}
