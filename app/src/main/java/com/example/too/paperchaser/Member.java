package com.example.too.paperchaser;

/**
 * Created by too on 11/21/17.
 */

public class Member {

    String memberId;
    String memberName;

    public Member (){

    }

    public Member(String memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
