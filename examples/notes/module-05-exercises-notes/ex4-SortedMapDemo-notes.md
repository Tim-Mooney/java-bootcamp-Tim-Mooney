TreeMap keys always iterate in sorted order — that part is guaranteed. HashMap key order is not a contract; it may differ between runs or JDK versions, so never depend on it for display order.

