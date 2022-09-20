package com.TumbleProject.community.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private Long BoardNum;
    private String BoardTitle;
    private String BoardContent;
    private String BoardWriter;
    private String BoardEnDate;

    public String getBoardWriter() {
        return BoardWriter;
    }

    public void setBoardWriter(String boardWriter) {
        BoardWriter = boardWriter;
    }

    public String getBoardEnDate() {
        return BoardEnDate;
    }

    public void setBoardEnDate(String boardEnDate) {
        BoardEnDate = boardEnDate;
    }

    public Long getBoardNum() {
        return BoardNum;
    }

    public void setBoardNum(Long boardNum) {
        BoardNum = boardNum;
    }

    public String getBoardTitle() {
        return BoardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        BoardTitle = boardTitle;
    }

    public String getBoardContent() {
        return BoardContent;
    }

    public void setBoardContent(String boardContent) {
        BoardContent = boardContent;
    }
}