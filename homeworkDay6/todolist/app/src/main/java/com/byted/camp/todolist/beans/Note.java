package com.byted.camp.todolist.beans;

import java.util.Date;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class Note implements Comparable<Note> {

    public final long id;
    private Priority priority;
    private Date date;
    private State state;
    private String content;

    public Note(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Note note) {
        //自定义比较方法，如果认为此实体本身大则返回1，否则返回-1
        if (this.priority.intValue >= note.getPriority().intValue) {
            return 1;
        }
        return -1;
    }
}
