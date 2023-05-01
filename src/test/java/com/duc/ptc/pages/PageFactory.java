package com.duc.ptc.pages;

public class PageFactory {
    private PageFactory() {
    }

    public static IPage getPage(String page) {
        switch (page.toLowerCase()) {
            case "home":
                return new HomePage();
            case "login":
                return new LoginPage();
            default:
                throw new IllegalArgumentException("page `" + page + "` is not supported.");
        }
    }
}
