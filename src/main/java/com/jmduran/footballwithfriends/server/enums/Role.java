/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.enums;

public enum Role {
    ADMIN, TREASURER, COACH, PLAYER, GUEST;
    
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
