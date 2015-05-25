package dk.danthrane.twbs

enum GridSize {
    XS("xs"),
    SM("sm"),
    MD("md"),
    LG("lg"),
    EXTRA_SMALL("xs"),
    SMALL("sm"),
    MEDIUM("md"),
    LARGE("lg")

    String columnName

    private GridSize(String columnName) {
        this.columnName = columnName
    }
}
