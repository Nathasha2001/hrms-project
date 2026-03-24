package com.example.hrmsproject.dto;

import lombok.Getter;

@Getter
public class EmergencyContactDto {

    private Long id;
    private String name;
    private String phone;

    public EmergencyContactDto() {
    }

    public EmergencyContactDto(Long employeeId, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
