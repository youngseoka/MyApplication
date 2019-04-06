package com.example.youngseok.myapplication.invite;

public class InviteDTO {

    private String phonebook_name;
    private String phonebook_phone;
    private int count =0;

    public String getPhonebook_name() {
        return phonebook_name;
    }

    public String getPhonebook_phone(){
        return phonebook_phone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPhonebook_name(String phonebook_name) {
        this.phonebook_name = phonebook_name;
    }

    public void setPhonebook_phone(String phonebook_phone) {
        this.phonebook_phone = phonebook_phone;
    }

}
