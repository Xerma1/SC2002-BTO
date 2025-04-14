package main.control.viewFilters;


public class viewFilterFactory {
    public static IviewFilter getViewFilter(String filterType) {
        return switch (filterType) {
            case "single" -> new viewProjectsSingle();
            case "all" -> new viewProjectsAll();
            default -> throw new IllegalArgumentException("Invalid filterType: " + filterType);
        };
    }
}
