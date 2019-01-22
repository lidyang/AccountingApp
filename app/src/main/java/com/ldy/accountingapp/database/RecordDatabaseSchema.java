package com.ldy.accountingapp.database;

/**
 * Created by ldy on 19/1/5.
 */

public class RecordDatabaseSchema {

    public static final class RecordTable {
        public static final String NAME = "record";

        public static final class cols{
            public static final String UUID = "uuid";
            public static final String TYPE = "type";
            public static final String CATEGORY="category";
            public static final String REMARK = "remark";
            public static final String AMOUNT = "amount";
            public static final String TIME = "time";
            public static final String DATE = "date";

        }
    }

}
