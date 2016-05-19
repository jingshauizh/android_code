package com.example.aa.aaapp.greendao.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.aa.aaapp.greendao.model.PayoutEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAYOUT_ENTITY".
*/
public class PayoutEntityDao extends AbstractDao<PayoutEntity, Long> {

    public static final String TABLENAME = "PAYOUT_ENTITY";

    /**
     * Properties of entity PayoutEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PayoutId = new Property(1, Integer.class, "payoutId", false, "PAYOUT_ID");
        public final static Property AccountBookId = new Property(2, int.class, "accountBookId", false, "ACCOUNT_BOOK_ID");
        public final static Property AccountBookName = new Property(3, String.class, "accountBookName", false, "ACCOUNT_BOOK_NAME");
        public final static Property CategoryId = new Property(4, int.class, "categoryId", false, "CATEGORY_ID");
        public final static Property CategoryName = new Property(5, String.class, "categoryName", false, "CATEGORY_NAME");
        public final static Property Path = new Property(6, String.class, "path", false, "PATH");
        public final static Property PayWayId = new Property(7, int.class, "payWayId", false, "PAY_WAY_ID");
        public final static Property PlaceId = new Property(8, int.class, "placeId", false, "PLACE_ID");
        public final static Property Amount = new Property(9, long.class, "amount", false, "AMOUNT");
        public final static Property PayoutDate = new Property(10, java.util.Date.class, "payoutDate", false, "PAYOUT_DATE");
        public final static Property PayoutType = new Property(11, String.class, "payoutType", false, "PAYOUT_TYPE");
        public final static Property PayoutUserId = new Property(12, int.class, "payoutUserId", false, "PAYOUT_USER_ID");
        public final static Property Comment = new Property(13, String.class, "comment", false, "COMMENT");
        public final static Property CreateDate = new Property(14, java.util.Date.class, "createDate", false, "CREATE_DATE");
        public final static Property State = new Property(15, int.class, "state", false, "STATE");
    };


    public PayoutEntityDao(DaoConfig config) {
        super(config);
    }
    
    public PayoutEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAYOUT_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PAYOUT_ID\" INTEGER," + // 1: payoutId
                "\"ACCOUNT_BOOK_ID\" INTEGER NOT NULL ," + // 2: accountBookId
                "\"ACCOUNT_BOOK_NAME\" TEXT NOT NULL ," + // 3: accountBookName
                "\"CATEGORY_ID\" INTEGER NOT NULL ," + // 4: categoryId
                "\"CATEGORY_NAME\" TEXT NOT NULL ," + // 5: categoryName
                "\"PATH\" TEXT NOT NULL ," + // 6: path
                "\"PAY_WAY_ID\" INTEGER NOT NULL ," + // 7: payWayId
                "\"PLACE_ID\" INTEGER NOT NULL ," + // 8: placeId
                "\"AMOUNT\" INTEGER NOT NULL ," + // 9: amount
                "\"PAYOUT_DATE\" INTEGER NOT NULL ," + // 10: payoutDate
                "\"PAYOUT_TYPE\" TEXT NOT NULL ," + // 11: payoutType
                "\"PAYOUT_USER_ID\" INTEGER NOT NULL ," + // 12: payoutUserId
                "\"COMMENT\" TEXT NOT NULL ," + // 13: comment
                "\"CREATE_DATE\" INTEGER NOT NULL ," + // 14: createDate
                "\"STATE\" INTEGER NOT NULL );"); // 15: state
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_PAYOUT_ENTITY_PAYOUT_ID ON PAYOUT_ENTITY" +
                " (\"PAYOUT_ID\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAYOUT_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PayoutEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer payoutId = entity.getPayoutId();
        if (payoutId != null) {
            stmt.bindLong(2, payoutId);
        }
        stmt.bindLong(3, entity.getAccountBookId());
        stmt.bindString(4, entity.getAccountBookName());
        stmt.bindLong(5, entity.getCategoryId());
        stmt.bindString(6, entity.getCategoryName());
        stmt.bindString(7, entity.getPath());
        stmt.bindLong(8, entity.getPayWayId());
        stmt.bindLong(9, entity.getPlaceId());
        stmt.bindLong(10, entity.getAmount());
        stmt.bindLong(11, entity.getPayoutDate().getTime());
        stmt.bindString(12, entity.getPayoutType());
        stmt.bindLong(13, entity.getPayoutUserId());
        stmt.bindString(14, entity.getComment());
        stmt.bindLong(15, entity.getCreateDate().getTime());
        stmt.bindLong(16, entity.getState());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PayoutEntity readEntity(Cursor cursor, int offset) {
        PayoutEntity entity = new PayoutEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // payoutId
            cursor.getInt(offset + 2), // accountBookId
            cursor.getString(offset + 3), // accountBookName
            cursor.getInt(offset + 4), // categoryId
            cursor.getString(offset + 5), // categoryName
            cursor.getString(offset + 6), // path
            cursor.getInt(offset + 7), // payWayId
            cursor.getInt(offset + 8), // placeId
            cursor.getLong(offset + 9), // amount
            new java.util.Date(cursor.getLong(offset + 10)), // payoutDate
            cursor.getString(offset + 11), // payoutType
            cursor.getInt(offset + 12), // payoutUserId
            cursor.getString(offset + 13), // comment
            new java.util.Date(cursor.getLong(offset + 14)), // createDate
            cursor.getInt(offset + 15) // state
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PayoutEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPayoutId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setAccountBookId(cursor.getInt(offset + 2));
        entity.setAccountBookName(cursor.getString(offset + 3));
        entity.setCategoryId(cursor.getInt(offset + 4));
        entity.setCategoryName(cursor.getString(offset + 5));
        entity.setPath(cursor.getString(offset + 6));
        entity.setPayWayId(cursor.getInt(offset + 7));
        entity.setPlaceId(cursor.getInt(offset + 8));
        entity.setAmount(cursor.getLong(offset + 9));
        entity.setPayoutDate(new java.util.Date(cursor.getLong(offset + 10)));
        entity.setPayoutType(cursor.getString(offset + 11));
        entity.setPayoutUserId(cursor.getInt(offset + 12));
        entity.setComment(cursor.getString(offset + 13));
        entity.setCreateDate(new java.util.Date(cursor.getLong(offset + 14)));
        entity.setState(cursor.getInt(offset + 15));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PayoutEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PayoutEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}