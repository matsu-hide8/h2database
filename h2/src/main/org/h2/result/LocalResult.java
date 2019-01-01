/*
 * Copyright 2004-2019 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (http://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.result;

import org.h2.engine.SysProperties;
import org.h2.value.Value;

/**
 * A local result set contains all row data of a result set.
 * The object is generated by {@link LocalResultFactory},
 * and it is also used directly by the ResultSet class in the embedded mode.
 * The memory usage and other policies are defined by implementation.
 */
public interface LocalResult extends ResultInterface, ResultTarget {
    /**
     * Redefine count of maximum rows holds in memory for the result.
     *
     * @param maxValue Maximum rows count in memory.
     *
     * @see SysProperties#MAX_MEMORY_ROWS
     */
    public void setMaxMemoryRows(int maxValue);

    /**
     * @param sort Sort order.
     */
    public void setSortOrder(SortOrder sort);

    /**
     * Remove duplicate rows.
     */
    public void setDistinct();

    /**
     * Remove rows with duplicates in columns with specified indexes.
     *
     * @param distinctIndexes distinct indexes
     */
    public void setDistinct(int[] distinctIndexes);

    /**
     * Check if this result set contains the given row.
     *
     * @param values the row
     * @return true if the row exists
     */
    boolean containsDistinct(Value[] values);

    /**
     * Check if this result set contains a NULL value. This method may reset
     * this result.
     *
     * @return true if there is a NULL value
     */
    boolean containsNull();

    /**
     * Remove the row from the result set if it exists.
     *
     * @param values the row
     */
    public void removeDistinct(Value[] values);

    /**
     * This method is called after all rows have been added.
     */
    public void done();

    /**
     * Set the number of rows that this result will return at the maximum.
     *
     * @param limit the limit (-1 means no limit, 0 means no rows)
     */
    public void setLimit(int limit);

    /**
     * @param fetchPercent whether limit expression specifies percentage of rows
     */
    public void setFetchPercent(boolean fetchPercent);

    /**
     * @param withTies whether tied rows should be included in result too
     */
    public void setWithTies(boolean withTies);

    /**
     * Set the offset of the first row to return.
     *
     * @param offset the offset
     */
    public void setOffset(int offset);
}
