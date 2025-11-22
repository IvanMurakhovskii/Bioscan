package com.murik.lite.ui.fragment.substances.tab;

public enum SubstancePage {
    SUBSTANCE_PAGE(0),SUBSTANCE_PAGE_2(1), OBJECT_PAGE(2), BIOASSAYS(3), FLOWER(4), ALCOHOL(5);

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
