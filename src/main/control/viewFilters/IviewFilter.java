package main.control.viewFilters;

import java.util.List;

public interface IviewFilter {

    public void view();
    public default List<String[]> getFilter() {
        System.out.println("Returns valid rows based on filter.");
        return null;
    }
}
