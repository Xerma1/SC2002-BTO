package main.control.viewFilters;


public class ViewFilterFactory {
    public static IviewFilter getViewFilter(String filterType) {
        return switch (filterType) {
            case "single" -> new ViewProjectsSingle();
            case "all" -> new ViewProjectsAll();
            default -> throw new IllegalArgumentException("Invalid filterType: " + filterType);
        };
    }
}
