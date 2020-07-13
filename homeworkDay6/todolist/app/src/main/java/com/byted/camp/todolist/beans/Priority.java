package com.byted.camp.todolist.beans;

public enum Priority {
    //优先级逐级递减
    FIRST(1), SECOND(2),THIRD(3);

    public final int intValue;

    Priority(int intValue) {
        this.intValue = intValue;
    }

    public static Priority from(int intValue) {
        for (Priority priority : Priority.values()) {
            if (priority.intValue == intValue) {
                return priority;
            }
        }
        return THIRD; // default 默认第三级最低
    }
}
