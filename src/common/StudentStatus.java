/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author NHI
 */
public enum StudentStatus {
    WAITING("Đợi khóa sau"),
    INPROGRESS("Đang theo học"),
    DONE("Đã hoàn thành"),
    DISCARD("Tạm dừng"),
    UNDEFINE("Chưa xác định");
    private String value;

    private StudentStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static StudentStatus getNameFromValue(final String valueof) {
        StudentStatus status = StudentStatus.UNDEFINE;
        switch (valueof) {
            case "Đợi khóa sau":
                status = StudentStatus.WAITING;
                break;
            case "Đang theo học":
                status = StudentStatus.INPROGRESS;
                break;
            case "Đã hoàn thành":
                status = StudentStatus.DONE;
                break;
            case "Tạm dừng":
                status = StudentStatus.DISCARD;
                break;
        }
        return status;
    }
}