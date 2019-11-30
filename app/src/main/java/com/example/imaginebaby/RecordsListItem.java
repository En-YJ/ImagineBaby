package com.example.imaginebaby;


public class RecordsListItem {

    private int records_id; // 아이디값
    private int records_image; //기록 이미지
    private String records_title;  // 기록 상위
    private String records_desc;  // 기록 하위
    private String records_time;   // 기록 시간


    // 생성자
    public RecordsListItem(int records_image, String records_title, String records_desc, String records_time) {
        this.records_image = records_image;
        this.records_title = records_title;
        this.records_desc = records_desc;
        this.records_time = records_time;
    }
    // 생성자
    public RecordsListItem(int records_id, int records_image, String records_title, String records_desc, String records_time) {
        this.records_id = records_id;
        this.records_image = records_image;
        this.records_title = records_title;
        this.records_desc = records_desc;
        this.records_time = records_time;
    }

    public int getRecords_id(){return records_id;}

    public int getRecords_image() { return records_image;}

    public String getRecords_title() {
        return records_title;
    }

    public String getRecords_desc() {
        return records_desc;
    }

    public String getRecords_time() {return records_time;}

}
