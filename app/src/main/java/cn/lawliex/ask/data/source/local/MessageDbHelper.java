package cn.lawliex.ask.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.lawliex.ask.data.Message;


/**
 * Created by Terence on 2017/1/5.
 */

public class MessageDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "ask.db";
    private static final String TEXT_TYPES = " TEXT";
    private static final String DATE_TYPES = " DATE";
    private static final String INT_TYPES = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String[] row = {"id","from_id","to_id","created_date","conversation_id","has_read","content",
                                            "from_name","to_name","from_url","to_url","type"};
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + "message(" +
                    "id INTEGER PRIMARY KEY," +
                    "from_id" + INT_TYPES + COMMA_SEP +
                    "to_id" + INT_TYPES + COMMA_SEP +
                    "created_date" + DATE_TYPES + COMMA_SEP +
                    "conversation_id" + TEXT_TYPES + COMMA_SEP +
                    "has_read" + INT_TYPES + COMMA_SEP +
                    "content" + TEXT_TYPES + COMMA_SEP +
                    "from_name" + TEXT_TYPES + COMMA_SEP +
                    "to_name" + TEXT_TYPES + COMMA_SEP +
                    "from_url" + TEXT_TYPES + COMMA_SEP +
                    "type" + INT_TYPES + COMMA_SEP +
                    "to_url" + TEXT_TYPES +  ")";
    private Context context;
    public MessageDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Toast.makeText(context, "消息表创建成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists message");
        onCreate(db);
    }

    public int insert(Message message){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(row[0],message.getId());
            values.put(row[1], message.getFromId());
            values.put(row[2], message.getToId());
            values.put(row[3], message.getCreatedDate().getTime());
            values.put(row[4], message.getConversationId());
            values.put(row[5], message.getHasRead());
            values.put(row[6], message.getContent());
            values.put(row[7], message.getFromName());
            values.put(row[8], message.getToName());
            values.put(row[9], message.getFromUrl());
            values.put(row[10], message.getToUrl());
            values.put(row[11],message.getType());
            db.insert("message", null, values);
        }catch (Exception e){
            e.getMessage();
            return 1;
        }
        return 0;
    }
    public int update(Message message){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(row[0],message.getId());
            values.put(row[1], message.getFromId());
            values.put(row[2], message.getToId());
            values.put(row[3], message.getCreatedDate().getTime());
            values.put(row[4], message.getConversationId());
            values.put(row[5], message.getHasRead());
            values.put(row[6], message.getContent());
            values.put(row[7], message.getFromName());
            values.put(row[8], message.getToName());
            values.put(row[9], message.getFromUrl());
            values.put(row[10], message.getToUrl());
            values.put(row[11],message.getType());
            db.update("message", values,null,null);
        }catch (Exception e){
            e.getMessage();
            return 1;
        }
        return 0;
    }
    public int delete(Message message){
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.delete("message", "id = ?", new String[]{message.getId() + ""});
        }catch (Exception e){
            e.getMessage();
            return 1;
        }
        return 0;
    }
    public int getLastestMessageId(String conversationId){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from message where conversation_id = ?",
                new String[]{conversationId});
        if(cursor.moveToFirst()){
            int id = cursor.getInt(0);
            return id;
        }
        return 0;
    }
    public List<Message> getMessage(int userId){
        List<Message> messages = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from message where from_id = ? or to_id = ? order by created_date",
                new String[]{userId + "",userId + ""});
        if(cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setId(cursor.getInt(cursor.getColumnIndex(row[0])));
                message.setFromId(cursor.getInt(cursor.getColumnIndex(row[1])));
                message.setToId(cursor.getInt(cursor.getColumnIndex(row[2])));
                message.setCreatedDate(new Date(cursor.getLong(cursor.getColumnIndex(row[3]))));
                message.setConversationId(cursor.getString(cursor.getColumnIndex(row[4])));
                message.setHasRead(cursor.getInt(cursor.getColumnIndex(row[5])));
                message.setContent(cursor.getString(cursor.getColumnIndex(row[6])));
                message.setFromName(cursor.getString(cursor.getColumnIndex(row[7])));
                message.setToName(cursor.getString(cursor.getColumnIndex(row[8])));
                message.setFromUrl(cursor.getString(cursor.getColumnIndex(row[9])));
                message.setToUrl(cursor.getString(cursor.getColumnIndex(row[10])));
                message.setType(cursor.getInt(cursor.getColumnIndex(row[11])));
                messages.add(message);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return messages;
    }
}
