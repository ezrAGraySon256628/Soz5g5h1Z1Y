// 代码生成时间: 2025-09-30 01:56:31
import grails.transaction.Transactional

@Transactional
class TableSortFilterService {

    /***
     * Sorts a list of objects based on the specified property and direction.
     *
     * @param list - The list of objects to sort.
     * @param propertyName - The name of the property to sort by.
     * @param direction - The direction of the sort (asc or desc).
     * @return A sorted list of objects.
     * @throws IllegalArgumentException if the direction is not 'asc' or 'desc'.
     **/
    List sortList(List list, String propertyName, String direction) {
        if (!['asc', 'desc'].contains(direction.toLowerCase())) {
            throw new IllegalArgumentException('Invalid sort direction. Please use 