package com.pantau.core

/**
 * Created by widodo on 22/08/15.
 */
import grails.validation.Validateable;

@Validateable
class UserRegisterCommad {
    String username
    String password
    String email
    String ktp
    String nohp
}
