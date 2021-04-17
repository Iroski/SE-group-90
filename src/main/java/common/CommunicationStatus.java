package common;

import lombok.AllArgsConstructor;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/5 20:34
 * @description:
 * @modifiedBy By:
 */
@AllArgsConstructor
public enum CommunicationStatus {
    OK(200,"success"),

    BAD_REQUEST(400,"input value is invalid"),
    USERNAME_ALREADY_EXISTS(4001,"username already exists"),
    TAG_ALREADY_EXIST_IN_VIDEO(40021,"this tag is already exist in video."),
    TAG_ALREADY_EXIST(40022,"this tag is exist"),
    VIDEO_ALREADY_EXIST_IN_TAG(40023,"this video is already exist in video"),
    VIDEO_ALREADY_EXIST(40024,"this video is already exist"),
    COACH_ALREADY_EXIST(4003,"this coach is already exist"),
    WRONG_PASSWORD(4004,"password is wrong"),


    USER_NOT_FOUND(4041,"user is not exist!"),
    ACCOUNT_NOT_FOUND(4042,"this account is not found"),
    LIVE_LESSON_TABLE_NOT_FOUND(4043,"live lesson table is not found"),
    COACH_NOT_FOUND(4044,"the coach is not found"),
    VIDEO_NOT_FOUND(4045,"there is no video!"),
        VIDEO_NOT_FOUND_IN_TAG(40451,"this video is not found in tag"),
    TAG_NOT_FOUND(4046,"this tag is not exist"),
        TAG_NOT_FOUND_IN_VIDEO(40461,"there is no this tag exist in the specific video"),
        TAG_OPERATION_NOT_FOUND(40462,"the operation for tag is not found"),
    ORDER_NOT_FOUND(4047,"this order is not exist"),

    INTERNAL_ERROR(5000,"database internal error"),
    NO_ENOUGH_BALANCE(5001,"your balance is not enough"),
    NO_ENOUGH_FREE_LESSON(5002,"there is no enough free lesson for your account")

    ;

    int code;
    String message;

    public String getMessage(){
        return this.message;
    }

    public int getCode(){
        return this.code;
    }
}
